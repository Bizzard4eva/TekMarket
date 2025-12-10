package com.pruebatecnica.TecMarket.service;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoRequest;
import com.pruebatecnica.TecMarket.entity.Dto.ProductoResponse;
import com.pruebatecnica.TecMarket.entity.Producto;
import com.pruebatecnica.TecMarket.exception.NotFoundException;
import com.pruebatecnica.TecMarket.repository.ProductoRepository;
import com.pruebatecnica.TecMarket.usecase.IProducto;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductoService implements IProducto {

    private final ProductoRepository repository;
    private final ModelMapper mapper;

    @Override
    public List<ProductoResponse> listProductos() {
        return repository.findAll()
                .stream()
                .map(producto -> mapper.map(producto, ProductoResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoResponse createProducto(ProductoRequest producto) {
        Producto registrado = repository.save(mapper.map(producto,Producto.class));
        return mapper.map(registrado,ProductoResponse.class);
    }

    @Override
    public ProductoResponse updateProducto(Long idProducto, ProductoRequest producto) {
        repository.findById(idProducto).orElseThrow(() -> new NotFoundException("⚠️ Producto no encontrado"));
        var actualizado = mapper.map(producto,Producto.class);
        actualizado.setId(idProducto);
        return mapper.map(repository.save(actualizado),ProductoResponse.class);
    }

    @Override
    public void deleteProducto(Long idProducto) {
        if(!repository.existsById(idProducto)) throw new NotFoundException("⚠️ Producto no encontrado");
        repository.deleteById(idProducto);
    }

}
