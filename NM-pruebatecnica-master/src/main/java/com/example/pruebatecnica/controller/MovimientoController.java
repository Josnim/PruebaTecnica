package com.example.pruebatecnica.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebatecnica.model.Movimiento;
import com.example.pruebatecnica.service.IMovimientoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/movimiento")
public class MovimientoController implements Serializable {

    @Autowired
    IMovimientoService movimientoService;
    
    @GetMapping
    public Map<String, Object> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    @PostMapping
    public Map<String, Object> createMovimiento(@RequestBody Movimiento movimiento) {
       return movimientoService.createMovimiento(movimiento);
    }

    @GetMapping("/{id}")
    public Map<String, Object> getMovimientosById(@PathVariable Long id) {
        return movimientoService.getMovimientoById(id);
    }


    
}
