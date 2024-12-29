package br.com.felipe.products_api.modules.produto.service;

import br.com.felipe.products_api.config.exception.ValidationException;
import br.com.felipe.products_api.modules.produto.dto.SupplierDTO;
import br.com.felipe.products_api.modules.produto.model.Supplier;
import br.com.felipe.products_api.modules.produto.repository.SupplierRepository;
import br.com.felipe.products_api.modules.produto.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductRepository productRepository;

    // Salvar um fornecedor
    public SupplierDTO save(SupplierDTO dto) {
        Supplier supplier = new Supplier();
        supplier.setName(dto.name());

        Supplier savedSupplier = supplierRepository.save(supplier);
        return mapToDTO(savedSupplier);
    }

    // Listar todos os fornecedores
    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Atualizar um fornecedor
    public SupplierDTO atualizar(Integer id, SupplierDTO dto) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new ValidationException("Fornecedor não encontrado com o ID: " + id));
        supplier.setName(dto.name());

        Supplier updatedSupplier = supplierRepository.save(supplier);
        return mapToDTO(updatedSupplier);
    }

    // Remover um fornecedor
    public void deletar(Integer id) {
        if (productRepository.existsBySupplierId(id)) {
            throw new ValidationException("Fornecedor não encontrado com o ID: " + id);

        }
        supplierRepository.deleteById(id);
    }

    // Buscar fornecedores por nome
    public List<SupplierDTO> buscarPorNome(String nome) {
        return supplierRepository.findByNameContainingIgnoreCase(nome)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Método auxiliar para mapear entidade para DTO
    private SupplierDTO mapToDTO(Supplier supplier) {
        return new SupplierDTO(supplier.getId(), supplier.getName());
    }
}
