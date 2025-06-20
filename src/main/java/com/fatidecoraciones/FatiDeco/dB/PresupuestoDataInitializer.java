package com.fatidecoraciones.FatiDeco.dB;

import org.springframework.context.annotation.Configuration;


@Configuration
public class PresupuestoDataInitializer {
//
//    private final PresupuestoService presupuestoService;
//
//    public PresupuestoDataInitializer(PresupuestoService presupuestoService) {
//        this.presupuestoService = presupuestoService;
//    }
//
//    @Bean
//    public CommandLineRunner presupuestoInitData() {
//        return (args -> {
//
//            Presupuesto presupuestoR1 = new Presupuesto(
//                    Sistema.ROLLER,
//                    120,
//                    130,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "Dor Principal",
//                    "Pared Durlock",
//                    "Cliente1",
//                    LocalDate.now(),
//                    false,
//                    false,
//                    false
//            );
//            Presupuesto presupuestoR2 = new Presupuesto(
//                    Sistema.ROLLER,
//                    240,
//                    130,
//                    Comando.IZQUIERDO,
//                    Apertura.NO_POSEE,
//                    "Sop Doble",
//                    "Living",
//                    "A recho",
//                    "Cliente1",
//                    LocalDate.now(),
//                    false,
//                    false,
//                    false
//            );
//            Presupuesto presupuestoP1 = new Presupuesto(
//                    Sistema.PERSIANA,
//                    120,
//                    100,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente2",
//                    LocalDate.now(),
//                    true,
//                    false,
//                    false
//            );
//            Presupuesto presupuestoP2 = new Presupuesto(
//                    Sistema.PERSIANA,
//                    100,
//                    140,
//                    Comando.IZQUIERDO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente2",
//                    LocalDate.now(),
//                    true,
//                    true,
//                    false
//            );
//            Presupuesto presupuestoT1 = new Presupuesto(
//                    Sistema.TELA,
//                    180,
//                    210,
//                    Comando.NO_POSEE,
//                    Apertura.BILATERAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente3",
//                    LocalDate.now(),
//                    false,
//                    true,
//                    false
//            );
//            Presupuesto presupuestoT2 = new Presupuesto(
//                    Sistema.TELA,
//                    120,
//                    130,
//                    Comando.NO_POSEE,
//                    Apertura.CENTRAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente5",
//                    LocalDate.now(),
//                    false,
//                    false,
//                    false
//            );
//            Presupuesto presupuestoV1 = new Presupuesto(
//                    Sistema.VERTICALES,
//                    120,
//                    130,
//                    Comando.IZQUIERDO,
//                    Apertura.CENTRAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente4",
//                    LocalDate.now(),
//                    true,
//                    true,
//                    true
//            );
//            Presupuesto presupuestoV2 = new Presupuesto(
//                    Sistema.VERTICALES,
//                    360,
//                    220,
//                    Comando.IZQUIERDO,
//                    Apertura.BILATERAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente4",
//                    LocalDate.now(),
//                    true,
//                    true,
//                    true
//            );
//            Presupuesto presupuestoD1 = new Presupuesto(
//                    Sistema.DUBAI,
//                    100,
//                    115,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente6",
//                    LocalDate.now(),
//                    false,
//                    true,
//                    true
//            );
//            Presupuesto presupuestoD2 = new Presupuesto(
//                    Sistema.DUBAI,
//                    160,
//                    220,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente6",
//                    LocalDate.now(),
//                    false,
//                    false,
//                    true
//            );
//
//            presupuestoService.save(presupuestoR1);
//            presupuestoService.save(presupuestoR2);
//            presupuestoService.save(presupuestoP1);
//            presupuestoService.save(presupuestoP2);
//            presupuestoService.save(presupuestoT1);
//            presupuestoService.save(presupuestoT2);
//            presupuestoService.save(presupuestoV1);
//            presupuestoService.save(presupuestoV2);
//            presupuestoService.save(presupuestoD1);
//            presupuestoService.save(presupuestoD2);
//        });
//    }
}