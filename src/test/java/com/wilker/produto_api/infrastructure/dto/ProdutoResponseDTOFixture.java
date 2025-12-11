package com.wilker.produto_api.infrastructure.dto;

import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;

import java.math.BigDecimal;

public class ProdutoResponseDTOFixture {

    public static ProdutoResponseDTO build(Long id,
                                    String nome,
                                    String descricao,
                                    BigDecimal preco,
                                    CategoriaResponseResumoDTO categoria){
        return new ProdutoResponseDTO(id, nome, descricao, preco, categoria);

    }

}
