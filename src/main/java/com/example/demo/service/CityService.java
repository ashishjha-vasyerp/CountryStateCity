package com.example.demo.service;

import com.example.demo.dto.CityDto;

public interface CityService  {
	
	CityDto createCity(CityDto cityDto);
	CityDto getCityByID(Long id);
	CityDto updateCity(Long id, CityDto cityDto);
    boolean deleteCity(Long id);
}
