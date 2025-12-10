package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalRequest;
import com.pruebatecnica.TecMarket.entity.Dto.SucursalResponse;
import com.pruebatecnica.TecMarket.entity.Sucursal;
import com.pruebatecnica.TecMarket.exception.NotFoundException;
import com.pruebatecnica.TecMarket.repository.SucursalRepository;
import com.pruebatecnica.TecMarket.usecase.ISucursal;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SucursalService implements ISucursal {

    private final SucursalRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<SucursalResponse> listSucursales() {
        return repository.findAll()
                .stream()
                .map(sucursal -> mapper.map(sucursal, SucursalResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public SucursalResponse createSucursal(SucursalRequest sucursal) {
        var registrado = repository.save(mapper.map(sucursal, Sucursal.class));
        return mapper.map(registrado, SucursalResponse.class);
    }

    @Override
    public SucursalResponse updateSucursal(Long idSucursal, SucursalRequest sucursal) {
        repository.findById(idSucursal).orElseThrow(() -> new NotFoundException("⚠️ Sucursal no encontrada"));
        var actualizado = mapper.map(sucursal, Sucursal.class);
        actualizado.setId(idSucursal);
        return mapper.map(repository.save(actualizado), SucursalResponse.class);
    }

    @Override
    public void deleteSucursal(Long idSucursal) {
        if(!repository.existsById(idSucursal)) throw new NotFoundException("⚠️ Sucursal no encontrada");
        repository.deleteById(idSucursal);
    }

}
