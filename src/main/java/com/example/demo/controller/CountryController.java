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

import com.example.demo.dto.CountryDto;
import com.example.demo.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryService countryService;
	
	@PostMapping("/create")
	public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto){
		CountryDto createCountry = countryService.createCountry(countryDto);
		return new ResponseEntity<>(createCountry, HttpStatus.CREATED);
	}
	 @GetMapping("/get/{id}")
	    public ResponseEntity<CountryDto> getCountryById(@PathVariable Long id) {
	        CountryDto countryDto = countryService.getCountry(id);
	        if (countryDto == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(countryDto, HttpStatus.OK);
	    }
	 @PutMapping("/update/{id}")
	    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id, @RequestBody CountryDto countryDto) {
	        CountryDto updatedCountry = countryService.updateCountry(id, countryDto);
	        if (updatedCountry == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	        return new ResponseEntity<>(updatedCountry, HttpStatus.OK);
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
	        if (countryService.deleteCountry(id)) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
}
