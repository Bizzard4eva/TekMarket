package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.ProductoRequest;
import com.pruebatecnica.TecMarket.entity.Dto.ProductoResponse;
import com.pruebatecnica.TecMarket.usecase.IProducto;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<ProductoResponse>> getProductos() {
        return ResponseEntity.ok(productoService.listProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoResponse> createProducto(@Valid @RequestBody ProductoRequest request) {
        var response = productoService.createProducto(request);
        return ResponseEntity.created(URI.create("/api/productos/" + response.getId())).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> updateProducto(@Valid @RequestBody ProductoRequest request,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(productoService.updateProducto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
