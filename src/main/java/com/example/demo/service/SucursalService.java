package com.example.demo.service;

import java.util.List;
import java.util.Optional;

public interface SucursalService <Sucursal>{
	
	Sucursal create(Sucursal s);
	Sucursal update(Sucursal s);
	void delete(Long id);
	Optional<Sucursal> read(Long id);
	List<Sucursal> readAll();
	
}