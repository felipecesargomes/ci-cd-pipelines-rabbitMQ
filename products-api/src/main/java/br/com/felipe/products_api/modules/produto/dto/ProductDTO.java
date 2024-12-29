package br.com.felipe.products_api.modules.produto.dto;

public record ProductDTO(
    Integer id,
    String name,
    Integer categoryId,
    String categoryName,
    Integer supplierId,
    String supplierName,
    Integer quantity
) {}
