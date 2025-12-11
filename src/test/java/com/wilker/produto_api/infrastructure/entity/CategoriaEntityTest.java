package com.wilker.produto_api.infrastructure.entity;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CategoriaEntityTest {

    @Test
    void testEqualsAndHashCodeWithSameId() {
        CategoriaEntity cat1 = CategoriaEntity.builder().id(1L).nome("Cat A").build();
        CategoriaEntity cat2 = CategoriaEntity.builder().id(1L).nome("Cat B").build();

        assertThat(cat1).isEqualTo(cat2);
        assertThat(cat1.hashCode()).isEqualTo(cat2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithDifferentId() {
        CategoriaEntity cat1 = CategoriaEntity.builder().id(1L).build();
        CategoriaEntity cat2 = CategoriaEntity.builder().id(2L).build();

        assertThat(cat1).isNotEqualTo(cat2);
        assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeWithNullId() {
        CategoriaEntity cat1 = CategoriaEntity.builder().build();
        CategoriaEntity cat2 = CategoriaEntity.builder().build();

        assertThat(cat1).isNotEqualTo(cat2);
        assertThat(cat1.hashCode()).isNotEqualTo(cat2.hashCode());
    }
}
