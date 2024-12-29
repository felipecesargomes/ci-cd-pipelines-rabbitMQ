package br.com.felipe.products_api.modules.produto.controller;

import br.com.felipe.products_api.modules.produto.dto.ProductDTO;
import br.com.felipe.products_api.modules.produto.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Criar um produto
    @PostMapping
    public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.save(dto));
    }

    // Atualizar um produto
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Integer id, @RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.update(id, dto));
    }

    // Deletar um produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // Buscar produtos por nome
    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> findByName(@RequestParam String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    // Buscar produtos por categoria
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> findByCategory(@PathVariable Integer categoryId) {
        return ResponseEntity.ok(productService.findByCategory(categoryId));
    }

    // Buscar produtos por fornecedor
    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<List<ProductDTO>> findBySupplier(@PathVariable Integer supplierId) {
        return ResponseEntity.ok(productService.findBySupplier(supplierId));
    }
}
