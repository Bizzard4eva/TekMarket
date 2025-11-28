package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.DetalleVenta;
import com.pruebatecnica.TecMarket.entity.Dto.VentaDto;
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

@Service
@AllArgsConstructor
public class VentaService implements IVenta {

    private final VentaRepository ventaRepository;
    private final SucursalRepository sucursalRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<VentaDto> listVentas() {
        return ventaRepository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public VentaDto createVenta(VentaDto venta) {
        if(venta.getIdSucursal() == null) throw new RuntimeException("La venta no tiene una sucursal asignada!");
        if(venta.getDetalles() == null || venta.getDetalles().isEmpty())
            throw new RuntimeException("La venta no incluye ningun producto");
        var sucursal = sucursalRepository.findById(venta.getIdSucursal())
                .orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));

        Venta nuevaVenta = Venta.builder()
                .fecha(venta.getFecha() != null ?  venta.getFecha() : LocalDate.now())
                .estado(venta.getEstado() != null ? venta.getEstado() : "PENDIENTE")
                .sucursal(sucursal)
                .build();
        Venta ventaConId = ventaRepository.save(nuevaVenta);

        var detalles = venta.getDetalles().stream()
                .map(detalle -> {
                        var producto = productoRepository.findByNombre(detalle.getProducto())
                                .orElseThrow(()
                                        -> new NotFoundException("Producto no encontrado: " + detalle.getProducto()));
                        return DetalleVenta.builder()
                                .venta(ventaConId)
                                .producto(producto)
                                .cantidad(detalle.getCantidad())
                                .precio(detalle.getPrecio())
                                .build();
                })
                .toList();
        ventaConId.setDetalles(detalles);
        ventaConId.setTotal(detalles.stream().mapToDouble(d -> d.getPrecio() * d.getCantidad()).sum());

        return Mapper.toDto(ventaRepository.save(ventaConId));
    }

    @Override
    public VentaDto updateVenta(VentaDto venta) {
        var v = ventaRepository.findById(venta.getId()).orElse(null);
        if(v == null) throw new NotFoundException("Venta no encontrada");
        if(venta.getFecha() != null) v.setFecha(venta.getFecha());
        if(venta.getEstado() != null) v.setEstado(venta.getEstado());
        if(venta.getTotal() != null) v.setTotal(venta.getTotal());
        if(venta.getIdSucursal() != null) {
            var s = sucursalRepository.findById(venta.getIdSucursal()).orElse(null);
            if(s == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(s);
        }
        return Mapper.toDto(ventaRepository.save(v));
    }

    @Override
    public void deleteVenta(Long idVenta) {
        if(ventaRepository.existsById(idVenta)) throw new NotFoundException("Venta no encontrada");
        ventaRepository.deleteById(idVenta);
    }
}
