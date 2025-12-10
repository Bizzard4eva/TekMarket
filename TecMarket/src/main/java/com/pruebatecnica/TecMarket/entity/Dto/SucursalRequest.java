package com.pruebatecnica.TecMarket.entity.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class SucursalRequest {

    @NotNull(message = "⚠️ Nombre requerido")
    private String nombre;
    @NotNull(message = "⚠️ Direccion requerida")
    private String direccion;
}
