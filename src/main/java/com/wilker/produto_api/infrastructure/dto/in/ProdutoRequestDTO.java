package com.wilker.produto_api.infrastructure.dto.in;

import lombok.Builder;

@Builder
public record ProdutoRequestDTO(

        String nome,
        String descricao,
        String preco,
        Long idCategoria
) {
}
