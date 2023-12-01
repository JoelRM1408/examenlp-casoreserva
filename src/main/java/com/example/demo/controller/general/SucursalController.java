package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sucursal;
import com.example.demo.serviceImpl.SucursalServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_SUCURSAL;

@RestController
@RequestMapping(API_SUCURSAL)
@CrossOrigin(origins = "http://localhost:4200/")
public class SucursalController {
	@Autowired
	private SucursalServiceImpl sucursalServiceImpl;
	
	@GetMapping("/listarsucursal")
	public ResponseEntity<List<Sucursal>> listar() {
		try {
		      List<Sucursal> su = sucursalServiceImpl.readAll();
		      if (su.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(su, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarsucursal")
    public ResponseEntity<Sucursal> crear(@Valid @RequestBody Sucursal sucursal){
        try {
        	Sucursal _su = sucursalServiceImpl.create(sucursal);
            return new ResponseEntity<Sucursal>(_su, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarsucursal/{id}")
	public ResponseEntity<Sucursal> getSucursalById(@PathVariable("id") Long id){
		Optional<Sucursal> carData = sucursalServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Sucursal>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("eliminarsucursal/{id}")
	public ResponseEntity<Sucursal> delete(@PathVariable("id") Long id){
		try {
			sucursalServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	@PutMapping("editarsucursal/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Sucursal sucursal){
		Optional<Sucursal> carData = sucursalServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Sucursal dbsucursal = carData.get();
	    	dbsucursal.setDireccion(sucursal.getDireccion());
	    	dbsucursal.setLocalidad(sucursal.getLocalidad());
	    	dbsucursal.setProvincia(sucursal.getProvincia());
	    	dbsucursal.setTelefono(sucursal.getTelefono());
	    	//dbsucursal.setReservas(sucursal.getReservas());
	        return new ResponseEntity<Sucursal>(sucursalServiceImpl.update(dbsucursal), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}