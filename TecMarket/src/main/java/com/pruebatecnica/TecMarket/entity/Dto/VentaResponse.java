package com.pruebatecnica.TecMarket.entity.Dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class VentaResponse {

    private Long id;
    private LocalDate fecha;
    private String estado;
    private Long idSucursal;
    private List<DetalleVentaResponse> detalles;
    private Double total;
}
