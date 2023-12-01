package com.example.demo.controller.general;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Reserva;
import com.example.demo.serviceImpl.ReservaServiceImpl;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.example.demo.commons.GlobalConstans.API_RESERVA;;

@RestController
@RequestMapping(API_RESERVA)
@CrossOrigin(origins = "http://localhost:4200/")
public class ReservaController {
	@Autowired
	private ReservaServiceImpl reservaServiceImpl;
	
	@GetMapping("/listareserva")
	public ResponseEntity<List<Reserva>> listar() {
		try {
		      List<Reserva> res = reservaServiceImpl.readAll();
		      if (res.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(res, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertareserva")
    public ResponseEntity<Reserva> crear(@Valid @RequestBody Reserva reserva){
        try {
        	Reserva _res = reservaServiceImpl.create(reserva);
            return new ResponseEntity<Reserva>(_res, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscareserva/{id}")
	public ResponseEntity<Reserva> getReservaById(@PathVariable("id") Long id){
		Optional<Reserva> carData = reservaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Reserva>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	/*
	@GetMapping("/buscarlibrosidioma/{idioma}")
	public ResponseEntity<List<Libro>> getLibroByIdioma(@PathVariable("idioma") String idioma){
		List <Libro> libro = libroServiceImpl.searchLibroidioma(idioma);
	    if (libro!=null ) {
	      return new ResponseEntity<List<Libro>>(libro, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@GetMapping("/buscarlibrostitulo/{titulo}")
	public ResponseEntity<List<Libro>> getLibroByTitulo(@PathVariable("titulo") String titulo){
		List <Libro> libro = libroServiceImpl.searchLibrotitulo(titulo);
	    if (libro!=null ) {
	      return new ResponseEntity<List<Libro>>(libro, HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}*/
	
	
	@DeleteMapping("/eliminareserva/{id}")
	public ResponseEntity<Reserva> delete(@PathVariable("id") Long id){
		try {
			reservaServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	@PutMapping("/editareserva/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Reserva reserva){
		Optional<Reserva> carData = reservaServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Reserva dbreserva = carData.get();
	        dbreserva.setClase(reserva.getClase());
	        dbreserva.setVuelo(reserva.getVuelo());
	        dbreserva.setCliente(reserva.getCliente());
	        dbreserva.setHotel(reserva.getHotel());
	        dbreserva.setSucursal(reserva.getSucursal());
	        
	        return new ResponseEntity<Reserva>(reservaServiceImpl.update(dbreserva), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}