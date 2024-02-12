package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CountryDto;
import com.example.demo.entity.Country;
import com.example.demo.repository.CountryRepository;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	public CountryDto createCountry(CountryDto countryDto) {
	    Country country = new Country();
	    country.setName(countryDto.getName());
	    // Save the country entity
	    country = countryRepository.save(country);
	    // Set the ID of the CountryDto after the entity is saved
	    countryDto.setId(country.getId());
	    return countryDto;
	}

	@Override
	public CountryDto getCountry(Long id) {
		// TODO Auto-generated method stub
		Country country = countryRepository.findById(id).orElse(null);
		if(country == null) {
		return null;}
		CountryDto countryDto = new CountryDto();
		countryDto.setId(country.getId());
		countryDto.setName(country.getName());
		return countryDto;
	}
	@Override
    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        Country country = countryRepository.findById(id).orElse(null);
        if (country == null) {
            return null;
        }
        country.setName(countryDto.getName());
        countryRepository.save(country);
        return countryDto;
    }

    @Override
    public boolean deleteCountry(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
