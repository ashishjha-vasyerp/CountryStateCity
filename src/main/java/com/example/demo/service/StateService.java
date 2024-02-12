package com.example.demo.service;

import com.example.demo.dto.StateDto;

public interface StateService {
	
	StateDto createState(StateDto stateDto);
	StateDto getStateById(Long id);
	StateDto updateState(Long id, StateDto stateDto);
    boolean deleteState(Long id);
}
