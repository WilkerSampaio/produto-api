package com.wilker.produto_api.infrastructure.mapper;

import com.wilker.produto_api.infrastructure.dto.CategoriaRequestDTOFixture;
import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseCompletoDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaConverterMapperTest {

    private CategoriaConverterMapper categoriaConverterMapper;

    private CategoriaRequestDTO categoriaRequestDTO;
    private CategoriaEntity categoriaEntity;

    @BeforeEach
    void setup() {
        categoriaConverterMapper = Mappers.getMapper(CategoriaConverterMapper.class);

        categoriaRequestDTO = CategoriaRequestDTOFixture.build("Categoria Teste");

        categoriaEntity = CategoriaEntity.builder()
                .id(10L)
                .nome("Categoria Teste")
                .produtos(null)
                .build();
    }

    @Test
    void deveConverterParaCategoriaEntityComSucesso() {
        CategoriaEntity entity = categoriaConverterMapper.paraCategoriaEntity(categoriaRequestDTO);

        assertNotNull(entity);
        assertNull(entity.getId()); // ID é ignorado pelo mapper
        assertEquals(categoriaRequestDTO.nome(), entity.getNome());
        assertNull(entity.getProdutos()); // Produtos são ignorados pelo mapper
    }

    @Test
    void deveConverterParaCategoriaResponseResumo() {
        CategoriaResponseResumoDTO dto = categoriaConverterMapper.paraCategoriaResponseResumo(categoriaEntity);

        assertNotNull(dto);
        assertEquals(categoriaEntity.getId(), dto.id());
        assertEquals(categoriaEntity.getNome(), dto.nome());
    }

    @Test
    void deveConverterParaCategoriaResponseCompletoComProdutos() {
        ProdutoEntity produto1 = ProdutoEntity.builder().id(1L).build();
        ProdutoEntity produto2 = ProdutoEntity.builder().id(2L).build();

        categoriaEntity.setProdutos(Set.of(produto1, produto2));

        CategoriaResponseCompletoDTO responseCompletoDTO = categoriaConverterMapper.paraCategoriaResponseCompleto(categoriaEntity);

        assertNotNull(responseCompletoDTO);
        assertEquals(categoriaEntity.getId(), responseCompletoDTO.id());
        assertEquals(categoriaEntity.getNome(), responseCompletoDTO.nome());
        assertNotNull(responseCompletoDTO.idsProdutos());
        assertEquals(2, responseCompletoDTO.idsProdutos().size());
        assertTrue(responseCompletoDTO.idsProdutos().contains(1L));
        assertTrue(responseCompletoDTO.idsProdutos().contains(2L));
    }

    @Test
    void deveConverterParaCategoriaResponseCompletoComProdutosNulos() {
        categoriaEntity.setProdutos(null);

        CategoriaResponseCompletoDTO dto = categoriaConverterMapper.paraCategoriaResponseCompleto(categoriaEntity);

        assertNotNull(dto.idsProdutos());
        assertTrue(dto.idsProdutos().isEmpty());
    }
}
