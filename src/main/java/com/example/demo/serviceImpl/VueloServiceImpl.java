package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Vuelo;
import com.example.demo.repository.VueloRepository;
import com.example.demo.service.VueloService;

@Service
public class VueloServiceImpl implements VueloService<Vuelo>{
	
	@Autowired
	private VueloRepository vueloRepository;

	@Override
	public Vuelo create(Vuelo v) {
		// TODO Auto-generated method stub
		return vueloRepository.save(v);
	}

	@Override
	public Vuelo update(Vuelo v) {
		// TODO Auto-generated method stub
		return vueloRepository.save(v);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		vueloRepository.deleteById(id);
	}

	@Override
	public Optional<Vuelo> read(Long id) {
		// TODO Auto-generated method stub
		return vueloRepository.findById(id);
	}

	@Override
	public List<Vuelo> readAll() {
		// TODO Auto-generated method stub
		return vueloRepository.findAll();
	}

}