package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class ProductoDto {

    private Long id;
    private String nombre;
    private String categoria;
    private Double precio;
    private Integer stock;
}
