package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class SucursalDto {

    private Long id;
    private String nombre;
    private String direccion;
}
