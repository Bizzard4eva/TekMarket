package com.pruebatecnica.TecMarket.entity.Dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.List;

@Builder @Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class VentaRequest {

    @Positive(message = "⚠️ IDSucursal valida requerida")
    private Long idSucursal;
    @NotEmpty(message = "⚠️ Venta requiera al menos un producto")
    private List<DetalleVentaRequest> detalles;
}
