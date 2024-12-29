package br.com.felipe.products_api.modules.produto.service;

import br.com.felipe.products_api.modules.produto.dto.CategoryDTO;
import br.com.felipe.products_api.modules.produto.model.Category;
import br.com.felipe.products_api.modules.produto.repository.CategoryRepository;
import br.com.felipe.products_api.modules.produto.repository.ProductRepository;
import br.com.felipe.products_api.config.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    // Salvar uma categoria
    public CategoryDTO save(CategoryDTO dto) {
        Category category = new Category();
        category.setDescription(dto.description());

        Category savedCategory = categoryRepository.save(category);
        return mapToDTO(savedCategory);
    }

    // Atualizar uma categoria
    public CategoryDTO update(Integer id, CategoryDTO dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Categoria não encontrada com o ID: " + id));

        category.setDescription(dto.description());

        Category updatedCategory = categoryRepository.save(category);
        return mapToDTO(updatedCategory);
    }

    // Deletar uma categoria
    public void delete(Integer id) {
        if (productRepository.existsByCategoryId(id)) {
            throw new ValidationException("Não é possível remover esta categoria, pois está associada a produtos.");
        }
        categoryRepository.deleteById(id);
    }

    // Listar todas as categorias
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Buscar categorias por descrição
    public List<CategoryDTO> findByDescription(String description) {
        return categoryRepository.findByDescriptionContainingIgnoreCase(description)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear entidade para DTO
    private CategoryDTO mapToDTO(Category category) {
        return new CategoryDTO(category.getId(), category.getDescription());
    }
}
