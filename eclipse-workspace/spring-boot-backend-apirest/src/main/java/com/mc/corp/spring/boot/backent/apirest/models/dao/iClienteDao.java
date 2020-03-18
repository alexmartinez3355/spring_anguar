package com.mc.corp.spring.boot.backent.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;

public interface iClienteDao extends CrudRepository<Cliente, Long> {

}
