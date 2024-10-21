package com.example.pruebatecnica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.pruebatecnica.model.Cuenta;


@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
}
