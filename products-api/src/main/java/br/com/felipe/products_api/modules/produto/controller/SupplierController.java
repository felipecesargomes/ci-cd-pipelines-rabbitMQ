package br.com.felipe.products_api.modules.produto.controller;

import br.com.felipe.products_api.modules.produto.dto.SupplierDTO;
import br.com.felipe.products_api.modules.produto.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // Criar um fornecedor
    @PostMapping
    public ResponseEntity<SupplierDTO> create(@RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(supplierService.save(dto));
    }

    // Listar todos os fornecedores
    @GetMapping
    public ResponseEntity<List<SupplierDTO>> findAll() {
        return ResponseEntity.ok(supplierService.findAll());
    }

    // Atualizar um fornecedor
    @PutMapping("/{id}")
    public ResponseEntity<SupplierDTO> update(@PathVariable Integer id, @RequestBody SupplierDTO dto) {
        return ResponseEntity.ok(supplierService.atualizar(id, dto));
    }

    // Deletar um fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        supplierService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar fornecedores por nome
    @GetMapping("/search")
    public ResponseEntity<List<SupplierDTO>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(supplierService.buscarPorNome(name));
    }
}
