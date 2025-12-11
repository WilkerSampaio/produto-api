package com.wilker.produto_api.infrastructure.mapper;

import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {CategoriaConverterMapper.class})
public interface ProdutoConverterMapper {

    @Mapping(target = "id", ignore = true)// Setado pelo banco
    @Mapping(target = "categoria", ignore = true) // Setado no serviço
    ProdutoEntity paraProdutoEntity(ProdutoRequestDTO produtoRequestDTO);

    @Mapping(target = "categoriaResponseResumoDTO", source = "categoria")
    ProdutoResponseDTO paraProdutoResponse(ProdutoEntity produtoEntity);
}
