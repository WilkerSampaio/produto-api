package com.wilker.produto_api.infrastructure.dto;

import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;

import java.math.BigDecimal;

public class ProdutoRequestDTOFixture {

    public static ProdutoRequestDTO build(String nome,
                                          String descricao,
                                          BigDecimal preco,
                                          Long idCategoria){
        return new ProdutoRequestDTO(nome, descricao, preco, idCategoria);
    }
}
