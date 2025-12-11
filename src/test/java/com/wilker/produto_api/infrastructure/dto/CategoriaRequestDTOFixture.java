package com.wilker.produto_api.infrastructure.dto;

import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;

public class CategoriaRequestDTOFixture {

    public static CategoriaRequestDTO build(String nome){
        return new CategoriaRequestDTO(nome);
    }
}
