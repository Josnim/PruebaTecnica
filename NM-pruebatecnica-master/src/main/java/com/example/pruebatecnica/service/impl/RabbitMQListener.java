package com.example.pruebatecnica.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.pruebatecnica.model.Cliente;
import com.example.pruebatecnica.model.Cuenta;
import com.example.pruebatecnica.service.ICuentaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RabbitMQListener {
    @Autowired
    ICuentaService cuentaService;

    @RabbitListener(queues = "myQueueRB")
    public void listen(String message) {
        ObjectMapper om = new ObjectMapper();
        Cliente object;
        try {
            log.info(message);
            object = om.readValue(message, Cliente.class);
            Cuenta cuenta = new Cuenta();
            if (object.getEstado()) {
                cuenta.setNumeroCuenta("");
                cuenta.setTipoCuenta("");
                cuenta.setSaldoInicial(0.0);
                cuenta.setEstado(false);
                cuenta.setClienteId(object.getClienteId());
                cuentaService.createCuenta(cuenta);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}