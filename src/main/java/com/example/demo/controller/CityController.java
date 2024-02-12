package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.CityDto;
import com.example.demo.service.CityService;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @PostMapping("/create")
    public ResponseEntity<CityDto> createCity(@RequestBody CityDto cityDto) {
        CityDto createdCity = cityService.createCity(cityDto);
        return new ResponseEntity<>(createdCity, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<CityDto> getCityById(@PathVariable Long id) {
        CityDto cityDto = cityService.getCityByID(id);
        if (cityDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @RequestBody CityDto cityDto) {
        CityDto updatedCity = cityService.updateCity(id, cityDto);
        if (updatedCity == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCity(@PathVariable Long id) {
        if (cityService.deleteCity(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    
}
