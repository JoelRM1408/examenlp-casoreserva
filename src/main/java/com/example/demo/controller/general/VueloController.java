package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Vuelo;
import com.example.demo.serviceImpl.VueloServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_VUELO;

@RestController
@RequestMapping(API_VUELO)
@CrossOrigin(origins = "http://localhost:4200/")
public class VueloController {
	@Autowired
	private VueloServiceImpl vueloServiceImpl;
	
	@GetMapping("/listarvuelo")
	public ResponseEntity<List<Vuelo>> listar() {
		try {
		      List<Vuelo> su = vueloServiceImpl.readAll();
		      if (su.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(su, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarvuelo")
    public ResponseEntity<Vuelo> crear(@Valid @RequestBody Vuelo vuelo){
        try {
        	Vuelo _vu = vueloServiceImpl.create(vuelo);
            return new ResponseEntity<Vuelo>(_vu, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarvuelo/{id}")
	public ResponseEntity<Vuelo> getVueloById(@PathVariable("id") Long id){
		Optional<Vuelo> carData = vueloServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Vuelo>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("eliminarvuelo/{id}")
	public ResponseEntity<Vuelo> delete(@PathVariable("id") Long id){
		try {
			vueloServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	@PutMapping("editarvuelo/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Vuelo vuelo){
		Optional<Vuelo> carData = vueloServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Vuelo dbvuelo = carData.get();
	    	dbvuelo.setFechasalida(vuelo.getFechasalida());
	    	dbvuelo.setHsalida(vuelo.getHsalida());
	    	dbvuelo.setFechallegada(vuelo.getFechallegada());
	    	dbvuelo.setHllegada(vuelo.getHllegada());
	    	dbvuelo.setOrigen(vuelo.getOrigen());
	    	dbvuelo.setDestino(vuelo.getDestino());
	    	dbvuelo.setNplazas(vuelo.getNplazas());
	    	//dbvuelo.setReservas(vuelo.getReservas());
	        return new ResponseEntity<Vuelo>(vueloServiceImpl.update(dbvuelo), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}
