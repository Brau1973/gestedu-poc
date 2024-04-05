package com.example.pruebaconcepto.controllers;

import com.example.pruebaconcepto.dtos.CarreraDTO;
import com.example.pruebaconcepto.services.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Autowired
    private CarreraService carreraService;

    @GetMapping()
    public ResponseEntity<List<CarreraDTO>> getAllCarreras() {
        List<CarreraDTO> carreras = carreraService.getAllCarreras();
        return ResponseEntity.ok().body(carreras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getCarreraById(@PathVariable("id") Long id) {
        CarreraDTO carrera = carreraService.getCarreraById(id);
        return ResponseEntity.ok().body(carrera);
    }

    @PostMapping()
    public ResponseEntity<CarreraDTO> createCarrera(@RequestBody CarreraDTO carreraDto) {
        CarreraDTO createdCarrera = carreraService.createCarrera(carreraDto);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
//                .buildAndExpand(createdCarrera.getId();
//        return ResponseEntity.created(location).body(createdCarrera);
        return ResponseEntity.ok().body(createdCarrera);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> updateCarrera(@PathVariable Long id, @RequestBody CarreraDTO carreraDto) {
        CarreraDTO updatedCarrera = carreraService.updateCarrera(id, carreraDto);
        return ResponseEntity.ok().body(updatedCarrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarrera(@PathVariable Long id) {
        carreraService.deleteCarrera(id);
        return ResponseEntity.noContent().build();
    }
}