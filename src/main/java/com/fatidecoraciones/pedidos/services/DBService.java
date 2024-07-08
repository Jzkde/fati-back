package com.fatidecoraciones.pedidos.services;

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
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DBService {

    @Autowired
    FlexRepository flexRepository;

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUser;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    private static final String SCRIPT_PATH =   "123.bat";

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
                if (fila.length >= 2) { // Asegúrate de que haya al menos dos columnas
                    Flex producto = new Flex();
                    producto.setTela(fila[0]);
                    try {
                        producto.setPrecio(Double.parseDouble(fila[1]));
                    } catch (NumberFormatException e) {
                        System.err.println("Error al parsear el precio: " + fila[1]);
                        continue; // Salta esta fila si el precio no es válido
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