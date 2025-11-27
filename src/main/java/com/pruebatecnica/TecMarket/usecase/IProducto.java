package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoDto;

import java.util.List;

public interface IProducto {

    List<ProductoDto> listProductos();
    ProductoDto createProducto (ProductoDto producto);
    ProductoDto updateProducto (Long idProducto, ProductoDto producto);
    void deleteProducto (Long idProducto);
}
