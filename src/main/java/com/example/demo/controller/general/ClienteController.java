package com.example.demo.controller.general;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Cliente;
import com.example.demo.serviceImpl.ClienteServiceImpl;

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

import static com.example.demo.commons.GlobalConstans.API_CLIENTE;

@RestController
@RequestMapping(API_CLIENTE)
@CrossOrigin(origins = "http://localhost:4200/")
public class ClienteController {
	@Autowired
	private ClienteServiceImpl clienteServiceImpl;
	
	@GetMapping("/listarcliente")
	public ResponseEntity<List<Cliente>> listar() {
		try {
		      List<Cliente> cl = clienteServiceImpl.readAll();
		      if (cl.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cl, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarcliente")
    public ResponseEntity<Cliente> crear(@Valid @RequestBody Cliente cliente){
        try {
        	Cliente _cl = clienteServiceImpl.create(cliente);
            return new ResponseEntity<Cliente>(_cl, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarcliente/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Long id){
		Optional<Cliente> carData = clienteServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Cliente>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	@DeleteMapping("eliminarcliente/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable("id") Long id){
		try {
			clienteServiceImpl.delete(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	      }
	}
	
	@PutMapping("editarcliente/{id}")
	public ResponseEntity<?> updateCarrera(@PathVariable("id") Long id, @Valid @RequestBody Cliente cliente){
		Optional<Cliente> carData = clienteServiceImpl.read(id);
	      if (carData.isPresent()) {
	    	Cliente dbcliente = carData.get();
	    	dbcliente.setDni(cliente.getDni());
	    	dbcliente.setNombres(cliente.getNombres());
	    	dbcliente.setApellidos(cliente.getApellidos());
	    	dbcliente.setTelefono(cliente.getTelefono());
	    	dbcliente.setEmail(cliente.getEmail());
	    	//dbcliente.setReservas(cliente.getReservas());
	        return new ResponseEntity<Cliente>(clienteServiceImpl.update(dbcliente), HttpStatus.OK);
	      } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	      }
	}
}