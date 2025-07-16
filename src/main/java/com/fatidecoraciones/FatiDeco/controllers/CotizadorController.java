package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.ProductoDto;
import com.fatidecoraciones.FatiDeco.dtos.RangoDto;
import com.fatidecoraciones.FatiDeco.dtos.TelaCotizadaDto;
import com.fatidecoraciones.FatiDeco.models.*;
import com.fatidecoraciones.FatiDeco.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("cotizar")
@CrossOrigin
public class CotizadorController {

    private final CortEspecialesService cortEspecialesService;
    private final ProductoService productoService;
    private final ServicioService servicioService;
    private final SistemaService sistemaService;
    private final RangoService rangoService;

    public CotizadorController(CortEspecialesService cortEspecialesService,
                               ProductoService productoService,
                               ServicioService servicioService,
                               SistemaService sistemaService,
                               RangoService rangoService) {

        this.cortEspecialesService = cortEspecialesService;
        this.productoService = productoService;
        this.servicioService = servicioService;
        this.sistemaService = sistemaService;
        this.rangoService = rangoService;
    }

    @GetMapping("/sistemas/{marca}")
    public ResponseEntity<?> cotizarSistemas(@RequestParam String telaN, int alto, int ancho, String sistemaN, @PathVariable String marca) {

        if (!cortEspecialesService.verificarMarca(marca)) {
            return new ResponseEntity<>("La MARCA no existe:" + marca, HttpStatus.NOT_FOUND);
        }
        if (cortEspecialesService.findByTela(telaN) == null) {
            return new ResponseEntity<>("La TELA no existe:" + telaN, HttpStatus.NOT_FOUND);
        }
        if (sistemaService.findBySistema(sistemaN) == null) {
            return new ResponseEntity<>("SISTEMA no válido:" + sistemaN, HttpStatus.BAD_REQUEST);
        }

        Sistema sistema = sistemaService.findBySistema(sistemaN);
        CortEspeciales tela = cortEspecialesService.findByTela(telaN);


        if (!marca.equalsIgnoreCase(tela.getMarca().getMarca())) {
            return new ResponseEntity<>("La MARCA: " + marca + " no corresponde a la TELA: " + tela.getTela(), HttpStatus.NOT_FOUND);
        }
        if (!Objects.equals(tela.getSistema().getId(), sistema.getId())) {
            return new ResponseEntity<>("La TELA: " + telaN + " no corresponde al sistema: " + sistema.getSistema(), HttpStatus.NOT_FOUND);
        }

        Rango rango = rangoService.obtenerRango(alto, ancho, marca, sistemaN);

        if (rango == null) {
            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.NOT_FOUND);
        }

        String sistemaNombre = rango.getCortEspeciales().getSistema().getSistema();

        double precioSistXmetro = rango.getCortEspeciales().getPrecio();
        float sistemaMin = rango.getSistemaMin();

        double telaXMetro = tela.getPrecio();
        float telaMin = rango.getTelaMin();

        double precioSistTotal;
        double precioTelaTotal;

        double mtTela = ancho * alto / 100.0;


        if (ancho < sistemaMin) {
            precioSistTotal = precioSistXmetro * sistemaMin / 100;
        } else {
            precioSistTotal = precioSistXmetro * ancho / 100;
        }

        if (mtTela < telaMin) {
            precioTelaTotal = telaXMetro * telaMin /100;
        } else {
            precioTelaTotal = telaXMetro * mtTela /100;
        }


        Double total = precioTelaTotal + precioSistTotal;

        System.out.println("Sistema: " + sistemaNombre);
        System.out.println("Precio x Mt sistema: " + precioSistXmetro);
        System.out.println("Precio Total sistema: " + precioSistTotal);

        System.out.println("Ancho: " + ancho + " / Alto: " + alto);

        System.out.println("Tela: " + tela.getTela());
        System.out.println("Precio x Mt2 tela: " + telaXMetro);
        System.out.println("Mt2 de tela: " + mtTela);
        System.out.println("Precio Total tela: " + precioTelaTotal);

        System.out.println("Total: " + total);
        System.out.println("-------------------------");

        return new ResponseEntity<>(total, HttpStatus.OK);

    }

    @PostMapping("/telas")
    public ResponseEntity<?> cotizarTelas(@RequestParam String telaN, Float ancho, Float prop, String
            tipoConf, @RequestBody(required = false) List<ProductoDto> accesorios) {
        //Tela
        Producto tela = productoService.findByNombreAndEsTela(true, telaN);
        if (tela == null) {
            return new ResponseEntity<>("No existe la TELA", HttpStatus.NOT_FOUND);
        }

        double precioTela = tela.getPrecio();

        if (prop == null || prop <= 0) {
            return new ResponseEntity<>("La PROPORCION de tela no puede ser 0 o menor", HttpStatus.FORBIDDEN);
        }
        if (ancho == null || ancho <= 0) {
            return new ResponseEntity<>("El ANCHO de la cortina no puede ser 0 o menor", HttpStatus.FORBIDDEN);
        }

        float cantTela = ancho * prop;
        double costoTela = precioTela * cantTela;

        //Confeccion
        Servicio precioConf = servicioService.findByNombre(tipoConf);
        if (precioConf == null) {
            return new ResponseEntity<>("No existe el tipo de CONFECCION", HttpStatus.NOT_FOUND);
        }

        int confec = (int) Math.ceil(cantTela / 1.5);
        double costoConf = confec * precioConf.getPrecio();

        if (accesorios == null) {
            accesorios = new ArrayList<>();
        }

        //Accesorios
        double precioAcc = 0.0;
        if (!accesorios.isEmpty()) {
            for (ProductoDto accesorio : accesorios) {
                // Verificar si el accesorio tiene esTela=false
                if (!accesorio.isEsTela()) {
                    Producto productoAcc = productoService.findByNombreAndEsTela(false, accesorio.getNombre());
                    if (productoAcc == null) {
                        return new ResponseEntity<>("No existe el ACCESORIO: " + accesorio.getNombre(), HttpStatus.NOT_FOUND);
                    }
                    precioAcc += productoAcc.getPrecio();
                }
            }
        }

        // Total
        double total = precioAcc + costoConf + costoTela;

        System.out.println("Precio tela: $" + precioTela);
        System.out.println("Cantidad de tela: " + cantTela + " mts");
        System.out.println("Costo total de tela: $" + costoTela);
        System.out.println("----------------");
        System.out.println("Precio confección: $" + precioConf.getPrecio());
        System.out.println("Cantidad de paños: " + confec);
        System.out.println("Costo total de confección: $" + costoConf);
        System.out.println("-----------------");
        System.out.println("Costo total de los accesorios: $" + precioAcc);
        System.out.println("******************");
        System.out.println("Total: $" + total);

        TelaCotizadaDto telaCotizada = new TelaCotizadaDto(precioTela, cantTela, costoTela, precioConf.getPrecio(), confec, costoConf, precioAcc, total);
        return new ResponseEntity<>(telaCotizada, HttpStatus.OK);
    }
}
