package com.example.pruebatecnica.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class Persona {
    @Column(unique = true)
    private String identificacion;

    private String nombre;
    private String genero;
    private int edad;

    private String direccion;
    private String telefono;

}
