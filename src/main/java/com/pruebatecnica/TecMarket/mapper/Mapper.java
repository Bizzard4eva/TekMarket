package com.pruebatecnica.TecMarket.mapper;

import com.pruebatecnica.TecMarket.entity.Dto.DetalleVentaDto;
import com.pruebatecnica.TecMarket.entity.Dto.ProductoDto;
import com.pruebatecnica.TecMarket.entity.Dto.SucursalDto;
import com.pruebatecnica.TecMarket.entity.Dto.VentaDto;
import com.pruebatecnica.TecMarket.entity.Producto;
import com.pruebatecnica.TecMarket.entity.Sucursal;
import com.pruebatecnica.TecMarket.entity.Venta;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class Mapper {

    public static ProductoDto toDto(Producto producto) {
        if(producto == null) return null;

        return ProductoDto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .build();
    }

    public static  Producto toEntity(ProductoDto producto) {
        if(producto == null) return null;

        return Producto.builder()
                .id(producto.getId())
                .nombre(producto.getNombre())
                .categoria(producto.getCategoria())
                .precio(producto.getPrecio())
                .stock(producto.getStock())
                .build();
    }

    public static SucursalDto toDto(Sucursal sucursal) {
        if(sucursal == null) return null;

        return SucursalDto.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

    public static Sucursal toEntity(SucursalDto sucursal) {
        if(sucursal == null) return null;

        return Sucursal.builder()
                .id(sucursal.getId())
                .nombre(sucursal.getNombre())
                .direccion(sucursal.getDireccion())
                .build();
    }

    public static VentaDto toDto(Venta venta) {
        if(venta == null) return null;

        var detalles = venta.getDetalles().stream()
                .map(detalle ->
                        DetalleVentaDto.builder()
                                .id(detalle.getId())
                                .producto(detalle.getProducto().getNombre())
                                .cantidad(detalle.getCantidad())
                                .precio(detalle.getPrecio())
                                .subtotal(detalle.getPrecio() * detalle.getCantidad())
                                .build()
                )
                .toList();
        var total = detalles.stream()
                        .map(DetalleVentaDto::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDto.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .estado(venta.getEstado())
                .idSucursal(venta.getSucursal().getId())
                .detalles(detalles)
                .total(total)
                .build();
    }

}
