package com.wilker.produto_api.infrastructure.mapper;

import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseCompletoDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.infrastructure.entity.CategoriaEntity;
import com.wilker.produto_api.infrastructure.entity.ProdutoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CategoriaConverterMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    CategoriaEntity paraCategoriaEntity(CategoriaRequestDTO categoriaRequestDTO);

    CategoriaResponseResumoDTO paraCategoriaResponseResumo(CategoriaEntity categoriaEntity);

    @Mapping(target = "idsProdutos", source = "produtos")
    CategoriaResponseCompletoDTO paraCategoriaResponseCompleto(CategoriaEntity categoriaEntity);

    default Set<Long> mapProdutosToId(Set<ProdutoEntity> produtos){
        if(produtos == null){
            return Collections.emptySet();
        }
        return produtos.stream()
                .map(ProdutoEntity::getId)
                .collect(Collectors.toSet());

    }
}