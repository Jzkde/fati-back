package com.fatidecoraciones.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PedidosApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(PedidosApplication.class, args);
    }


//    @Bean
//    public CommandLineRunner initData(PedidoRepository pedidoService,
//                                      PresupuestoRepository presupuestoRepository,
//                                      FlexRepository flexService
//    ) {
//        return (args -> {
//
//            Flex flex1 = new Flex(
//                    "SISTEMA VTX15 - 32MM",
//                    46290.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            Flex flex2 = new Flex(
//                    "SISTEMA VTX15 -38MM",
//                    50570.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            Flex flex3 = new Flex(
//                    "SISTEMA VTX20 -45MM",
//                    55740.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            Flex flex4 = new Flex(
//                    "BLACKOUT VINILICO (Ancho 1.83 mts)",
//                    48310.0,
//                    true,
//                    Sistema.ROLLER
//            );
//            Pedido pedido1 = new Pedido(
//                    LocalDate.now(),
//                    "Flexcolor",
//                    "Mail",
//                    "19999",
//                    "A-15666",
//                    "R-986765",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente1",
//                    "Diego",
//                    ""
//            );
//            Pedido pedido2 = new Pedido(
//                    LocalDate.now(),
//                    "SabelCort",
//                    "Web",
//                    "987456",
//                    "15666",
//                    "R-98665",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente2",
//                    "Diego",
//                    ""
//            );
//            Pedido pedido3 = new Pedido(
//                    LocalDate.now(),
//                    "SabeCort",
//                    "Telefono",
//                    "7894",
//                    "B-9890",
//                    "R-68756",
//                    65465,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente3",
//                    "Gonzalo",
//                    ""
//            );
//            Pedido pedido4 = new Pedido(
//                    LocalDate.now(),
//                    "Muresco",
//                    "Mail",
//                    "19999",
//                    "A-15666",
//                    "R-986765",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente4",
//                    "Diego",
//                    ""
//            );
//            Pedido pedido5 = new Pedido(
//                    LocalDate.now(),
//                    "SabelCort",
//                    "Web",
//                    "5656",
//                    "15666",
//                    "R-854665",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente5",
//                    "Gonzalo",
//                    ""
//            );
//            Pedido pedido6 = new Pedido(
//                    LocalDate.now(),
//                    "SabeCort",
//                    "Telefono",
//                    "7894",
//                    "B-9890",
//                    "R-68756",
//                    65465,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente6",
//                    "Matias",
//                    ""
//            );
//            Pedido pedido7 = new Pedido(
//                    LocalDate.now(),
//                    "Flexcolor",
//                    "Mail",
//                    "19999",
//                    "A-15666",
//                    "R-986765",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente7",
//                    "Matias",
//                    ""
//            );
//            Pedido pedido8 = new Pedido(
//                    LocalDate.now(),
//                    "Shefa",
//                    "Web",
//                    "987456",
//                    "15666",
//                    "R-98665",
//                    987987,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente8",
//                    "Diego",
//                    ""
//            );
//            Pedido pedido9 = new Pedido(
//                    LocalDate.now(),
//                    "SabeCort",
//                    "Telefono",
//                    "7894",
//                    "B-9890",
//                    "R-68756",
//                    65465,
//                    false,
//                    Estado.PEDIDO,
//                    "Cliente9",
//                    "Gonzalo",
//                    ""
//            );
//            Presupuesto presupuesto1 = new Presupuesto(
//                    Sistema.ROLLER,
//                    120,
//                    130,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente1"
//
//            );
//            Presupuesto presupuesto2 = new Presupuesto(
//                    Sistema.PERSIANA,
//                    120,
//                    100,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente2"
//            );
//            Presupuesto presupuesto3 = new Presupuesto(
//                    Sistema.TELA,
//                    180,
//                    210,
//                    Comando.NO_POSEE,
//                    Apertura.BILATERAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente3"
//            );
//            Presupuesto presupuesto4 = new Presupuesto(
//                    Sistema.VERTICALES,
//                    120,
//                    130,
//                    Comando.IZQUIERDO,
//                    Apertura.CENTRAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente4"
//            );
//            Presupuesto presupuesto5 = new Presupuesto(
//                    Sistema.TELA,
//                    120,
//                    130,
//                    Comando.NO_POSEE,
//                    Apertura.CENTRAL,
//                    "",
//                    "",
//                    "",
//                    "Cliente5"
//            );
//            Presupuesto presupuesto6 = new Presupuesto(
//                    Sistema.DUBAI,
//                    100,
//                    115,
//                    Comando.DERECHO,
//                    Apertura.NO_POSEE,
//                    "",
//                    "",
//                    "",
//                    "Cliente6"
//            );
//
//            pedidoService.save(pedido1);
//            pedidoService.save(pedido2);
//            pedidoService.save(pedido3);
//            pedidoService.save(pedido4);
//            pedidoService.save(pedido5);
//            pedidoService.save(pedido6);
//            pedidoService.save(pedido7);
//            pedidoService.save(pedido8);
//            pedidoService.save(pedido9);
//
//            presupuestoRepository.save(presupuesto2);
//            presupuestoRepository.save(presupuesto3);
//            presupuestoRepository.save(presupuesto4);
//            presupuestoRepository.save(presupuesto5);
//            presupuestoRepository.save(presupuesto6);
//
//            flexService.save(flex1);
//            flexService.save(flex2);
//            flexService.save(flex3);
//            flexService.save(flex4);
//        });
//
//    }

}
