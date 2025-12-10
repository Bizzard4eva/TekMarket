package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductoResponse {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
}
