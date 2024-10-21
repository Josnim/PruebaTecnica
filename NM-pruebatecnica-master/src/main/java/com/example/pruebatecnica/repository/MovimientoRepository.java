package com.example.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebatecnica.model.Movimiento;


@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    
}
