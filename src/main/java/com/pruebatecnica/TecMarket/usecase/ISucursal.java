package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalDto;

import java.util.List;

public interface ISucursal {

    List<SucursalDto> listSucursales();
    SucursalDto createSucursal (SucursalDto sucursal);
    SucursalDto updateSucursal (Long idSucursal, SucursalDto sucursal);
    void deleteSucursal (Long idSucursal);
}
