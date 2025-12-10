package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Builder @Setter @Getter
@NoArgsConstructor @AllArgsConstructor
public class SucursalResponse {

    private Long id;
    private String nombre;
    private String direccion;
}
