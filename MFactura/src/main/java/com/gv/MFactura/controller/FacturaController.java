package com.gv.MFactura.controller;

import com.gv.MFactura.entity.Factura;
import com.gv.MFactura.repository.IFacturaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final IFacturaRepository facturaRepository;

    public FacturaController(IFacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    // Crear una nueva factura
    @PostMapping
    public Factura createFactura(@RequestBody Factura factura) {
        return facturaRepository.save(factura);
    }

    // Listar todas las facturas
    @GetMapping
    public List<Factura> getAllFacturas() {
        return facturaRepository.findAll();
    }

    // Obtener una factura por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Factura> getFacturaById(@PathVariable Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar una factura
    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody Factura facturaDetails) {
        Optional<Factura> factura = facturaRepository.findById(id);
        if (factura.isPresent()) {
            Factura existingFactura = factura.get();
            existingFactura.setClienteId(facturaDetails.getClienteId());
            existingFactura.setMonto(facturaDetails.getMonto());
            existingFactura.setFecha(facturaDetails.getFecha());
            Factura updatedFactura = facturaRepository.save(existingFactura);
            return ResponseEntity.ok(updatedFactura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar una factura
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        if (factura.isPresent()) {
            facturaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
