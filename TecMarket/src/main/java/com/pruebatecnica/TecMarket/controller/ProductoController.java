package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoDto;
import com.pruebatecnica.TecMarket.usecase.IProducto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {

    private final IProducto productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getProductos() {
        return ResponseEntity.ok(productoService.listProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDto> createProducto(@RequestBody ProductoDto productoDto) {
        var producto = productoService.createProducto(productoDto);
        return ResponseEntity.created(URI.create("/api/productos/" + producto.getId())).body(producto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable Long id,
                                                      @RequestBody ProductoDto productoDto) {
        return ResponseEntity.ok(productoService.updateProducto(id, productoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }


}
