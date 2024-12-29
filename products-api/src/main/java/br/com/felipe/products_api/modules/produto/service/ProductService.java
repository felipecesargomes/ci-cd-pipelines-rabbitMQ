package br.com.felipe.products_api.modules.produto.service;

import br.com.felipe.products_api.modules.produto.dto.ProductDTO;
import br.com.felipe.products_api.modules.produto.model.Category;
import br.com.felipe.products_api.modules.produto.model.Product;
import br.com.felipe.products_api.modules.produto.model.Supplier;
import br.com.felipe.products_api.modules.produto.repository.ProductRepository;
import br.com.felipe.products_api.config.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Criar um novo produto
    public ProductDTO save(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.name());
        product.setQuantity(dto.quantity());
        product.setCategory(new Category(dto.categoryId(), null));
        product.setSupplier(new Supplier(dto.supplierId(), null));

        Product savedProduct = productRepository.save(product);

        return mapToDTO(savedProduct);
    }

    // Atualizar um produto existente
    public ProductDTO update(Integer id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Produto não encontrado com o ID: " + id));

        product.setName(dto.name());
        product.setQuantity(dto.quantity());
        product.setCategory(new Category(dto.categoryId(), null));
        product.setSupplier(new Supplier(dto.supplierId(), null));

        Product updatedProduct = productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    // Deletar um produto
    public void delete(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new ValidationException("Produto não encontrado com o ID: " + id);
        }
        productRepository.deleteById(id);
    }

    // Buscar todos os produtos
    public List<ProductDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Buscar produtos por nome
    public List<ProductDTO> findByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Buscar produtos por categoria
    public List<ProductDTO> findByCategory(Integer categoryId) {
        return productRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Buscar produtos por fornecedor
    public List<ProductDTO> findBySupplier(Integer supplierId) {
        return productRepository.findBySupplierId(supplierId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear entidade para DTO
    private ProductDTO mapToDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getCategory().getId(),
                product.getCategory().getDescription(),
                product.getSupplier().getId(),
                product.getSupplier().getName(),
                product.getQuantity()
        );
    }
}
