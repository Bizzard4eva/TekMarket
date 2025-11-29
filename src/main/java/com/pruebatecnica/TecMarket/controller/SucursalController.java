package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.SucursalDto;
import com.pruebatecnica.TecMarket.usecase.ISucursal;
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
    public ResponseEntity<List<SucursalDto>> getSucursales() {
        return ResponseEntity.ok(sucursalService.listSucursales());
    }

    @PostMapping
    public ResponseEntity<SucursalDto> createSucursal(@RequestBody SucursalDto sucursalDto) {
        var sucursal =  sucursalService.createSucursal(sucursalDto);
        return ResponseEntity.created(URI.create("/api/sucursales" + sucursal.getId())).body(sucursal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> updateSucursal(@PathVariable Long id,
                                                      @RequestBody SucursalDto sucursalDto) {
        return ResponseEntity.ok(sucursalService.updateSucursal(id, sucursalDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        sucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }
}
