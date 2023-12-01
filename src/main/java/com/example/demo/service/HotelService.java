package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface HotelService <Hotel>{
	
	Hotel create(Hotel h);
	Hotel update(Hotel h);
	void delete(Long id);
	Optional<Hotel> read(Long id);
	List<Hotel> readAll();
	
}
