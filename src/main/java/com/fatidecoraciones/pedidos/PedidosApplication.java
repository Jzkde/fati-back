package com.fatidecoraciones.pedidos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PedidosApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner initData (PedidoRepository pedidoService,
//									   ClienteService clienteService,
//									   PresupuestoRepository presupuestoRepository,
//									   FlexRepository flexService
//	){
//		return (args -> {
//
//			Flex flex1 = new Flex(
//					"SISTEMA VTX15 - 32MM",
//					46290.0,
//					false,
//					Sistema.ROLLER
//
//			);
//
//			Flex flex2 = new Flex(
//					"SISTEMA VTX15 -38MM",
//					50570.0,
//					false,
//					Sistema.ROLLER
//			);
//
//			Flex flex3 = new Flex(
//					"SISTEMA VTX20 -45MM",
//					55740.0,
//					false,
//					Sistema.ROLLER
//
//			);
//
//			Flex flex4 = new Flex(
//					"BLACKOUT VINILICO (Ancho 1.83 mts)",
//					48310.0,
//					true,
//					Sistema.ROLLER
//
//			);
//
//			Cliente cliente1 = new Cliente(
//					"Jose",
//					"Quiroga",
//					"J J Paso 421",
//					"3884790124"
//			);
//			Cliente cliente2 = new Cliente(
//					"Ernesto",
//					"Mamani",
//					"Las vicuñas 1421",
//					"3884280999"
//			);
//			Cliente cliente3 = new Cliente(
//					"Maria",
//					"Melano",
//					"mz 13 lt 91 - B° Malvinas",
//					"3714963344"
//			);
//			Cliente cliente4 = new Cliente(
//					"Ines",
//					"Jure",
//					"San Martin 915",
//					"3884227966");
//
//			Pedido pedido1 = new Pedido(
//					"Flexcolor",
//					"Mail",
//					"19999",
//					"A-15666",
//					"R-986765",
//					987987,
//					"Diego",
//					""
//			);
//			Pedido pedido2 = new Pedido(
//					"SabelCort",
//					"Web",
//					"987456",
//					"15666",
//					"R-98665",
//					987987,
//					"Diego",
//					""
//			);
//			Pedido pedido3 = new Pedido(
//					"SabeCort",
//					"Telefono",
//					"7894",
//					"B-9890",
//					"R-68756",
//					65465,
//					"Gonzalo",
//					""
//			);
//			Pedido pedido4 = new Pedido(
//					"Muresco",
//					"Mail",
//					"19999",
//					"A-15666",
//					"R-986765",
//					987987,
//					"Diego",
//					""
//			);
//			Pedido pedido5 = new Pedido(
//					"SabelCort",
//					"Web",
//					"5656",
//					"15666",
//					"R-854665",
//					987987,
//					"Gonzalo",
//					""
//			);
//			Pedido pedido6 = new Pedido(
//					"SabeCort",
//					"Telefono",
//					"7894",
//					"B-9890",
//					"R-68756",
//					65465,
//					"Matias",
//					""
//			);
//			Pedido pedido7 = new Pedido(
//					"Flexcolor",
//					"Mail",
//					"19999",
//					"A-15666",
//					"R-986765",
//					987987,
//					"Matias",
//					""
//			);
//			Pedido pedido8 = new Pedido(
//					"Shefa",
//					"Web",
//					"987456",
//					"15666",
//					"R-98665",
//					987987,
//					"Diego",
//					""
//			);
//			Pedido pedido9 = new Pedido(
//					"SabeCort",
//					"Telefono",
//					"7894",
//					"B-9890",
//					"R-68756",
//					65465,
//					"Gonzalo",
//					""
//			);
//			Presupuesto presupuesto1 = new Presupuesto(
//					Sistema.ROLLER,
//					120,
//					130,
//					Comando.DERECHO,
//					Apertura.NO_POSEE,
//					"",
//					"",
//					""
//
//			);
//			Presupuesto presupuesto2 = new Presupuesto(
//					Sistema.PERCIANA,
//					120,
//					100,
//					Comando.DERECHO,
//					Apertura.NO_POSEE,
//					"",
//					"",
//					""
//			);
//			Presupuesto presupuesto3 = new Presupuesto(
//					Sistema.TELA,
//					180,
//					210,
//					Comando.NO_POSEE,
//					Apertura.BILATERAL,
//					"",
//					"",
//					""
//			);
//			Presupuesto presupuesto4 = new Presupuesto(
//					Sistema.VERTICALES,
//					120,
//					130,
//					Comando.IZQUIERDO,
//					Apertura.CENTRAL,
//					"",
//					"",
//					""
//			);
//			Presupuesto presupuesto5 = new Presupuesto(
//					Sistema.TELA,
//					120,
//					130,
//					Comando.NO_POSEE,
//					Apertura.CENTRAL,
//					"",
//					"",
//					""
//			);
//			Presupuesto presupuesto6 = new Presupuesto(
//					Sistema.DUBAI,
//					100,
//					115,
//					Comando.DERECHO,
//					Apertura.NO_POSEE,
//					"",
//					"",
//					""
//			);
//
//			cliente1.addPedidos(pedido1);
//			cliente1.addPedidos(pedido2);
//			cliente1.addPedidos(pedido3);
//			cliente2.addPedidos(pedido4);
//			cliente2.addPedidos(pedido5);
//			cliente2.addPedidos(pedido6);
//			cliente3.addPedidos(pedido7);
//			cliente4.addPedidos(pedido8);
//			cliente4.addPedidos(pedido9);
//
//			cliente1.addPresupuesto(presupuesto1);
//			cliente1.addPresupuesto(presupuesto2);
//			cliente2.addPresupuesto(presupuesto3);
//			cliente3.addPresupuesto(presupuesto4);
//			cliente3.addPresupuesto(presupuesto5);
//			cliente4.addPresupuesto(presupuesto6);
//
//			clienteService.save(cliente1);
//			clienteService.save(cliente2);
//			clienteService.save(cliente3);
//			clienteService.save(cliente4);
//
//			pedidoService.save(pedido1);
//			pedidoService.save(pedido2);
//			pedidoService.save(pedido3);
//			pedidoService.save(pedido4);
//			pedidoService.save(pedido5);
//			pedidoService.save(pedido6);
//			pedidoService.save(pedido7);
//			pedidoService.save(pedido8);
//			pedidoService.save(pedido9);
//
//			presupuestoRepository.save(presupuesto2);
//			presupuestoRepository.save(presupuesto3);
//			presupuestoRepository.save(presupuesto4);
//			presupuestoRepository.save(presupuesto5);
//			presupuestoRepository.save(presupuesto6);
//
//			flexService.save(flex1);
//			flexService.save(flex2);
//			flexService.save(flex3);
//			flexService.save(flex4);
//		});
//
//	}

}
