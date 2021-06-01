package com.cg.tca.services;

import java.util.List;

import com.cg.tca.entities.TimeCard;
import com.cg.tca.exception.ResourceNotFoundException;

public interface TimeCardService {

	TimeCard saveTimeEntry(TimeCard sup);

	boolean removeEntry(int timeCardId) throws ResourceNotFoundException;

	int updateEntries(int id, TimeCard tcard) throws ResourceNotFoundException;

	List<TimeCard> displayAll();

	TimeCard getTimeCard(Integer tcId);

}