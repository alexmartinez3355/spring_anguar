package com.mc.corp.spring.boot.backent.apirest.models.services;

import java.util.List;
import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;

public interface iClienteService {
	
	public List<Cliente> findAll();

}
