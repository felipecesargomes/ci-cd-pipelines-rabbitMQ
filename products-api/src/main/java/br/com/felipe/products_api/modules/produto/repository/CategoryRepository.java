package br.com.felipe.products_api.modules.produto.repository;

import br.com.felipe.products_api.modules.produto.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByDescriptionContainingIgnoreCase(String description);
}
