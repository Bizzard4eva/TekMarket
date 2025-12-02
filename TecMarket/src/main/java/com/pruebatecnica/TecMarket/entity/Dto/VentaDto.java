package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class VentaDto {

    private Long id;
    private LocalDate fecha;
    private String estado;
    // Sucursal
    private Long idSucursal;
    // Lista de Detalles
    private List<DetalleVentaDto> detalles;
    private Double total;
}
