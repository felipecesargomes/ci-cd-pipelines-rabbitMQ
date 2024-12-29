package br.com.felipe.products_api.modules.produto.controller;

import br.com.felipe.products_api.modules.produto.dto.CategoryDTO;
import br.com.felipe.products_api.modules.produto.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // Criar uma categoria
    @PostMapping
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.save(dto));
    }

    // Atualizar uma categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Integer id, @RequestBody CategoryDTO dto) {
        return ResponseEntity.ok(categoryService.update(id, dto));
    }

    // Excluir uma categoria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Listar todas as categorias
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    // Buscar categorias por descrição
    @GetMapping("/search")
    public ResponseEntity<List<CategoryDTO>> findByDescription(@RequestParam String description) {
        return ResponseEntity.ok(categoryService.findByDescription(description));
    }
}
