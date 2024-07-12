package com.fatidecoraciones.pedidos.services;

import com.fatidecoraciones.pedidos.enums.Sistema;
import com.fatidecoraciones.pedidos.models.Flex;
import com.fatidecoraciones.pedidos.repositories.FlexRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBService {

    private static final String SCRIPT_PATH = "123.bat";
    @Autowired
    FlexRepository flexRepository;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String dbUser;
    @Value("${spring.datasource.password}")
    private String dbPassword;

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
            System.out.println("Backup successful.");
        } else {
            System.out.println("Backup failed.");
        }
    }

    public void cargarDatosDesdeCSV(MultipartFile file) throws IOException, CsvException {
        List<Flex> productos = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> datos = reader.readAll();
            for (String[] fila : datos) {
                if (fila.length >= 4) { // columnas necesarias
                    Flex producto = new Flex();
                    producto.setTela(fila[0]);
                    // Carga precio
                    try {
                        producto.setPrecio(Double.parseDouble(fila[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear el precio: " + fila[1]);
                        continue;
                    }
                    // Carga si es "tela" o no
                    producto.setEsTela(Boolean.parseBoolean(fila[2]));
                    // Carga el sistema
                    try {
                        producto.setSistema(Sistema.valueOf(fila[3].toUpperCase()));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error al parsear el sistema: " + fila[3]);
                        continue;
                    }
                    productos.add(producto);
                } else {
                    System.err.println("Fila incompleta: " + String.join(",", fila));
                }
            }
        }

        if (!productos.isEmpty()) {
            flexRepository.saveAll(productos);
            System.out.println("Productos cargados exitosamente.");
        } else {
            System.out.println("No se cargaron productos. Verifica el archivo CSV.");
        }
    }
}