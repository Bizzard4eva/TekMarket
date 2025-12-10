package com.pruebatecnica.TecMarket.entity.Dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class ProductoRequest {

    @NotNull(message = "⚠️ Nombre del producto requerido")
    private String nombre;
    @NotNull(message = "⚠️ Categoria del producto requerida")
    private String categoria;
    @PositiveOrZero(message = "⚠️ Precio valido requerido")
    private Double precio;
    @PositiveOrZero(message = "⚠️ Stock valido requerido")
    private Integer stock;
}
