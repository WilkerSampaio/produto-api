package com.wilker.produto_api.controller;

import com.wilker.produto_api.infrastructure.dto.in.ProdutoRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.ProdutoResponseDTO;
import com.wilker.produto_api.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")

public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        return ResponseEntity.ok(produtoService.criarProduto(produtoRequestDTO));
    }


}
