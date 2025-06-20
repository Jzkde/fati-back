package com.fatidecoraciones.FatiDeco.services;

import com.fatidecoraciones.FatiDeco.dtos.ResultadoDto;
import com.fatidecoraciones.FatiDeco.models.CortEspeciales;
import com.fatidecoraciones.FatiDeco.models.Marca;
import com.fatidecoraciones.FatiDeco.models.Producto;
import com.fatidecoraciones.FatiDeco.models.Sistema;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DBService {

    private static final String SCRIPT_PATH = "123.bat";

    private final CortEspecialesService cortEspecialesService;
    private final MarcaService marcaService;
    private final ProductoService productoService;
    private final SistemaService sistemaService;

    public DBService(CortEspecialesService cortEspecialesService,
                     MarcaService marcaService,
                     ProductoService productoService,
                     SistemaService sistemaService) {

        this.cortEspecialesService = cortEspecialesService;
        this.marcaService = marcaService;
        this.productoService = productoService;
        this.sistemaService = sistemaService;
    }

    public void backupDatabase() throws IOException, InterruptedException {
        File scriptFile = new File(SCRIPT_PATH);

        ProcessBuilder processBuilder = new ProcessBuilder(scriptFile.getAbsolutePath());
        processBuilder.redirectErrorStream(true); // Combina la salida estándar y de error

        Process process = processBuilder.start();

        // Captura y maneja la salida estándar y de error del proceso
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); // Puedes usar un logger aquí
            }
        }

        int processComplete = process.waitFor();

        if (processComplete == 0) {
            System.out.println("Backup Ok.");
        } else {
            System.out.println("Backup Error.");
        }
    }

    public ResultadoDto cargarDatosCortEspeciales(MultipartFile file) throws IOException, CsvException {
        List<CortEspeciales> listaCortEspeciales = new ArrayList<>();
        ResultadoDto resultado = new ResultadoDto();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> datos = reader.readAll();
            int filaIndex = 1;
            for (String[] fila : datos) {

                // Columnas: nombre, precio, esTela, Sistema, marca
                if (fila.length >= 5) {
                    CortEspeciales cortEspeciales = new CortEspeciales();

                    // Carga Nombre
                    cortEspeciales.setTela(fila[0]);

                    // Carga Precio
                    try {
                        cortEspeciales.setPrecio(Double.parseDouble(fila[1]));
                    } catch (NumberFormatException e) {
                        resultado.getErrores().add("Fila " + filaIndex + ": Error al parsear el precio: " + fila[1]);
                        filaIndex++;
                        continue;
                    }

                    // Carga si en "tela" o no
                    cortEspeciales.setEsTela(Boolean.parseBoolean(fila[2]));

                    // Carga Sistema
                    String nombreSistema = fila[3].trim();
                    Sistema sistema = sistemaService.findBySistema(nombreSistema);
                    if (sistema == null) {
                        resultado.getErrores().add("Fila " + filaIndex + ": Sistema inválido: " + nombreSistema);
                        filaIndex++;
                        continue;
                    }

                    // Carga Marca
                    String nombreMarca = fila[4].trim();
                    Marca marca = marcaService.findByMarca(nombreMarca);
                    if (marca == null) {
                        resultado.getErrores().add("Fila " + filaIndex + ": Marca no encontrada: " + nombreMarca);
                        filaIndex++;
                        continue;
                    }

                    // Asocia la marca con la cortina
                    cortEspeciales.setMarca(marca);
                    marca.addMarcaEsp(cortEspeciales);

                    cortEspeciales.setSistema(sistema);
                    sistema.addCortEspeciales(cortEspeciales);

                    listaCortEspeciales.add(cortEspeciales);

                } else {
                    resultado.getErrores().add("Fila " + filaIndex + ": Fila incompleta: " + String.join(",", fila));
                }
                filaIndex++;
            }
        }

        // Guarda los listaCortEspeciales en la base de datos
        if (!listaCortEspeciales.isEmpty()) {
            cortEspecialesService.saveAll(listaCortEspeciales);
            resultado.setCantidadCargada(listaCortEspeciales.size());
        }

        return resultado;
    }


    public ResultadoDto cargarAcce(MultipartFile file) throws IOException, CsvException {
        List<Producto> productos = new ArrayList<>();
        ResultadoDto resultado = new ResultadoDto();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> datos = reader.readAll();
            int filaIndex = 1;
            for (String[] fila : datos) {

                // Columnas: nombre, precio, esTela, articulo, marca
                if (fila.length >= 5) {
                    Producto producto = new Producto();

                    // Carga el Articulo
                    producto.setArt(fila[0]);

                    // Carga Nombre
                    producto.setNombre(fila[1]);

                    // Carga precio
                    try {
                        producto.setPrecio(Double.parseDouble(fila[2]));
                    } catch (NumberFormatException e) {
                        resultado.getErrores().add("Fila " + filaIndex + ": Error al parsear el precio: " + fila[2]);
                        filaIndex++;
                        continue;
                    }

                    // Carga si es "tela" o no
                    producto.setEsTela(Boolean.parseBoolean(fila[3]));


                    // Carga la marca
                    String nombreMarca = fila[4].trim();
                    Marca marca = marcaService.findByMarca(nombreMarca);
                    if (marca == null) {
                        resultado.getErrores().add("Fila " + filaIndex + ": Marca no encontrada: " + nombreMarca);
                        filaIndex++;
                        continue;
                    }

                    // Asocia la marca con el producto
                    producto.setMarca(marca);
                    marca.addMarcaProd(producto);

                    productos.add(producto);
                } else {
                    resultado.getErrores().add("Fila " + filaIndex + ": Fila incompleta: " + String.join(",", fila));
                }
                filaIndex++;
            }
        }
        // Guarda los productos en la base de datos
        if (!productos.isEmpty()) {
            productoService.saveAll(productos);
            resultado.setCantidadCargada(productos.size());
        }
        return resultado;
    }
}