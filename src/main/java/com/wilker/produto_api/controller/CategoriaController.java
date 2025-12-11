package com.wilker.produto_api.controller;

import com.wilker.produto_api.infrastructure.dto.in.CategoriaRequestDTO;
import com.wilker.produto_api.infrastructure.dto.out.CategoriaResponseResumoDTO;
import com.wilker.produto_api.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categoria")

public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseResumoDTO> criarCategoria(@RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.ok(categoriaService.criarCategoria(categoriaRequestDTO));
    }
}
