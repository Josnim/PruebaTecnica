package com.example.pruebatecnica.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pruebatecnica.model.Cuenta;
import com.example.pruebatecnica.model.Movimiento;
import com.example.pruebatecnica.repository.MovimientoRepository;
import com.example.pruebatecnica.service.ICuentaService;
import com.example.pruebatecnica.service.IMovimientoService;
import com.example.pruebatecnica.utils.MovimientosUtils;


@Service
public class MovimientoServiceImpl implements IMovimientoService {

    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    ICuentaService cuentaService;

    @Override
    public Map<String, Object> createMovimiento(Movimiento movimiento) {

        try {
            Cuenta cuenta = cuentaService.getCuentaById(movimiento.getCuenta().getIdCuenta());
            double valorMovimiento = Math.abs(movimiento.getValor());
            List<Movimiento> movimientos;
            if (!cuenta.getClienteId().equals(null)) {
                if (movimiento.getValor() == 0) {
                    return MovimientosUtils.MakeResponse("ERROR", "No se puede hacer un movimeinto con valor de 0",
                            null);
                }
                if (valorMovimiento <= cuenta.getSaldoInicial()) {
                    if (movimiento.getValor() < 0) {
                        cuenta.setSaldoInicial(cuenta.getSaldoInicial() - valorMovimiento);

                    } else {
                        cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());

                    }
                    movimientos = cuenta.getMovimientos();
                    movimientos.add(movimiento);
                    cuenta.setMovimientos(movimientos);
                    cuentaService.updateCuentaMovimientos(cuenta, cuenta.getIdCuenta());
                    movimientoRepository.save(movimiento);
                    return MovimientosUtils.MakeResponse("SUCCES", "TRX EXITOSA", movimiento);
                }

            } else {
                return MovimientosUtils.MakeResponse("ERROR", "No se econtro una cuenta para registrar el movimiento",
                        null);
            }
        } catch (Exception e) {
            return MovimientosUtils.MakeResponse("ERROR", "Error inesperado",
                    e.getMessage());
        }
        return MovimientosUtils.MakeResponse("ERROR", "Error inesperado",
                null);
    }

    @Override
    public Map<String, Object> getMovimientoById(Long id) {
        Movimiento movimiento =  movimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrado"));
        return MovimientosUtils.MakeResponse("SUCCES", "TRX EXITOSA", movimiento);
    }

    @Override
    public Map<String, Object> getAllMovimientos() {
        List<Movimiento> movimientos =  movimientoRepository.findAll();
        return MovimientosUtils.MakeResponse("SUCCES", "TRX EXITOSA", movimientos);
    }

}
