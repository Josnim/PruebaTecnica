package com.example.pruebatecnica.service;

import java.util.List;

import com.example.pruebatecnica.model.Cliente;


public interface ICliente {
    Cliente createCliente(Cliente cliente);
    Cliente getClienteById(Long id);
    List<Cliente> getAllClientes();
    Cliente updateCliente(Cliente cliente, Long id);
    Cliente deleteCliente(Long id);
}
