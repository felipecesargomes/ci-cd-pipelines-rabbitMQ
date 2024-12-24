package br.com.felipe.products_api.modules.produto.dto;

import org.springframework.beans.BeanUtils;

import br.com.felipe.products_api.modules.produto.model.Category;

public class CategoryResponse {
    private Integer id;
    private String description;

    public static CategoryResponse of(Category category) {
        var response = new CategoryResponse();
        BeanUtils.copyProperties(category, response);
        return response;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
