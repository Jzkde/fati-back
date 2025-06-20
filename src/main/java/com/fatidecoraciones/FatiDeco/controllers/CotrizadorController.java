package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.ProductoDto;
import com.fatidecoraciones.FatiDeco.dtos.TelaCotizadaDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.Producto;
import com.fatidecoraciones.FatiDeco.models.Servicio;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.fatidecoraciones.FatiDeco.services.CortEspecialesService;
import com.fatidecoraciones.FatiDeco.services.ProductoService;
import com.fatidecoraciones.FatiDeco.services.ServicioService;
import com.fatidecoraciones.FatiDeco.services.SistemaService;
import com.itextpdf.styledxmlparser.css.util.CssDimensionParsingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("cotizar")
@CrossOrigin
public class CotrizadorController {

    private final CortEspecialesService cortEspecialesService;
    private final ProductoService productoService;
    private final ServicioService servicioService;
    private final SistemaService sistemaService;

    public CotrizadorController(CortEspecialesService cortEspecialesService,
                                ProductoService productoService,
                                ServicioService servicioService,
                                SistemaService sistemaService) {

        this.cortEspecialesService = cortEspecialesService;
        this.productoService = productoService;
        this.servicioService = servicioService;
        this.sistemaService = sistemaService;
    }

    @GetMapping("/sistemas/{marca}")
    public ResponseEntity<?> cotizarSistemas(@RequestParam String telaN, int alto, int ancho, String sistemaN, @PathVariable String marca) {


        Sistema sistema = sistemaService.findBySistema(sistemaN);
        if (sistema == null) {
            return new ResponseEntity<>("SISTEMA no válido:" + sistemaN, HttpStatus.BAD_REQUEST);
        }
        if (!cortEspecialesService.verificarMarca(marca)) {
            return new ResponseEntity<>("La MARCA no existe:" + marca, HttpStatus.NOT_FOUND);
        }
        if (cortEspecialesService.findByTela(telaN) == null) {
            return new ResponseEntity<>("La TELA no existe:" + telaN, HttpStatus.NOT_FOUND);
        }

        CortEspeciales tela = cortEspecialesService.findByTela(telaN);

        double sist;
        double precioSist;
        double precioTela;
        double mtTela = ancho * alto / 10000.0;

        switch (marca.toUpperCase()) {
            case "FLEXCOLOR":
                switch (sistemaN.toUpperCase()) {
                    case "ROLLER":

                        CortEspeciales VTX32 = cortEspecialesService.findByTela("SISTEMA VTX15 - 32MM");
                        CortEspeciales VTX38 = cortEspecialesService.findByTela("SISTEMA VTX15 -38MM");
                        CortEspeciales VTX45 = cortEspecialesService.findByTela("SISTEMA VTX20 -45MM");

                        if (ancho > 50 && ancho < 100) {
                            sist = VTX32.getPrecio() * 100;
                        } else if (ancho >= 100 && ancho <= 160) {
                            sist = VTX32.getPrecio() * ancho;
                        } else if (ancho > 160 && ancho <= 250) {
                            sist = VTX38.getPrecio() * ancho;
                        } else if (ancho > 250 && ancho <= 400) {
                            sist = VTX45.getPrecio() * ancho;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio();
                        }
                        break;

                    case "VERTICALES":
                        if (ancho > 50 && ancho < 150) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * 150;
                        } else if (ancho >= 150 && ancho < 400) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * ancho;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1.5) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio() * 1.5;
                        }
                        break;

                    case "PERSIANA":
                        if (ancho > 50 && mtTela < 3) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * 100;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio();
                        }
                        break;

                    case "ORIENTAL":
                    case "DUBAI":
                    case "ROMANA":
                        if (ancho > 50 && ancho < 100) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * 100;
                        } else if (ancho >= 100 && ancho < 400) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * ancho;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio();
                        }
                        break;

                    default:
                        return new ResponseEntity<>("SISTEMA no válido para esta marca", HttpStatus.BAD_REQUEST);
                }
                break;

            case "ROYALCORT":

                if (cortEspecialesService.findByTelaAndSistemaAndMarca(sistemaN, telaN, marca) == null) {
                    return new ResponseEntity<>("La TELA no corresponde al SISTEMA", HttpStatus.NOT_FOUND);
                }
                switch (sistemaN.toUpperCase()) {
                    case "ROLLER":
                        CortEspeciales VTX32 = cortEspecialesService.findByTela("RC Sistema Roller 32Mm");
                        CortEspeciales VTX38 = cortEspecialesService.findByTela("RC Sistema Roller 38Mm");
                        CortEspeciales VTX45 = cortEspecialesService.findByTela("RC Sistema Roller 45Mm");

                        if (ancho <= 50 && alto <= 180 || (ancho > 50 && ancho <= 180 && alto <= 240) || (ancho > 180 && ancho <= 210 && alto <= 200) || (ancho > 210 && ancho <= 240 && alto <= 100)) {

                            sist = VTX32.getPrecio() * ancho;
                        } else if ((ancho <= 50 && alto > 180 && alto <= 280) || (ancho > 50 && ancho <= 90 && alto > 240 && alto <= 280) || (ancho > 90 && ancho <= 180 && alto > 240 && alto <= 320) || (ancho > 180 && ancho <= 210 && alto > 220 && alto <= 320) || (ancho > 210 && ancho <= 240 && alto > 100 && alto <= 320) || (ancho > 240 && ancho <= 270 && alto <= 220) || (ancho > 270 && ancho <= 300 && alto <= 120)) {
                            sist = VTX38.getPrecio() * ancho;
                        } else if ((ancho > 90 && ancho <= 240 && alto > 320 && alto <= 380) || (ancho > 240 && ancho <= 270 && alto > 240 && alto <= 380) || (ancho > 270 && ancho <= 300 && alto > 140 && alto <= 260) || (ancho > 300 && ancho <= 330 && alto <= 240) || (ancho > 330 && ancho <= 360 && alto <= 120)) {
                            sist = VTX45.getPrecio() * ancho;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio();
                        }
                        break;

                    case "ZEBRA":
                        if (ancho > 50 && ancho < 150) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * 150;
                        } else if (ancho >= 150 && ancho < 400) {
                            sist = cortEspecialesService.getSistema(sistemaN).get().getPrecio() * ancho;
                        } else {
                            return new ResponseEntity<>("Fuera de RANGO", HttpStatus.OK);
                        }

                        if (mtTela > 1.5) {
                            precioTela = tela.getPrecio() * mtTela;
                        } else {
                            precioTela = tela.getPrecio() * 1.5;
                        }
                        break;

                    default:
                        return new ResponseEntity<>("SISTEMA no válido para esta marca", HttpStatus.BAD_REQUEST);
                }
                break;

            default:
                return new ResponseEntity<>("MARCA no soportada", HttpStatus.BAD_REQUEST);
        }

        precioSist = sist / 100;
        Double total = precioTela + precioSist;

        System.out.println("Precio sistema: " + precioSist);
        System.out.println("Ancho: " + ancho);
        System.out.println("Alto: " + alto);
        System.out.println("Tela: " + tela.getTela());
        System.out.println("Precio tela: " + precioTela);
        System.out.println("Total: " + total);
        System.out.println("-------------------------");

        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @PostMapping("/telas")
    public ResponseEntity<?> cotizarTelas(@RequestParam String telaN, Float ancho, Float prop, String tipoConf, @RequestBody(required = false) List<ProductoDto> accesorios) {
        //Tela
        Producto tela = productoService.findByNombreAndEsTela(true, telaN);
        if (tela == null) {
            return new ResponseEntity<>("No existe la TELA", HttpStatus.NOT_FOUND);
        }

        Double precioTela = tela.getPrecio();

        if (prop == null || prop <= 0) {
            return new ResponseEntity<>("La PROPORCION de tela no puede ser 0 o menor", HttpStatus.FORBIDDEN);
        }
        if (ancho == null || ancho <= 0) {
            return new ResponseEntity<>("El ANCHO de la cortina no puede ser 0 o menor", HttpStatus.FORBIDDEN);
        }

        Float cantTela = ancho * prop;
        Double costoTela = precioTela * cantTela;

        //Confeccion
        Servicio precioConf = servicioService.findByNombre(tipoConf);
        if (precioConf == null) {
            return new ResponseEntity<>("No existe el tipo de CONFECCION", HttpStatus.NOT_FOUND);
        }

        int confec = (int) Math.ceil(cantTela / 1.5);
        Double costoConf = confec * precioConf.getPrecio();

        if (accesorios == null) {
            accesorios = new ArrayList<>();
        }

        //Accesorios
        Double precioAcc = 0.0;
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
        Double total = precioAcc + costoConf + costoTela;

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
