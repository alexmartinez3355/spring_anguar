package com.mc.corp.spring.boot.backent.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mc.corp.spring.boot.backent.apirest.models.dao.iClienteDao;
import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;

@Service
public class ClienteServicesImpl implements iClienteService {

	@Autowired
	private iClienteDao cliente_dao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) cliente_dao.findAll();
	}
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return cliente_dao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return cliente_dao.save(cliente);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		cliente_dao.deleteById(id);
	}

}
