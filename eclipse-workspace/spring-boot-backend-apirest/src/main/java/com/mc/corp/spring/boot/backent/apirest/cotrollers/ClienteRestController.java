package com.mc.corp.spring.boot.backent.apirest.cotrollers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;
import com.mc.corp.spring.boot.backent.apirest.models.services.iClienteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private iClienteService cliente_service;

	// Metodo para consultar todos los clientes.
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return cliente_service.findAll();
	}

	// Método que devuelve los datos de un solo cliente. Se debe pasar el ID del
	// cliente a consultar.
	/*
	 * @GetMapping("/clientes/{id}")
	 * 
	 * @ResponseStatus(HttpStatus.OK) public Cliente show(@PathVariable Long id) {
	 * return cliente_service.findById(id); }
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();
		// Manejar errores del servidor por ejemplo que no se puede acceder a la base de
		// datos.
		try {
			cliente = cliente_service.findById(id);

		} catch (DataAccessException err) {
			response.put("Mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", err.getMessage().concat(": ").concat(err.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// Manejar errores al momento de consultar información.
		// Por ejemplo que no se encuentra el cliente con cierto ID.
		if (cliente == null) {
			response.put("mensaje", "El cliente ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	// Método que permite crear un nuevo cliente.
	/*
	 * @PostMapping("/clientes")
	 * 
	 * @ResponseStatus(HttpStatus.CREATED) public Cliente create(@RequestBody
	 * Cliente cliente) { return cliente_service.save(cliente); }
	 */
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente nuevo_cliente = null;
		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {	
			List<String> errores = result.getFieldErrors()
					.stream()
					.map(err -> "Error en el campo '"+err.getField()+"': "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errores );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			nuevo_cliente = cliente_service.save(cliente);
		} catch (DataAccessException error) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", error.getMessage().concat(": ").concat(error.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("mensaje", "El cliente ha sido creado con éxito!");
		response.put("cliente", nuevo_cliente);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Método que permite actualizar un cliente. Se debe pasar el ID del cliente a
	// actualizar.
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> actualizar(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Cliente cliente_actual = cliente_service.findById(id);
		Cliente cliente_actualizado = null;

		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {	
			List<String> errores = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+err.getField()+"' "+err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errores );
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (cliente_actual == null) {
			response.put("mensaje", "Error: no se puede editar, El cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			cliente_actual.setNombre(cliente.getNombre());
			cliente_actual.setApellido(cliente.getApellido());
			cliente_actual.setEmail(cliente.getEmail());
			cliente_actual.setFecha(cliente.getFecha());

			cliente_actualizado = cliente_service.save(cliente_actual);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El cliente ha sido actualizado con éxito!");
		response.put("cliente", cliente_actualizado);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	// Método que permite eliminar un cliente. Se debe pasar el ID del cliente a
	// eliminar.
	/*@DeleteMapping("/clientes/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		cliente_service.delete(id);
	}*/
	
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			cliente_service.delete(id);
			
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el cliente en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El cliente eliminado con éxito!");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
		
	}
	
}
