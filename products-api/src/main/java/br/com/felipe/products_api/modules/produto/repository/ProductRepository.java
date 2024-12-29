package br.com.felipe.products_api.modules.produto.repository;

import br.com.felipe.products_api.modules.produto.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByCategoryId(Integer categoryId);
    List<Product> findBySupplierId(Integer supplierId);
    boolean existsBySupplierId(Integer supplierId);
    boolean existsByCategoryId(Integer categoryId);
}
