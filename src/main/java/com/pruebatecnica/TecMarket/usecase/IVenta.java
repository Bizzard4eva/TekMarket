package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.VentaDto;

import java.util.List;

public interface IVenta {

    List<VentaDto> listVentas();
    VentaDto createVenta(VentaDto venta);
    VentaDto updateVenta(VentaDto venta);
    void deleteVenta(Long idVenta);
}
