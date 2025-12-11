package com.wilker.produto_api.infrastructure.mapper;

import com.wilker.produto_api.infrastructure.dto.ProdutoRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ProdutoConverterMapperTest {

    @Autowired
    private ProdutoConverterMapper produtoConverterMapper;

    private ProdutoRequestDTO produtoRequestDTO;
    private ProdutoEntity produtoEntity;
    private CategoriaEntity categoriaEntity;

    @BeforeEach
    void setup() {
        categoriaEntity = CategoriaEntity.builder()
                .id(1L)
                .nome("Categoria Teste")
                .build();

        produtoEntity = ProdutoEntity.builder()
                .id(1L)
                .nome("Produto Teste")
                .descricao("Produto para teste")
                .preco(new BigDecimal("200.00"))
                .categoria(categoriaEntity)
                .build();

        produtoRequestDTO = ProdutoRequestDTOFixture.build(
                "Produto Teste",
                "Produto para teste",
                new BigDecimal("200.00"),
                1L);
    }

    @Test
    void deveConverterParaProdutoEntityComSucesso() {
        ProdutoEntity entity = produtoConverterMapper.paraProdutoEntity(produtoRequestDTO);

        assertNotNull(entity);
        assertEquals(produtoRequestDTO.nome(), entity.getNome());
        assertEquals(produtoRequestDTO.descricao(), entity.getDescricao());
        assertEquals(0, produtoRequestDTO.preco().compareTo(entity.getPreco()));

        assertNull(entity.getId());
        assertNull(entity.getCategoria());
    }

    @Test
    void deveConverterParaResponseComSucesso() {
        ProdutoResponseDTO response = produtoConverterMapper.paraProdutoResponse(produtoEntity);

        assertNotNull(response);
        assertEquals(produtoEntity.getId(), response.id());
        assertEquals(produtoEntity.getNome(), response.nome());
        assertEquals(produtoEntity.getDescricao(), response.descricao());
        assertEquals(0, produtoEntity.getPreco().compareTo(response.preco()));

        assertNotNull(response.categoriaResponseResumoDTO());
        assertEquals(categoriaEntity.getId(), response.categoriaResponseResumoDTO().id());
        assertEquals(categoriaEntity.getNome(), response.categoriaResponseResumoDTO().nome());
    }
}
