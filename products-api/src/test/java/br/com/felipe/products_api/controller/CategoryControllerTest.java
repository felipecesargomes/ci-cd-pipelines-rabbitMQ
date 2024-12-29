package br.com.felipe.products_api.controller;

import br.com.felipe.products_api.modules.produto.controller.CategoryController;
import br.com.felipe.products_api.modules.produto.dto.CategoryDTO;
import br.com.felipe.products_api.modules.produto.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO(1, "Eletrônicos");
        when(categoryService.save(Mockito.any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(post("/api/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Eletrônicos"));
    }

    @Test
    void testUpdateCategory() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO(1, "Eletrodomésticos");
        when(categoryService.update(Mockito.eq(1), Mockito.any(CategoryDTO.class))).thenReturn(categoryDTO);

        mockMvc.perform(put("/api/categories/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("Eletrodomésticos"));
    }

    @Test
    void testDeleteCategory() throws Exception {
        mockMvc.perform(delete("/api/categories/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testFindAllCategories() throws Exception {
        List<CategoryDTO> categories = Arrays.asList(
                new CategoryDTO(1, "Eletrônicos"),
                new CategoryDTO(2, "Livros")
        );
        when(categoryService.findAll()).thenReturn(categories);

        mockMvc.perform(get("/api/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Eletrônicos"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].description").value("Livros"));
    }

    @Test
    void testFindByDescription() throws Exception {
        List<CategoryDTO> categories = Arrays.asList(
                new CategoryDTO(1, "Eletrônicos")
        );
        when(categoryService.findByDescription("Eletrônicos")).thenReturn(categories);

        mockMvc.perform(get("/api/categories/search?description=Eletrônicos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].description").value("Eletrônicos"));
    }
}
