package com.example.pruebatecnica.service;

import java.util.Map;

import com.example.pruebatecnica.model.Movimiento;

public interface IMovimientoService {

    Map<String, Object> createMovimiento(Movimiento movimiento);

    Map<String, Object> getMovimientoById(Long id);

    Map<String, Object>  getAllMovimientos();
}
