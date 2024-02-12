package com.example.demo.controller;

import java.util.List;

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

    @GetMapping("/all")
    public ResponseEntity<List<StateDto>> getAllStates() {
        List<StateDto> states = stateService.getAllStates();
        return ResponseEntity.ok(states);
    }
    
    @PostMapping("/create")
    public ResponseEntity<StateDto> createState(@RequestBody StateDto stateDto) {
        StateDto createdState = stateService.createState(stateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdState);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<StateDto> getStateById(@PathVariable Long id) {
        StateDto stateDto = stateService.getStateById(id);
        return stateDto != null ? ResponseEntity.ok(stateDto) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StateDto> updateState(@PathVariable Long id, @RequestBody StateDto stateDto) {
        StateDto updatedState = stateService.updateState(id, stateDto);
        return updatedState != null ? ResponseEntity.ok(updatedState) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteState(@PathVariable Long id) {
        return stateService.deleteState(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}