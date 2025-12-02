package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalDto;
import com.pruebatecnica.TecMarket.exception.NotFoundException;
import com.pruebatecnica.TecMarket.mapper.Mapper;
import com.pruebatecnica.TecMarket.repository.SucursalRepository;
import com.pruebatecnica.TecMarket.usecase.ISucursal;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SucursalService implements ISucursal {

    private final SucursalRepository repository;

    @Override
    public List<SucursalDto> listSucursales() {
        return repository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public SucursalDto createSucursal(SucursalDto sucursal) {
        return Mapper.toDto(repository.save(Mapper.toEntity(sucursal)));
    }

    @Override
    public SucursalDto updateSucursal(Long idSucursal, SucursalDto sucursal) {
        repository.findById(idSucursal).orElseThrow(() -> new NotFoundException("Sucursal no encontrada"));
        sucursal.setId(idSucursal);
        return Mapper.toDto(repository.save(Mapper.toEntity(sucursal)));
    }

    @Override
    public void deleteSucursal(Long idSucursal) {
        if(!repository.existsById(idSucursal)) throw new NotFoundException("Sucursal no encontrada");
        repository.deleteById(idSucursal);
    }
}
