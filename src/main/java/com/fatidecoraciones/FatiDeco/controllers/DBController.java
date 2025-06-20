package com.fatidecoraciones.FatiDeco.controllers;

import com.fatidecoraciones.FatiDeco.dtos.ResultadoDto;
import com.fatidecoraciones.FatiDeco.services.DBService;
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

    private final DBService dbService;

    public DBController(DBService dbService) {
        this.dbService = dbService;
    }

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

    @PostMapping("/carga/sistemas")
    public ResponseEntity<ResultadoDto> cargarCortEspeciales(@RequestParam("file") MultipartFile file) {
        try {
            ResultadoDto resultado = dbService.cargarDatosCortEspeciales(file);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (IOException | CsvException e) {
            ResultadoDto errorResultado = new ResultadoDto();
            errorResultado.getErrores().add("Error al cargar el archivo: " + e.getMessage());
            return new ResponseEntity<>(errorResultado, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/carga/acce")
    public ResponseEntity<ResultadoDto> cargarAcces(@RequestParam("file") MultipartFile file) {
        try {
            ResultadoDto resultado = dbService.cargarAcce(file);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (IOException | CsvException e) {
            ResultadoDto errorResultado = new ResultadoDto();
            errorResultado.getErrores().add("Error al cargar el archivo: " + e.getMessage());
            return new ResponseEntity<>(errorResultado, HttpStatus.BAD_REQUEST);
        }
    }
}