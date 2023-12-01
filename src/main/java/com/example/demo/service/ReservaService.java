package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface ReservaService <Reserva>{
	
	Reserva create(Reserva r);
	Reserva update(Reserva r);
	void delete(Long id);
	Optional<Reserva> read(Long id);
	List<Reserva> readAll();
	
}
