package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalRequest;
import com.pruebatecnica.TecMarket.entity.Dto.SucursalResponse;
import com.pruebatecnica.TecMarket.usecase.ISucursal;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/sucursales")
public class SucursalController {

    private final ISucursal sucursalService;

    @GetMapping
    public ResponseEntity<List<SucursalResponse>> getSucursales() {
        return ResponseEntity.ok(sucursalService.listSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalResponse> createSucursal(@Valid @RequestBody SucursalRequest request) {
        var sucursal =  sucursalService.createSucursal(request);
        return ResponseEntity.created(URI.create("/api/sucursales" + sucursal.getId())).body(sucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalResponse> updateSucursal(@Valid @RequestBody SucursalRequest request,
                                                           @PathVariable Long id) {
        return ResponseEntity.ok(sucursalService.updateSucursal(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
