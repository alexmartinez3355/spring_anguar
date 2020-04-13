package com.mc.corp.spring.boot.backent.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;

public interface iClienteService {
	
	public List<Cliente> findAll();
	
	public Page<Cliente> findAll(Pageable pageable);
	
	// Metodo que realiza la busqueda de un cliente por medio de un ID. Recive un Id de cliente y debuelve un cliente.
	public Cliente findById(Long id);
	
	// Metodo que permite guardar. Resive un "cliente" a guardar y retorna el cliente guardado.
	public Cliente save(Cliente cliente);
	
	// Metodo que permite eliminar a un cliente. Recive un id de cliente y retorna un void (no retorna nada).
	public void delete(Long id);

}
