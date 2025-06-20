package com.fatidecoraciones.FatiDeco.dB;


import org.springframework.context.annotation.Configuration;

@Configuration
public class CortEspecialesDataInitializer {
//
//    private final CortEspecialesService cortEspecialesService;
//    private final MarcaService marcaService;
//
//    public CortEspecialesDataInitializer(CortEspecialesService cortEspecialesService,
//                                         MarcaService marcaService) {
//        this.cortEspecialesService = cortEspecialesService;
//        this.marcaService = marcaService;
//    }
//
//    @Bean
//    public CommandLineRunner cortEspecialesInitData() {
//        return (args -> {
//
//            //Marca
//            Marca flexcolor = new Marca(
//                    "FLEXCOLOR"
//            );
//            Marca royal = new Marca(
//                    "ROYALCORT"
//            );
//
//            //CortEspeciales
//            // Sistemas
//            CortEspeciales rollerFS1 = new CortEspeciales(
//                    "SISTEMA VTX15 - 32MM",
//                    46290.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerFS2 = new CortEspeciales(
//                    "SISTEMA VTX15 -38MM",
//                    50570.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerFS3 = new CortEspeciales(
//                    "SISTEMA VTX20 -45MM",
//                    55740.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales verticalFS = new CortEspeciales(
//                    "SISTEMA VERTICAL",
//                    48230.0,
//                    false,
//                    Sistema.VERTICALES
//            );
//            CortEspeciales dubaiFS = new CortEspeciales(
//                    "SISTEMA DUBAI COMPLETO CASETTE 100",
//                    95920.0,
//                    false,
//                    Sistema.DUBAI
//            );
//            CortEspeciales persianaFS = new CortEspeciales(
//                    "PERSIANA",
//                    0.0,
//                    false,
//                    Sistema.PERSIANA
//            );
//            CortEspeciales romanaFS = new CortEspeciales(
//                    "SISTEMA ROMANA CON TELA Y CADENA",
//                    107180.0,
//                    false,
//                    Sistema.ROMANA
//            );
//
//            // Adicionales
//            CortEspeciales adicionalF1 = new CortEspeciales(
//                    "Sin Adicionales",
//                    0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//            CortEspeciales adicionalF2 = new CortEspeciales(
//                    "Sop Intermedio",
//                    3520.0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//            CortEspeciales adicionalF3 = new CortEspeciales(
//                    "Sop Doble",
//                    14780.0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//
//            //Telas
//            CortEspeciales dubaiFT1 = new CortEspeciales(
//                    "ZAFIRO (ANCHO 2.80 MTS)",
//                    91910.0,
//                    true,
//                    Sistema.DUBAI
//            );
//            CortEspeciales dubaiFT2 = new CortEspeciales(
//                    "WOODGRAIN (ANCHO 2.80 MTS)",
//                    15685.0,
//                    true,
//                    Sistema.DUBAI
//            );
//            CortEspeciales persianaFT1 = new CortEspeciales(
//                    "ALUMINIO SOLIDO",
//                    92100.0,
//                    true,
//                    Sistema.PERSIANA
//            );
//            CortEspeciales persianaFT2 = new CortEspeciales(
//                    "ALUMINIO MICROPERFORADO",
//                    106760.0,
//                    true,
//                    Sistema.PERSIANA
//            );
//            CortEspeciales rollerFT1 = new CortEspeciales(
//                    "BLACKOUT VINILICO (Ancho 1.83 mts)",
//                    48310.0,
//                    true,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerFT2 = new CortEspeciales(
//                    "DIONE BLACKOUT (Ancho 2.49 mts)",
//                    66540.0,
//                    true,
//                    Sistema.ROLLER
//            );
//            CortEspeciales verticalFT1 = new CortEspeciales(
//                    "SCREEN BALI",
//                    84250.0,
//                    true,
//                    Sistema.VERTICALES
//            );
//            CortEspeciales verticalFT2 = new CortEspeciales(
//                    "CALGARY",
//                    52470.0,
//                    true,
//                    Sistema.VERTICALES
//            );
//
//            //Royal Cort
//            // Sistemas
//            CortEspeciales rollerRS1 = new CortEspeciales(
//                    "RC Sistema Roller 32Mm",
//                    34080.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerRS2 = new CortEspeciales(
//                    "RC Sistema Roller 38Mm",
//                    40070.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerRS3 = new CortEspeciales(
//                    "RC Sistema Roller 45Mm",
//                    53470.0,
//                    false,
//                    Sistema.ROLLER
//            );
//            CortEspeciales zebraRS = new CortEspeciales(
//                    "RC Sistema Zebra",
//                    95150.0,
//                    false,
//                    Sistema.ZEBRA
//            );
//
//            // Adicionales
//            CortEspeciales adicionalR1 = new CortEspeciales(
//                    "Sin Adicionales",
//                    0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//            CortEspeciales adicionalR2 = new CortEspeciales(
//                    "Sop Intermedio",
//                    7480.0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//            CortEspeciales adicionalR3 = new CortEspeciales(
//                    "Sop Doble",
//                    7790.0,
//                    false,
//                    Sistema.ADICIONAL
//            );
//
//            // Telas
//            CortEspeciales rollerRT1 = new CortEspeciales(
//                    "RC Blackout Premium  (Ancho 1.83 mts)",
//                    33180.0,
//                    true,
//                    Sistema.ROLLER
//            );
//            CortEspeciales rollerRT2 = new CortEspeciales(
//                    "RC Sunscreen 5% 2 (Ancho 2.49 mts)",
//                    50700.0,
//                    true,
//                    Sistema.ROLLER
//            );
//            CortEspeciales zebraRT1 = new CortEspeciales(
//                    "RC Zebra Capri  (Ancho 2.99 mts)",
//                    121230.0,
//                    true,
//                    Sistema.ZEBRA
//            );
//            CortEspeciales zebraRT2 = new CortEspeciales(
//                    "RC Zebra Cairo  (Ancho 2.59 mts)",
//                    82230.0,
//                    true,
//                    Sistema.ZEBRA
//            );
//
//            royal.addMarcaEsp(rollerRS1);
//            royal.addMarcaEsp(rollerRS2);
//            royal.addMarcaEsp(rollerRS3);
//            royal.addMarcaEsp(zebraRS);
//            royal.addMarcaEsp(adicionalR1);
//            royal.addMarcaEsp(adicionalR2);
//            royal.addMarcaEsp(adicionalR3);
//            royal.addMarcaEsp(rollerRT1);
//            royal.addMarcaEsp(rollerRT2);
//            royal.addMarcaEsp(zebraRT1);
//            royal.addMarcaEsp(zebraRT2);
//
//            flexcolor.addMarcaEsp(rollerFS1);
//            flexcolor.addMarcaEsp(rollerFS2);
//            flexcolor.addMarcaEsp(rollerFS3);
//            flexcolor.addMarcaEsp(verticalFS);
//            flexcolor.addMarcaEsp(dubaiFS);
//            flexcolor.addMarcaEsp(persianaFS);
//            flexcolor.addMarcaEsp(romanaFS);
//            flexcolor.addMarcaEsp(adicionalF1);
//            flexcolor.addMarcaEsp(adicionalF2);
//            flexcolor.addMarcaEsp(adicionalF3);
//            flexcolor.addMarcaEsp(dubaiFT1);
//            flexcolor.addMarcaEsp(dubaiFT2);
//            flexcolor.addMarcaEsp(persianaFT1);
//            flexcolor.addMarcaEsp(persianaFT2);
//            flexcolor.addMarcaEsp(verticalFT1);
//            flexcolor.addMarcaEsp(verticalFT2);
//            flexcolor.addMarcaEsp(rollerFT1);
//            flexcolor.addMarcaEsp(rollerFT2);
//
//            //Marca
//            marcaService.save(flexcolor);
//            marcaService.save(royal);
//
//            //Royal Cort
//            cortEspecialesService.save(rollerRS1);
//            cortEspecialesService.save(rollerRS2);
//            cortEspecialesService.save(rollerRS3);
//            cortEspecialesService.save(zebraRS);
//            cortEspecialesService.save(adicionalR1);
//            cortEspecialesService.save(adicionalR2);
//            cortEspecialesService.save(adicionalR3);
//            cortEspecialesService.save(rollerRT1);
//            cortEspecialesService.save(rollerRT2);
//            cortEspecialesService.save(zebraRT1);
//            cortEspecialesService.save(zebraRT2);
//
//            //CortEspeciales Color
//            cortEspecialesService.save(rollerFS1);
//            cortEspecialesService.save(rollerFS2);
//            cortEspecialesService.save(rollerFS3);
//            cortEspecialesService.save(verticalFS);
//            cortEspecialesService.save(dubaiFS);
//            cortEspecialesService.save(persianaFS);
//            cortEspecialesService.save(romanaFS);
//            cortEspecialesService.save(adicionalF1);
//            cortEspecialesService.save(adicionalF2);
//            cortEspecialesService.save(adicionalF3);
//            cortEspecialesService.save(dubaiFT1);
//            cortEspecialesService.save(dubaiFT2);
//            cortEspecialesService.save(persianaFT1);
//            cortEspecialesService.save(persianaFT2);
//            cortEspecialesService.save(verticalFT1);
//            cortEspecialesService.save(verticalFT2);
//            cortEspecialesService.save(rollerFT1);
//            cortEspecialesService.save(rollerFT2);
//        });
//    }
}