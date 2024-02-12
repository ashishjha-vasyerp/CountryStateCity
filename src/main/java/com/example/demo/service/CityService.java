package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.CityDto;

public interface CityService  {
	
	CityDto createCity(CityDto cityDto);
	CityDto getCityByID(Long id);
	CityDto updateCity(Long id, CityDto cityDto);
    boolean deleteCity(Long id);
    List<CityDto> getAllCities();
}
