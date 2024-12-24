package br.com.felipe.products_api.modules.produto.repository;
import org.springframework.stereotype.Repository;
import br.com.felipe.products_api.modules.produto.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    
}
