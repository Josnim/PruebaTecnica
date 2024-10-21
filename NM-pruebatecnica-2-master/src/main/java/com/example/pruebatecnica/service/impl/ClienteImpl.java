package com.example.pruebatecnica.service.impl;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.model.Cliente;
import com.example.pruebatecnica.repository.ClienteRepository;
import com.example.pruebatecnica.service.ICliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service
public class ClienteImpl implements ICliente {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Cliente> getAllClientes(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente createCliente(Cliente cliente) {
        cliente.setEstado(true);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Cliente clienteNew = clienteRepository.save(cliente);
            String jsonString = objectMapper.writeValueAsString(clienteNew);
            rabbitTemplate.convertAndSend("myExchange", "myRoutingKey", jsonString);
            return clienteNew;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public Cliente getClienteById(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    @Override
    public Cliente updateCliente(Cliente cliente, Long id) {
        Cliente nuevoCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setNombre(nuevoCliente.getNombre());
        cliente.setGenero(nuevoCliente.getGenero());
        cliente.setEdad(nuevoCliente.getEdad());
        cliente.setIdentificacion(nuevoCliente.getIdentificacion());
        cliente.setDireccion(nuevoCliente.getDireccion());
        cliente.setTelefono(nuevoCliente.getTelefono());
        cliente.setContraseña(nuevoCliente.getContraseña());
        cliente.setEstado(nuevoCliente.getEstado());

        

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente deleteCliente(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        cliente.setEstado(false);

        return clienteRepository.save(cliente);
    }

}
