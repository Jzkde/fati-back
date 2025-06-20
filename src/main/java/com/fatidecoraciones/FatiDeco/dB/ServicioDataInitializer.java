package com.fatidecoraciones.FatiDeco.dB;

import org.springframework.context.annotation.Configuration;


@Configuration
public class ServicioDataInitializer {

//    private final ServicioService servicioService;
//    private final MarcaService marcaService;
//
//    public ServicioDataInitializer(ServicioService servicioService, MarcaService marcaService) {
//        this.servicioService = servicioService;
//        this.marcaService = marcaService;
//    }
//
//    @Bean
//    public CommandLineRunner servicioInitData() {
//        return (args -> {
//
//            //Marca
//            Marca fati = new Marca(
//                    "FATI"
//            );
//
//            //Servicio
//            Servicio servicio1 = new Servicio(
//                    "Simple",
//                    Serv.COLOCACION,
//                    6600
//            );
//            Servicio servicio2 = new Servicio(
//                    "Doble",
//                    Serv.COLOCACION,
//                    9500
//            );
//            Servicio servicio3 = new Servicio(
//                    "Verticales",
//                    Serv.COLOCACION,
//                    13320
//            );
//            Servicio servicio4 = new Servicio(
//                    "Sin Servicio",
//                    Serv.COLOCACION,
//                    0
//            );
//
//            //Taller
//            Servicio propia = new Servicio(
//                    "Propia",
//                    Serv.CONFECCION,
//                    13320
//            );
//            Servicio ajena = new Servicio(
//                    "Ajena",
//                    Serv.CONFECCION,
//                    16000
//            );
//
//
//
//            fati.addMarcaServ(servicio1);
//            fati.addMarcaServ(servicio2);
//            fati.addMarcaServ(servicio3);
//            fati.addMarcaServ(servicio4);
//            fati.addMarcaServ(propia);
//            fati.addMarcaServ(ajena);
//
//            marcaService.save(fati);
//
//            servicioService.save(servicio1);
//            servicioService.save(servicio2);
//            servicioService.save(servicio3);
//            servicioService.save(servicio4);
//
//            servicioService.save(ajena);
//            servicioService.save(propia);
//        });
//    }
}