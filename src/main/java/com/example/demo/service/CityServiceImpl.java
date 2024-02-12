package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CityDto;
import com.example.demo.dto.StateDto;
import com.example.demo.entity.City;
import com.example.demo.repository.CityRepository;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CityDto createCity(CityDto cityDto) {
        City city = modelMapper.map(cityDto, City.class);
        city = cityRepository.save(city);
        return modelMapper.map(city, CityDto.class);
    }

    @Override
    public CityDto getCityByID(Long id) {
        City city = cityRepository.findById(id).orElse(null);
        return city != null ? modelMapper.map(city, CityDto.class) : null;
    }

    @Override
    public CityDto updateCity(Long id, CityDto cityDto) {
        City existingCity = cityRepository.findById(id).orElse(null);
        if (existingCity != null) {
            existingCity.setName(cityDto.getName());
            // Update other properties as needed
            cityRepository.save(existingCity);
            return modelMapper.map(existingCity, CityDto.class);
        }
        return null;
    }

    @Override
    public boolean deleteCity(Long id) {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<CityDto> getAllCities() {
        List<City> cities = cityRepository.findAll();
        return cities.stream()
                     .map(this::convertToDto)
                     .collect(Collectors.toList());
    }

    private CityDto convertToDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);
        // Map the associated country details if present
        if (city.getState() !=null)
        {
        	cityDto.setState(modelMapper.map(cityDto.getState(), StateDto.class));
        }
        return cityDto;
    }
}
