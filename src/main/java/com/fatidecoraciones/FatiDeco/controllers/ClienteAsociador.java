package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.models.Cliente;
import com.fatidecoraciones.FatiDeco.models.Medida;
import com.fatidecoraciones.FatiDeco.models.Taller;
import com.fatidecoraciones.FatiDeco.repositories.ClienteRepository;
import com.fatidecoraciones.FatiDeco.repositories.MedidaRepository;
import com.fatidecoraciones.FatiDeco.repositories.TallerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("asociar")
@CrossOrigin
public class ClienteAsociador {

    private final MedidaRepository medidaRepository;
    private final ClienteRepository clienteRepository;
    private final TallerRepository tallerRepository;


    public ClienteAsociador(MedidaRepository medidaRepository,
                            ClienteRepository clienteRepository,
                            TallerRepository tallerRepository) {
        this.medidaRepository = medidaRepository;
        this.clienteRepository = clienteRepository;
        this.tallerRepository = tallerRepository;
    }

    @GetMapping()
    public ResponseEntity<String> asociarClientesAPresupuestos() {
        List<Medida> medidas = medidaRepository.findAll();
        List<Taller> confecciones = tallerRepository.findAll();
        int presupuestosAsociados = 0;
        int confeccionesAsociadas = 0;

        for (Medida medida : medidas) {
            if (medida.getCliente() != null)
                continue;

            String nombreCliente = medida.getClienteNombre();
            if (nombreCliente == null || nombreCliente.trim().isEmpty())
                continue;

            Cliente cliente = clienteRepository.findByNombre(nombreCliente)
                    .orElseGet(() -> {
                        Cliente nuevo = new Cliente(nombreCliente.trim(), "", "");
                        return clienteRepository.save(nuevo);
                    });

            medida.setCliente(cliente);
            medidaRepository.save(medida);
            presupuestosAsociados++;
        }
        for (Taller confeccion : confecciones) {
            if (confeccion.getCliente() != null)
                continue;

            String nombreCliente = confeccion.getClienteNombre();
            if (nombreCliente == null || nombreCliente.trim().isEmpty())
                continue;

            Cliente cliente = clienteRepository.findByNombre(nombreCliente)
                    .orElseGet(() -> {
                        Cliente nuevo = new Cliente(nombreCliente.trim(), "", "");
                        return clienteRepository.save(nuevo);
                    });

            confeccion.setCliente(cliente);
            tallerRepository.save(confeccion);
            confeccionesAsociadas++;
        }
        return ResponseEntity.ok(presupuestosAsociados +" Medias asociadas correctamente.\n"
        + confeccionesAsociadas + " Confecciones asociadas correctamente");
    }
}