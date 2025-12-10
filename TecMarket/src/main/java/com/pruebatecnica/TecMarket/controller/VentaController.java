package com.pruebatecnica.TecMarket.controller;

import com.pruebatecnica.TecMarket.entity.Dto.VentaRequest;
import com.pruebatecnica.TecMarket.entity.Dto.VentaResponse;
import com.pruebatecnica.TecMarket.usecase.IVenta;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<VentaResponse>> getVentas() {
        return ResponseEntity.ok(ventaService.listVentas());
    }
    @PostMapping
    public ResponseEntity<VentaResponse> createVenta(@Valid @RequestBody VentaRequest request) {
        var venta = ventaService.createVenta(request);
        return ResponseEntity.created(URI.create("/api/ventas/" + venta.getId())).body(venta);
    }
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> updateVenta(@Valid @RequestBody VentaRequest request,
                                                     @PathVariable Long id) {
        return ResponseEntity.ok(ventaService.updateVenta(id, request));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
        return ResponseEntity.noContent().build();
    }

}
