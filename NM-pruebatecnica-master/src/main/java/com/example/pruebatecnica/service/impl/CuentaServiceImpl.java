package com.example.pruebatecnica.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.model.Cuenta;
import com.example.pruebatecnica.repository.CuentaRepository;
import com.example.pruebatecnica.service.ICuentaService;

@Service
public class CuentaServiceImpl implements ICuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    @Override
    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrado"));
    }

    @Override
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    @Override
    public Cuenta updateCuenta(Cuenta cuenta, Long id) {
        Cuenta nuevaCuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

                nuevaCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
                nuevaCuenta.setTipoCuenta(cuenta.getTipoCuenta());
                nuevaCuenta.setSaldoInicial(cuenta.getSaldoInicial());
                nuevaCuenta.setEstado(cuenta.getEstado());

        return cuentaRepository.save(nuevaCuenta);
    }

    @Override
    public Cuenta updateCuentaMovimientos(Cuenta cuenta, Long id) {
        Cuenta nuevaCuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

                nuevaCuenta.setMovimientos(cuenta.getMovimientos());
                nuevaCuenta.setSaldoInicial(cuenta.getSaldoInicial());

        return cuentaRepository.save(nuevaCuenta);
    }

    @Override
    public Cuenta deleteCuenta(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrado"));

        cuenta.setEstado(false);

        return cuentaRepository.save(cuenta);
    }

}
