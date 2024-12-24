package br.com.felipe.products_api.modules.produto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.felipe.products_api.config.exception.ValidationException;
import br.com.felipe.products_api.modules.produto.dto.CategoryRequest;
import br.com.felipe.products_api.modules.produto.dto.CategoryResponse;
import br.com.felipe.products_api.modules.produto.model.Category;
import br.com.felipe.products_api.modules.produto.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryResponse save(CategoryRequest request) {
        validateCategoryNameInformed(request);
        var category = categoryRepository.save(Category.of(request));
        return CategoryResponse.of(category);
    }

    private void validateCategoryNameInformed(CategoryRequest request) {
        if (request.getDescription() == null || request.getDescription().trim().isEmpty()) {
            throw new ValidationException("Category name was not informed.");
        }
    }

}
