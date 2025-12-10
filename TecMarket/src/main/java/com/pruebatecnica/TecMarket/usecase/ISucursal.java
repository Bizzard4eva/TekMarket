package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalRequest;
import com.pruebatecnica.TecMarket.entity.Dto.SucursalResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ISucursal {

    List<SucursalResponse> listSucursales();
    SucursalResponse createSucursal (SucursalRequest sucursal);
    SucursalResponse updateSucursal (Long idSucursal, SucursalRequest sucursal);
    void deleteSucursal (Long idSucursal);
}
