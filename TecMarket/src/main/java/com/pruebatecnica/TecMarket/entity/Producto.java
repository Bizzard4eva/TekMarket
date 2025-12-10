package com.pruebatecnica.TecMarket.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
    @Column(nullable = false, length = 100)
    private String categoria;
    @Column(nullable = false)
    private Double precio;
    @Column(nullable = false)
    private Integer stock;
}
