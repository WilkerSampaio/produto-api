package com.wilker.produto_api.infrastructure.entity;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;

class ProdutoEntityTest {

    @Test
    void testEqualsAndHashCodeWithSameId() {
        ProdutoEntity produto1 = ProdutoEntity.builder()
                .id(1L)
                .nome("Produto A")
                .descricao("Desc A")
                .preco(BigDecimal.valueOf(10))
                .categoria(new CategoriaEntity())
                .build();

        ProdutoEntity produto2 = ProdutoEntity.builder()
                .id(1L)
                .nome("Produto B")
                .descricao("Desc B")
                .preco(BigDecimal.valueOf(20))
                .categoria(new CategoriaEntity())
                .build();

        assertThat(produto1).isEqualTo(produto2);
        assertThat(produto1.hashCode()).isEqualTo(produto2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentId() {
        ProdutoEntity produto1 = ProdutoEntity.builder().id(1L).build();
        ProdutoEntity produto2 = ProdutoEntity.builder().id(2L).build();

        assertThat(produto1).isNotEqualTo(produto2);
        assertThat(produto1.hashCode()).isNotEqualTo(produto2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithNullId() {
        ProdutoEntity produto1 = ProdutoEntity.builder().build();
        ProdutoEntity produto2 = ProdutoEntity.builder().build();

        assertThat(produto1).isNotEqualTo(produto2);
        // hashCode deve usar super.hashCode() (não igual)
        assertThat(produto1.hashCode()).isNotEqualTo(produto2.hashCode());
    }
}

