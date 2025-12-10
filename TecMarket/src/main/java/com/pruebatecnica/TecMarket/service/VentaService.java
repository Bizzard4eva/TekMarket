package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.DetalleVenta;
import com.pruebatecnica.TecMarket.entity.Dto.DetalleVentaRequest;
import com.pruebatecnica.TecMarket.entity.Dto.VentaRequest;
import com.pruebatecnica.TecMarket.entity.Dto.VentaResponse;
import com.pruebatecnica.TecMarket.entity.Venta;
import com.pruebatecnica.TecMarket.exception.NotFoundException;
import com.pruebatecnica.TecMarket.mapper.Mapper;
import com.pruebatecnica.TecMarket.repository.ProductoRepository;
import com.pruebatecnica.TecMarket.repository.SucursalRepository;
import com.pruebatecnica.TecMarket.repository.VentaRepository;
import com.pruebatecnica.TecMarket.usecase.IVenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VentaService implements IVenta {

    private final VentaRepository ventaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<VentaResponse> listVentas() {
        return ventaRepository.findAll().stream()
                .map(Mapper::toResponse)
                .toList();
    }
    // En la capa service convertimos VentaResquet y DetalleVentaRequest -> Entity
    @Override
    public VentaResponse createVenta(VentaRequest venta) {
        var sucursal = sucursalRepository.findById(venta.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("⚠️ Sucursal no existe"));
        Venta registrada = Venta.builder()
                .fecha(LocalDate.now())
                .estado("PENDIENTE")
                .sucursal(sucursal)
                .build();
        var detalles = toEntity(venta.getDetalles(), registrada);
        registrada.setDetalles(detalles);
        registrada.setTotal(detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum());

        return Mapper.toResponse(ventaRepository.save(registrada));
    }
    @Override
    public VentaResponse updateVenta(Long idVenta, VentaRequest venta) {
        var registrada = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new NotFoundException("⚠️ Registro de venta no encontrada"));
        var sucursal = sucursalRepository.findById(venta.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("⚠️ Sucursal no existe"));
        var detalles =  toEntity(venta.getDetalles(), registrada);
        registrada.setSucursal(sucursal);
        registrada.setDetalles(detalles);
        registrada.setTotal(detalles.stream().mapToDouble(DetalleVenta::getSubtotal).sum());

        return Mapper.toResponse(ventaRepository.save(registrada));
    }
    @Override
    public void deleteVenta(Long idVenta) {
        if(ventaRepository.existsById(idVenta)) throw new NotFoundException("⚠️ Venta no encontrada");
        ventaRepository.deleteById(idVenta);
    }
    private List<DetalleVenta> toEntity(List<DetalleVentaRequest> detalles, Venta registrada) {
        return detalles.stream()
                .map(detalle -> {
                    var producto = productoRepository.findByNombre(detalle.getProducto())
                            .orElseThrow(() -> new NotFoundException("⚠️ Producto no existe"));
                    return DetalleVenta.builder()
                            .venta(registrada)
                            .producto(producto)
                            .cantidad(detalle.getCantidad())
                            .subtotal(producto.getPrecio() * detalle.getCantidad())
                            .build();
                }).collect(Collectors.toList());
    }

}
