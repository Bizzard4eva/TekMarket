package com.pruebatecnica.TecMarket.entity.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class DetalleVentaRequest {

    @NotNull(message = "⚠️ Nombre del producto requerido")
    private String producto;
    @Positive(message = "⚠️ Cantidad del producto requerida")
    private Integer cantidad;
}
