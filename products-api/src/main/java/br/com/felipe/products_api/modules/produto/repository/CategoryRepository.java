package br.com.felipe.products_api.modules.produto.repository;
import org.springframework.stereotype.Repository;
import br.com.felipe.products_api.modules.produto.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
