package com.clienteapirest.clienteapirest.services;


import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clienteapirest.clienteapirest.ClienteConEdadCalculada;
import com.clienteapirest.clienteapirest.models.Cliente;
import com.clienteapirest.clienteapirest.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteConEdadCalculada> buscarTodosLosClientes(){
        List<ClienteConEdadCalculada> listaDeClientesConEdadCalculada = new ArrayList<>();
        List<Cliente> clientes = clienteRepository.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateActual = LocalDate.now();

        for (int i = 0; i < clientes.size(); i++) {
            Cliente clienteSinEdadCalculada = clientes.get(i);
            LocalDate fechaNacimiento = LocalDate.parse(clienteSinEdadCalculada.getFechaDeNacimiento(), formatter);
            Period edad = Period.between(fechaNacimiento, dateActual);

            ClienteConEdadCalculada cliente = new ClienteConEdadCalculada(clienteSinEdadCalculada.getId(), clienteSinEdadCalculada.getNombre(), clienteSinEdadCalculada.getApellido(), edad.getYears());
            listaDeClientesConEdadCalculada.add(i, cliente);
        }
        return listaDeClientesConEdadCalculada;
    }

    public ClienteConEdadCalculada buscarClientePorId(Long idCliente){
        //buscamos al cliente
        Optional<Cliente> clienteSinEdadCalculada = clienteRepository.findById(idCliente); 
        //calculamos la edad del cliente
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaNacimiento = LocalDate.parse(clienteSinEdadCalculada.get().getFechaDeNacimiento(), formatter);
        Period edad = Period.between(fechaNacimiento, LocalDate.now());
        //generamos un ClienteConEdadCalculada con los datos anteriores
        ClienteConEdadCalculada cliente = new ClienteConEdadCalculada(idCliente, clienteSinEdadCalculada.get().getNombre(), clienteSinEdadCalculada.get().getApellido(), edad.getYears());

            return cliente;
    }

    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
