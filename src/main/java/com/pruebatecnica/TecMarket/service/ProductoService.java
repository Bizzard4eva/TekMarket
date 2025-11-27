package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoDto;
import com.pruebatecnica.TecMarket.exception.NotFoundException;
import com.pruebatecnica.TecMarket.mapper.Mapper;
import com.pruebatecnica.TecMarket.repository.ProductoRepository;
import com.pruebatecnica.TecMarket.usecase.IProducto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ProductoService implements IProducto {

    private final ProductoRepository repository;

    @Override
    public List<ProductoDto> listProductos() {
        return repository.findAll().stream()
                .map(Mapper::toDto)
                .toList();
    }

    @Override
    public ProductoDto createProducto(ProductoDto producto) {
        return Mapper.toDto(repository.save(Mapper.toEntity(producto)));
    }

    @Override
    public ProductoDto updateProducto(Long idProducto, ProductoDto producto) {
        repository.findById(idProducto).orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        producto.setId(idProducto);
        return Mapper.toDto(repository.save(Mapper.toEntity(producto)));
    }

    @Override
    public void deleteProducto(Long idProducto) {
        if(!repository.existsById(idProducto))  throw new NotFoundException("Producto no encontrado");
        repository.deleteById(idProducto);
    }
}
