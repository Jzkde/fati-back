package com.fatidecoraciones.pedidos.controllers;

import com.fatidecoraciones.pedidos.services.DBService;
import com.opencsv.exceptions.CsvException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> backupDatabase() {
        try {
            dbService.backupDatabase();
            return new ResponseEntity<>("Copia de serguridad EXITOSA.", HttpStatus.OK);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Copia de seguridad FALLO: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/carga/flex")
    public ResponseEntity<String> cargarFlex(@RequestParam("file") MultipartFile file) {
        try {
            dbService.cargarDatosFlex(file);
            return new ResponseEntity<>("Datos cargados exitosamente.", HttpStatus.OK);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al cargar los datos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/carga/royal")
    public ResponseEntity<String> cargarRC(@RequestParam("file") MultipartFile file) {
        try {
            dbService.cargarDatosRC(file);
            return new ResponseEntity<>("Datos cargados exitosamente.", HttpStatus.OK);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error al cargar los datos: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}