package com.clienteapirest.clienteapirest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clienteapirest.clienteapirest.models.Cliente;
import com.clienteapirest.clienteapirest.services.ClienteService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ClientesController {

    @Autowired
    private ClienteService service;

    /*
    Endpoint de prueba, puede utilizarlo para confirmar el correcto funcionamiento
     */
    @GetMapping(path = "/prueba")
    public String prueba() {
        return "OK";
    }


    /*
    GET /cliente/{id}
    Obtiene los datos del Cliente con id indicado en la URL
     */
    @GetMapping(path = "/cliente/{id}")
    public Optional<Cliente> getClienteById(@PathVariable Long id) throws Exception {
        return this.service.buscarClientePorId(id);
    }

    /*
    GET /cliente/all
    Obtiene todos los clientes registrados
     */
    @GetMapping(path = "/cliente/all")
    public List<Cliente> getAllCliente() throws Exception {
        return this.service.buscarTodosLosClientes();
    }

    /*
    POST /cliente
    Recibe en el body un objeto Cliente y lo persiste en la base de datos MySql
    Ejemplo:
    {
        "nombre": "Juan",
        "apellido": "Perez",
        "fechaDeNacimiento": "24/12/2022",
    }
     */
    @PostMapping(path = "/cliente")
    public Cliente createRealState(@RequestBody Cliente cliente) throws Exception {
        return this.service.crearCliente(cliente);
    }
}
