package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.VentaRequest;
import com.pruebatecnica.TecMarket.entity.Dto.VentaResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IVenta {

    List<VentaResponse> listVentas();
    VentaResponse createVenta(VentaRequest venta);
    VentaResponse updateVenta(Long idVenta, VentaRequest venta);
    void deleteVenta(Long idVenta);
}
