package com.wilker.produto_api.infrastructure.dto.in;

import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import lombok.Builder;

@Builder
public record ProdutoRequestDTO(

        String nome,
        String descricao,
        String preco,
        CategoriaEntity categoria
) {
}
