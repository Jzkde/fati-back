package com.fatidecoraciones.FatiDeco.dB;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductoDataInitializer {

//    private final ProductoService productoService;
//    private final MarcaService marcaService;
//
//    public ProductoDataInitializer(ProductoService productoService,
//                                   MarcaService marcaService) {
//
//        this.productoService = productoService;
//        this.marcaService = marcaService;
//    }
//
//    @Bean
//    public CommandLineRunner telaInitData() {
//        return (args -> {
//
//            // Accesorios
//            Producto rielEu1 = new Producto(
//                    "1L",
//                    "Riel Eu 160",
//                    10000.0,
//                    false
//            );
//            Producto rielEu2 = new Producto(
//                    "1L",
//                    "Riel Eu 200",
//                    20000,
//                    false
//            );
//            Producto rielAm1 = new Producto(
//                    "1L",
//                    "Riel Am 120",
//                    30000,
//                    false
//            );
//            Producto rielAm2 = new Producto(
//                    "1L",
//                    "Riel Am 220",
//                    40000,
//                    false
//            );
//            Producto madera1 = new Producto(
//                    "0L",
//                    "Barral 33 240",
//                    50000,
//                    false
//            );
//            Producto madera2 = new Producto(
//                    "1L",
//                    "Barral 22 140",
//                    60000,
//                    false
//            );
//
//            // Telas
//            Producto voile1 = new Producto(
//                    "10L",
//                    "Voile Lino Portobello",
//                    33180.0,
//                    true
//            );
//            Producto voile2 = new Producto(
//                    "11L",
//                    "Voile Niza",
//                    50700.0,
//                    true
//            );
//            Producto madras = new Producto(
//                    "12L",
//                    "Madras Pesado",
//                    121230.0,
//                    true
//            );
//            Producto rafia = new Producto(
//                    "9L",
//                    "Rafia Liviana",
//                    82230.0,
//                    true
//            );
//            Producto conf = new Producto(
//                    "xxx",
//                    "Solo Confeccion",
//                    0,
//                    true
//            );
//
//
//            Marca freda = new Marca(
//                    "Freda Plast"
//            );
//            Marca murray = new Marca(
//                    "Murray Lea"
//            );
//            Marca sabel = new Marca(
//                    "SabelCort"
//            );
//            Marca sheffa = new Marca(
//                    "Sheffa"
//            );
//            Marca kovi = new Marca(
//                    "Kovi"
//            );
//            Marca nadie = new Marca(
//                    "XXX"
//            );
//
//            kovi.addMarcaProd(rafia);
//            kovi.addMarcaProd(voile2);
//            sheffa.addMarcaProd(madras);
//            sheffa.addMarcaProd(voile1);
//            freda.addMarcaProd(rielEu1);
//            freda.addMarcaProd(rielEu2);
//            sabel.addMarcaProd(rielAm1);
//            sabel.addMarcaProd(rielAm2);
//            murray.addMarcaProd(madera1);
//            murray.addMarcaProd(madera2);
//            nadie.addMarcaProd(conf);
//
//            marcaService.save(freda);
//            marcaService.save(murray);
//            marcaService.save(sabel);
//            marcaService.save(sheffa);
//            marcaService.save(kovi);
//            marcaService.save(nadie);
//
//            productoService.save(rielEu1);
//            productoService.save(rielEu2);
//            productoService.save(rielAm1);
//            productoService.save(rielAm2);
//            productoService.save(madera1);
//            productoService.save(madera2);
//
//            productoService.save(voile1);
//            productoService.save(voile2);
//            productoService.save(madras);
//            productoService.save(rafia);
//            productoService.save(conf);
//        });
//    }
}