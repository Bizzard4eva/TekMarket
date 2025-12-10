package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DetalleVentaResponse {

    private Long id;
    private String producto;
    private Integer cantidad;
    private Double subtotal;
}