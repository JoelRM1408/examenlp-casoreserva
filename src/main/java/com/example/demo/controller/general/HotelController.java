package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Hotel;
import com.example.demo.serviceImpl.HotelServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_HOTEL;;

@RestController
@RequestMapping(API_HOTEL)
@CrossOrigin(origins = "http://localhost:4200/")
public class HotelController {
	@Autowired
	private HotelServiceImpl hotelServiceImpl;
	
	@GetMapping("/listarhotel")
	public ResponseEntity<List<Hotel>> listar() {
		try {
		      List<Hotel> hot = hotelServiceImpl.readAll();
		      if (hot.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(hot, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarhotel")
    public ResponseEntity<Hotel> crear(@Valid @RequestBody Hotel hotel){
        try {
        	Hotel _hot = hotelServiceImpl.create(hotel);
            return new ResponseEntity<Hotel>(_hot, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarhotel/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable("id") Long id){
		Optional<Hotel> carData = hotelServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Hotel>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("eliminarhotel/{id}")
	public ResponseEntity<Hotel> delete(@PathVariable("id") Long id){
		try {
			hotelServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	@PutMapping("editarhotel/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Hotel hotel){
		Optional<Hotel> carData = hotelServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Hotel dbhotel = carData.get();
	    	dbhotel.setNombre(hotel.getNombre());
	    	dbhotel.setDireccion(hotel.getDireccion());
	    	dbhotel.setLocalidad(hotel.getLocalidad());
	    	dbhotel.setProvincia(hotel.getProvincia());
	    	dbhotel.setTelefono(hotel.getTelefono());
	    	dbhotel.setNestrellas(hotel.getNestrellas());
	    	//dbhotel.setReservas(hotel.getReservas());
	    	
	        return new ResponseEntity<Hotel>(hotelServiceImpl.update(dbhotel), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
