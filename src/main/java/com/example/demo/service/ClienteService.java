package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface ClienteService <Cliente>{
	
	Cliente create(Cliente c);
	Cliente update(Cliente c);
	void delete(Long id);
	Optional<Cliente> read(Long id);
	List<Cliente> readAll();

}
