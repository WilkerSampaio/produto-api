package com.wilker.produto_api.controler;

import com.wilker.produto_api.controller.CategoriaController;
import com.wilker.produto_api.infrastructure.dto.CategoriaRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.CategoriaResponseResumoDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.exception.GlobalExceptionHandler;
import com.wilker.produto_api.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CategoriaControllerTest {

    @Mock
    private CategoriaService categoriaService;

    @InjectMocks
    private CategoriaController categoriaController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String json;

    private CategoriaRequestDTO categoriaRequestDTO;
    private CategoriaResponseResumoDTO categoriaResponseResumoDTO;

    @BeforeEach
    void setup() throws Exception {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(categoriaController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();

        categoriaRequestDTO = CategoriaRequestDTOFixture.build("Categoria Teste");

        categoriaResponseResumoDTO = CategoriaResponseResumoDTOFixture.build(
                10L,
                "Categoria Teste"
        );

        json = objectMapper.writeValueAsString(categoriaRequestDTO);
    }

    @Test
    void deveCriarCategoriaComSucesso() throws Exception {

        when(categoriaService.criarCategoria(categoriaRequestDTO)).thenReturn(categoriaResponseResumoDTO);

        mockMvc.perform(post("/categoria")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10L))
                .andExpect(jsonPath("$.nome").value("Categoria Teste"));

        verify(categoriaService).criarCategoria(categoriaRequestDTO);
        verifyNoMoreInteractions(categoriaService);
    }
}
