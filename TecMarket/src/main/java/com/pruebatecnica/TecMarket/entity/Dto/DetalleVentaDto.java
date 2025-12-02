package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class DetalleVentaDto {

    private Long id;
    private String producto;
    private Integer cantidad;
//    private Double precio;
    private Double subtotal;
}
