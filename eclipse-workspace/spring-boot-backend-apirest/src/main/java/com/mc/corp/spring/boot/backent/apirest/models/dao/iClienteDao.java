package com.mc.corp.spring.boot.backent.apirest.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.corp.spring.boot.backent.apirest.models.entity.Cliente;

public interface iClienteDao extends JpaRepository<Cliente, Long> {

}
