package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StateDto;
import com.example.demo.service.StateService;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @PostMapping("/create")
    public ResponseEntity<StateDto> createState(@RequestBody StateDto stateDto) {
        StateDto createdState = stateService.createState(stateDto);
        return new ResponseEntity<>(createdState, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StateDto> getStateById(@PathVariable Long id) {
        StateDto stateDto = stateService.getStateById(id);
        if (stateDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(stateDto, HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<StateDto> updateState(@PathVariable Long id, @RequestBody StateDto stateDto) {
        StateDto updatedState = stateService.updateState(id, stateDto);
        if (updatedState == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedState, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        if (stateService.deleteState(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    

}

