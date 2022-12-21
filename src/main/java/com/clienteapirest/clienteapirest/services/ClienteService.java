package com.clienteapirest.clienteapirest.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.clienteapirest.clienteapirest.models.Cliente;
import com.clienteapirest.clienteapirest.repositories.ClienteRepository;

public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodosLosClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarClientePorId(Long idCliente){
        return clienteRepository.findById(idCliente);
    }

    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
