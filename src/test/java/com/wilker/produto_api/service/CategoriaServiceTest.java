package com.wilker.produto_api.service;

import com.wilker.produto_api.infrastructure.dto.CategoriaRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.CategoriaResponseResumoDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.mapper.CategoriaConverterMapper;
import com.wilker.produto_api.infrastructure.repository.CategoriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class CategoriaServiceTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private CategoriaConverterMapper categoriaConverterMapper;

    @InjectMocks
    private CategoriaService categoriaService;

    private CategoriaRequestDTO categoriaRequestDTO;
    private CategoriaEntity categoriaEntity;
    private CategoriaResponseResumoDTO categoriaResponseResumoDTO;

    @BeforeEach
    void setup() {

        categoriaRequestDTO = CategoriaRequestDTOFixture.build("Categoria Teste");

        categoriaEntity = CategoriaEntity.builder()
                .id(10L)
                .nome("Categoria Teste")
                .produtos(Collections.emptySet())
                .build();

        categoriaResponseResumoDTO = CategoriaResponseResumoDTOFixture.build(10L, "Categoria Teste");
    }

    @Test
    void deveCriarCategoriaComSucesso() {

        when(categoriaConverterMapper.paraCategoriaEntity(categoriaRequestDTO)).thenReturn(categoriaEntity);
        when(categoriaRepository.save(any(CategoriaEntity.class))).thenReturn(categoriaEntity);
        when(categoriaConverterMapper.paraCategoriaResponseResumo(categoriaEntity)).thenReturn(categoriaResponseResumoDTO);

        CategoriaResponseResumoDTO response = categoriaService.criarCategoria(categoriaRequestDTO);

        assertEquals(categoriaResponseResumoDTO, response);

        verify(categoriaConverterMapper).paraCategoriaEntity(categoriaRequestDTO);
        verify(categoriaRepository).save(categoriaEntity);
        verify(categoriaConverterMapper).paraCategoriaResponseResumo(categoriaEntity);

        verifyNoMoreInteractions(categoriaRepository, categoriaConverterMapper);
    }
}
