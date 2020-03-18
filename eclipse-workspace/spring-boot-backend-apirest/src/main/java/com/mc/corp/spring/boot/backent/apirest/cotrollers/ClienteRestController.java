package com.mc.corp.spring.boot.backent.apirest.cotrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;
import com.mc.corp.spring.boot.backent.apirest.models.services.iClienteService;

@RestController
@RequestMapping("/api")
public class ClienteRestController {
	
	@Autowired
	private iClienteService cliente_service;
	
	public List<Cliente> index(){
		return cliente_service.findAll();
	}
}
