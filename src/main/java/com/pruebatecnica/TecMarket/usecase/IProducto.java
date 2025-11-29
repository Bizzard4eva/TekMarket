package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProducto {

    List<ProductoDto> listProductos();
    ProductoDto createProducto (ProductoDto producto);
    ProductoDto updateProducto (Long idProducto, ProductoDto producto);
    void deleteProducto (Long idProducto);
}
