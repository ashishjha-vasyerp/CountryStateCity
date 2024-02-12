package com.example.demo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CountryDto;
import com.example.demo.dto.StateDto;
import com.example.demo.entity.State;
import com.example.demo.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StateDto createState(StateDto stateDto) {
        State state = modelMapper.map(stateDto, State.class);
        state = stateRepository.save(state);
        return modelMapper.map(state, StateDto.class);
    }

    @Override
    public StateDto getStateById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("State not found with id: " + id));
        return modelMapper.map(state, StateDto.class);
    }

    @Override
    public StateDto updateState(Long id, StateDto stateDto) {
        State existingState = stateRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("State not found with id: " + id));

        existingState.setName(stateDto.getName());
        // Update other properties as needed

        State updatedState = stateRepository.save(existingState);
        return modelMapper.map(updatedState, StateDto.class);
    }

    @Override
    public boolean deleteState(Long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<StateDto> getAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private StateDto convertToDto(State state) {
        StateDto stateDto = modelMapper.map(state, StateDto.class);
        if (state.getCountry() != null) {
            stateDto.setCountry(modelMapper.map(state.getCountry(), CountryDto.class));
        }
        return stateDto;
    }
}
