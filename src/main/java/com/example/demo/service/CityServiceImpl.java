package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CityDto;
import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Override
	public CityDto createCity(CityDto cityDto) {
	    City city = new City();
	    city.setName(cityDto.getName());
	    // Save the city entity
	    city = cityRepository.save(city);
	    // Set the ID of the CityDto after the entity is saved
	    cityDto.setId(city.getId());
	    return cityDto;
	}


	@Override
	public CityDto getCityByID(Long id) {
		// TODO Auto-generated method stub
		City city = cityRepository.findById(id).orElse(null);
		if( city == null)
		{return null;}
		CityDto cityDto = new CityDto();
		cityDto.setId(city.getId());
		cityDto.setName(city.getName());
		return cityDto;
		
	}
	@Override
    public CityDto updateCity(Long id, CityDto cityDto) {
        City city = cityRepository.findById(id).orElse(null);
        if (city == null) {
            return null;
        }
        city.setName(cityDto.getName());
        // Update other properties as needed
        cityRepository.save(city);
        return cityDto;
    }

    @Override
    public boolean deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
