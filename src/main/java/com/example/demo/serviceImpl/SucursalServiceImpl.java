package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Sucursal;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.service.SucursalService;

@Service
public class SucursalServiceImpl implements SucursalService<Sucursal>{
	
	@Autowired
	private SucursalRepository sucursalRepository;

	@Override
	public Sucursal create(Sucursal s) {
		// TODO Auto-generated method stub
		return sucursalRepository.save(s);
	}

	@Override
	public Sucursal update(Sucursal s) {
		// TODO Auto-generated method stub
		return sucursalRepository.save(s);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		sucursalRepository.deleteById(id);
	}

	@Override
	public Optional<Sucursal> read(Long id) {
		// TODO Auto-generated method stub
		return sucursalRepository.findById(id);
	}

	@Override
	public List<Sucursal> readAll() {
		// TODO Auto-generated method stub
		return sucursalRepository.findAll();
	}

}