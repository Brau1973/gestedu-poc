package com.example.pruebaconcepto.services;

import com.example.pruebaconcepto.dtos.CarreraDTO;
import com.example.pruebaconcepto.exceptions.ResourceNotFoundException;
import com.example.pruebaconcepto.models.Carrera;
import com.example.pruebaconcepto.repositories.CarreraRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<CarreraDTO> getAllCarreras() {
        return carreraRepository.findAll().stream()
                .map(carrera -> modelMapper.map(carrera, CarreraDTO.class))
                .collect(Collectors.toList());
    }

    public CarreraDTO getCarreraById(Long id) {
        return carreraRepository.findById(id)
                .map(carrera -> modelMapper.map(carrera, CarreraDTO.class))
                .orElseThrow(() -> new ResourceNotFoundException("Carrera not found with id " + id));
    }

    public CarreraDTO createCarrera(CarreraDTO carreraDto) {
        Carrera carrera = modelMapper.map(carreraDto, Carrera.class);
        Carrera savedCarrera = carreraRepository.save(carrera);
        return modelMapper.map(savedCarrera, CarreraDTO.class);
    }

    public CarreraDTO updateCarrera(Long id, CarreraDTO carreraDto) {
        return carreraRepository.findById(id)
                .map(existingCarrera -> {
                    existingCarrera.setNombre(carreraDto.getNombre());
                    Carrera updatedCarrera = carreraRepository.save(existingCarrera);
                    return modelMapper.map(updatedCarrera, CarreraDTO.class);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Carrera not found with id " + id));
    }

    public void deleteCarrera(Long id) {
        Carrera carrera = carreraRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carrera not found with id " + id));
        carreraRepository.delete(carrera);
    }
}