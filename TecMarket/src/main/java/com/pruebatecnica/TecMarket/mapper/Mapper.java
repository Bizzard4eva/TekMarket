package com.pruebatecnica.TecMarket.mapper;

import com.pruebatecnica.TecMarket.entity.DetalleVenta;
import com.pruebatecnica.TecMarket.entity.Dto.DetalleVentaResponse;
import com.pruebatecnica.TecMarket.entity.Dto.VentaResponse;
import com.pruebatecnica.TecMarket.entity.Venta;

public class Mapper {

    private static DetalleVentaResponse toResponse(DetalleVenta detalle) {
        if(detalle == null) return null;

        return  DetalleVentaResponse.builder()
                .id(detalle.getId())
                .producto(detalle.getProducto().getNombre())
                .cantidad(detalle.getCantidad())
                .subtotal(detalle.getSubtotal())
                .build();
    }
    public static VentaResponse toResponse(Venta venta) {
        if(venta == null) return null;

        var detalles = venta.getDetalles().stream()
                .map(Mapper::toResponse)
                .toList();
        return VentaResponse.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .idSucursal(venta.getSucursal().getId())
                .detalles(detalles)
                .total(venta.getTotal())
                .build();
    }

}
