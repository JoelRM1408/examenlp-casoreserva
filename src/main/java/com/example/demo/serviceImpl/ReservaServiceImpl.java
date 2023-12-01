package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Reserva;
import com.example.demo.repository.ReservaRepository;
import com.example.demo.service.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService<Reserva>{
	
	@Autowired
	private ReservaRepository reservaRepository;

	@Override
	public Reserva create(Reserva r) {
		// TODO Auto-generated method stub
		return reservaRepository.save(r);
	}

	@Override
	public Reserva update(Reserva r) {
		// TODO Auto-generated method stub
		return reservaRepository.save(r);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		reservaRepository.deleteById(id);
	}

	@Override
	public Optional<Reserva> read(Long id) {
		// TODO Auto-generated method stub
		return reservaRepository.findById(id);
	}

	@Override
	public List<Reserva> readAll() {
		// TODO Auto-generated method stub
		return reservaRepository.findAll();
	}

}