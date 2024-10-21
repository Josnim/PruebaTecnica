package com.example.pruebatecnica.service;

import java.util.List;

import com.example.pruebatecnica.model.Cuenta;

public interface ICuentaService {

    Cuenta createCuenta(Cuenta cuenta);

    Cuenta getCuentaById(Long id);

    List<Cuenta> getAllCuentas();

    Cuenta updateCuenta(Cuenta cuenta, Long id);

    Cuenta updateCuentaMovimientos(Cuenta cuenta, Long id);

    Cuenta deleteCuenta(Long id);

}
