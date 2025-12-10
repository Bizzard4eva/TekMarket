package com.pruebatecnica.TecMarket.usecase;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoRequest;
import com.pruebatecnica.TecMarket.entity.Dto.ProductoResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IProducto {

    List<ProductoResponse> listProductos();
    ProductoResponse createProducto (ProductoRequest producto);
    ProductoResponse updateProducto (Long idProducto, ProductoRequest producto);
    void deleteProducto (Long idProducto);
}
