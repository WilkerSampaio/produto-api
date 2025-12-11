package com.wilker.produto_api.service;

import com.wilker.produto_api.infrastructure.dto.CategoriaResponseResumoDTOFixture;
import com.wilker.produto_api.infrastructure.dto.ProdutoRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.ProdutoResponseDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import com.wilker.produto_api.infrastructure.exception.ResourceNotFoundException;
import com.wilker.produto_api.infrastructure.mapper.ProdutoConverterMapper;
import com.wilker.produto_api.infrastructure.repository.CategoriaRepository;
import com.wilker.produto_api.infrastructure.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @Mock
    private ProdutoConverterMapper produtoConverterMapper;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private ProdutoService produtoService;

    private ProdutoRequestDTO produtoRequestDTO;
    private ProdutoEntity produtoEntity;
    private CategoriaEntity categoriaEntity;
    private ProdutoResponseDTO produtoResponseDTO;
    private CategoriaResponseResumoDTO categoriaResponseResumoDTO;

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

        categoriaResponseResumoDTO = CategoriaResponseResumoDTOFixture.build(
                1L,
                "Categoria Teste");

        produtoResponseDTO = ProdutoResponseDTOFixture.build(
                1L,
                "Produto Teste",
                "Produto para teste",
                new BigDecimal("200.00"),
                categoriaResponseResumoDTO);
    }


    @Test
    void deveCriarProdutoComSucesso() {

        when(produtoConverterMapper.paraProdutoEntity(produtoRequestDTO)).thenReturn(produtoEntity);
        when(categoriaRepository.findById(produtoRequestDTO.idCategoria())).thenReturn(Optional.of(categoriaEntity));
        when(produtoRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);
        when(produtoConverterMapper.paraProdutoResponse(produtoEntity)).thenReturn(produtoResponseDTO);

        ProdutoResponseDTO responseDTO = produtoService.criarProduto(produtoRequestDTO);

        assertEquals(produtoResponseDTO, responseDTO);

        verify(produtoConverterMapper).paraProdutoEntity(produtoRequestDTO);
        verify(categoriaRepository).findById(produtoRequestDTO.idCategoria());
        verify(produtoRepository).save(any(ProdutoEntity.class));
        verify(produtoConverterMapper).paraProdutoResponse(produtoEntity);

        verifyNoMoreInteractions(produtoConverterMapper, categoriaRepository, produtoRepository);
    }

    @Test
    void deveLancarExcecaoQuandoCategoriaNaoEncontrada() {

        when(produtoConverterMapper.paraProdutoEntity(produtoRequestDTO))
                .thenReturn(produtoEntity);

        when(categoriaRepository.findById(produtoRequestDTO.idCategoria()))
                .thenReturn(Optional.empty());

        ResourceNotFoundException e = assertThrows(ResourceNotFoundException.class,
                () -> produtoService.criarProduto(produtoRequestDTO));

        assertEquals("Categoria não encontrada com ID: " + produtoRequestDTO.idCategoria(), e.getMessage());

        verify(produtoConverterMapper).paraProdutoEntity(produtoRequestDTO);
        verify(categoriaRepository).findById(produtoRequestDTO.idCategoria());

        verifyNoInteractions(produtoRepository);
        verifyNoMoreInteractions(produtoConverterMapper, categoriaRepository);
    }
}


