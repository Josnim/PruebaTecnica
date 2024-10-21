package com.example.pruebatecnica.controller;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pruebatecnica.model.Cuenta;
import com.example.pruebatecnica.service.ICuentaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cuenta")
public class CuentaController implements Serializable {
     @Autowired
    ICuentaService cuentaService;
    
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cliente) {
       return cuentaService.createCuenta(cliente);
    }

    @GetMapping("/{id}")
    public Cuenta getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id);
    }

    @PutMapping("/{id}")
    public Cuenta updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaActualizada) {
       return cuentaService.updateCuenta(cuentaActualizada, id);
    }

    @DeleteMapping("/{id}")
    public Cuenta deleteCuenta(@PathVariable Long id) {
      return cuentaService.deleteCuenta(id);
    }
    
}
