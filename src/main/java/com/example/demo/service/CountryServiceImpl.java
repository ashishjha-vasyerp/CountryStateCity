package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CountryDto;
import com.example.demo.entity.Country;
import com.example.demo.entity.State;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.StateRepository;

@Service
public class CountryServiceImpl implements CountryService {
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private StateRepository stateRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        country = countryRepository.save(country);
        return modelMapper.map(country, CountryDto.class);
    }
    
    @Override
    public CountryDto getCountry(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        return optionalCountry.map(country -> modelMapper.map(country, CountryDto.class)).orElse(null);
    }
    
    @Override
    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            country.setName(countryDto.getName());
            countryRepository.save(country);
            return modelMapper.map(country, CountryDto.class);
        }
        return null;
    }
    
    @Override
    public boolean deleteCountry(Long id) {
        if (countryRepository.existsById(id)) {
            countryRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    @Override
    public boolean appendStateToCountry(Long countryId, Long stateId) {
        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        Optional<State> optionalState = stateRepository.findById(stateId);
        
        if (optionalCountry.isPresent() && optionalState.isPresent()) {
            Country country = optionalCountry.get();
            State state = optionalState.get();
            country.getStates().add(state);
            state.setCountry(country);
            countryRepository.save(country);
            return true;
        }
        return false;
    }
    
    @Override
    public List<CountryDto> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream()
                       .map(country -> modelMapper.map(country, CountryDto.class))
                       .collect(Collectors.toList());
    }
}
