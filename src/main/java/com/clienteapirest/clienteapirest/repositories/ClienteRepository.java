package com.clienteapirest.clienteapirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;

import com.clienteapirest.clienteapirest.models.Cliente;

//@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
}
