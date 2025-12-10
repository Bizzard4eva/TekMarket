package com.pruebatecnica.TecMarket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nombre;
    @Column(nullable = false)
    private String direccion;
}
