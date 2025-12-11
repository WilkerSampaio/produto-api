package com.wilker.produto_api.controler;

import com.wilker.produto_api.controller.ProdutoController;
import com.wilker.produto_api.infrastructure.dto.CategoriaResponseResumoDTOFixture;
import com.wilker.produto_api.infrastructure.dto.ProdutoRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.ProdutoResponseDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.exception.GlobalExceptionHandler;
import com.wilker.produto_api.infrastructure.exception.ResourceNotFoundException;
import com.wilker.produto_api.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
 class ProdutoControllerTest {

    @Mock
    ProdutoService produtoService;

    @InjectMocks
    ProdutoController produtoController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private String json;

    private ProdutoRequestDTO produtoRequestDTO;
    private ProdutoResponseDTO produtoResponseDTO;
    private CategoriaResponseResumoDTO categoriaResponseResumoDTO;

    @BeforeEach
    void setup() {

        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .alwaysDo(print())
                .build();

        produtoRequestDTO = ProdutoRequestDTOFixture.build(
                "Produto Teste",
                "Produto para teste",
                new BigDecimal("200.00"),
                1L);

        categoriaResponseResumoDTO = CategoriaResponseResumoDTOFixture.build(
                1L,
                "Categoria Teste");

        produtoResponseDTO = ProdutoResponseDTOFixture.build(
                1L,
                "Produto Teste",
                "Produto para teste",
                new BigDecimal("200.00"),
                categoriaResponseResumoDTO);

        json = objectMapper.writeValueAsString(produtoRequestDTO);
    }

    @Test
    void deveCriarProdutoComSucesso200ok() throws Exception {

        when(produtoService.criarProduto(any(ProdutoRequestDTO.class)))
                .thenReturn(produtoResponseDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.descricao").value("Produto para teste"))
                .andExpect(jsonPath("$.preco").value(200.00))
                .andExpect(jsonPath("$.categoriaResponseResumoDTO.id").value(1L))
                .andExpect(jsonPath("$.categoriaResponseResumoDTO.nome").value("Categoria Teste"));

        verify(produtoService).criarProduto(any(ProdutoRequestDTO.class));
        verifyNoMoreInteractions(produtoService);
    }

    @Test
    void deveRetornarNotFound404CasoCategoriaNaoEncontrada() throws Exception {

        when(produtoService.criarProduto(any(ProdutoRequestDTO.class)))
                .thenThrow(new ResourceNotFoundException(
                        "Categoria não encontrada com ID: " + produtoRequestDTO.idCategoria()
                ));

        mockMvc.perform(MockMvcRequestBuilders.post("/produto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Not Found"))
                .andExpect(jsonPath("$.message")
                        .value("Categoria não encontrada com ID: " + produtoRequestDTO.idCategoria()))
                .andExpect(jsonPath("$.path").value("/produto"))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(produtoService).criarProduto(any(ProdutoRequestDTO.class));
        verifyNoMoreInteractions(produtoService);
    }
}
