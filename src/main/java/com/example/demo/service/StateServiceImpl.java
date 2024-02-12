package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.StateDto;
import com.example.demo.entity.State;
import com.example.demo.repository.StateRepository;

@Service
public class StateServiceImpl implements StateService {

	@Autowired
	private StateRepository stateRepository;
	
	@Override
	public StateDto createState(StateDto stateDto) {
	    State state = new State();
	    state.setName(stateDto.getName());
	    // Save the state entity
	    state = stateRepository.save(state);
	    // Set the ID of the StateDto after the entity is saved
	    stateDto.setId(state.getId());
	    return stateDto;
	}


	@Override
	public StateDto getStateById(Long id) {
		// TODO Auto-generated method stub
		State state = stateRepository.findById(id).orElse(null);
		if(state == null)
		{return null; }
		StateDto stateDto = new StateDto();
		stateDto.setId(state.getId());
		stateDto.setName(state.getName());
		
		return stateDto;
	}
	@Override
    public StateDto updateState(Long id, StateDto stateDto) {
        State state = stateRepository.findById(id).orElse(null);
        if (state == null) {
            return null;
        }
        state.setName(stateDto.getName());
        // Update other properties as needed
        stateRepository.save(state);
        return stateDto;
    }

    @Override
    public boolean deleteState(Long id) {
        if (stateRepository.existsById(id)) {
            stateRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
