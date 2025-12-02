package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.VentaDto;
import com.pruebatecnica.TecMarket.usecase.IVenta;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/ventas")
public class VentaController {

    private final IVenta ventaService;

    @GetMapping
    public ResponseEntity<List<VentaDto>> getVentas() {
        return ResponseEntity.ok(ventaService.listVentas());
    }
    @PostMapping
    public ResponseEntity<VentaDto> createVenta(@RequestBody VentaDto ventaDto) {
        var venta = ventaService.createVenta(ventaDto);
        return ResponseEntity.created(URI.create("/api/ventas/" + venta.getId())).body(venta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VentaDto> updateVenta(@PathVariable Long id,
                                                @RequestBody VentaDto ventaDto) {
        return ResponseEntity.ok(ventaService.updateVenta(ventaDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }
}
