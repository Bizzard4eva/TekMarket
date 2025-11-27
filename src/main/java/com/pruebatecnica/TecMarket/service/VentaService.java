package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.Dto.VentaDto;
import com.pruebatecnica.TecMarket.mapper.Mapper;
import com.pruebatecnica.TecMarket.repository.VentaRepository;
import com.pruebatecnica.TecMarket.usecase.IVenta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VentaService implements IVenta {

    private final VentaRepository repository;

    @Override
    public List<VentaDto> listVentas() {
        return repository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public VentaDto createVenta(VentaDto venta) {
        return null;
    }

    @Override
    public VentaDto updateVenta(VentaDto venta) {
        return null;
    }

    @Override
    public void deleteVenta(Long idVenta) {

    }
}
