package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CountryDto;

public interface CountryService {
	
	CountryDto createCountry(CountryDto countryDto);
	CountryDto getCountry(Long id);
	CountryDto updateCountry(Long id, CountryDto countryDto);
	boolean deleteCountry(Long id);
	boolean appendStateToCountry(Long countryId, Long stateId);
	List<CountryDto> getAllCountries();
}
