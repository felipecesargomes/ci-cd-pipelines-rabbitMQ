package br.com.felipe.products_api.modules.produto.repository;

import br.com.felipe.products_api.modules.produto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    List<Supplier> findByNameContainingIgnoreCase(String name);
}
