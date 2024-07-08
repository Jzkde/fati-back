package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.services.DBService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("db")
@CrossOrigin
public class DBController {
    @Autowired
    private DBService dbService;

    @GetMapping("/backup")
    public String backupDatabase() {
        try {
            dbService.backupDatabase();
            return "Backup successful.";
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Backup failed: " + e.getMessage();
        }
    }
    @PostMapping("/carga")
    public String cargarDatos(@RequestParam("file") MultipartFile file) {
        try {
            dbService.cargarDatosDesdeCSV(file);
            return "Datos cargados exitosamente.";
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return "Error al cargar los datos: " + e.getMessage();
        }
    }
}