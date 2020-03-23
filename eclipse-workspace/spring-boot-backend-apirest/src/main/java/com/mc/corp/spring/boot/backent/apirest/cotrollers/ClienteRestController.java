package com.mc.corp.spring.boot.backent.apirest.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;
import com.mc.corp.spring.boot.backent.apirest.models.services.iClienteService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private iClienteService cliente_service;
	
	// Metodo para consultar todos los clientes.
	@GetMapping("/clientes")
	public List<Cliente> index(){
		return cliente_service.findAll();
	}
	
	// Método que devuelve los datos de un solo cliente. Se debe pasar el ID del cliente a consultar.
	@GetMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente show(@PathVariable Long id) {
		return cliente_service.findById(id);
	}
	
	// Método que permite crear un nuevo cliente.
	@PostMapping("/clientes")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		return cliente_service.save(cliente);
	}
	
	// Método que permite actualizar un cliente. Se debe pasar el ID del cliente a actualizar.
	@PutMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente actualizar(@RequestBody Cliente cliente, @PathVariable Long id) {
		Cliente cliente_actual = cliente_service.findById(id);
		
		cliente_actual.setNombre(cliente.getNombre());
		cliente_actual.setApellido(cliente.getApellido());
		cliente_actual.setEmail(cliente.getEmail());
		
		return cliente_service.save(cliente_actual);
	}
	
	// Método que permite eliminar un cliente. Se debe pasar el ID del cliente a eliminar.
	@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		cliente_service.delete(id);
	}
}
