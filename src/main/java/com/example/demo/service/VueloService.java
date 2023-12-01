package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface VueloService <Vuelo>{
	
	Vuelo create(Vuelo v);
	Vuelo update(Vuelo v);
	void delete(Long id);
	Optional<Vuelo> read(Long id);
	List<Vuelo> readAll();
	
}