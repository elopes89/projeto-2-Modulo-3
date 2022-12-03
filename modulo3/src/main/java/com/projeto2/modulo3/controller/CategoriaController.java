package com.projeto2.modulo3.controller;

import com.projeto2.modulo3.dto.CategoriaDto;
import com.projeto2.modulo3.input.CategoriaInput;
import com.projeto2.modulo3.model.Categoria;
import com.projeto2.modulo3.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Categorias")
@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @ApiOperation("LISTAR")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Categorias listados")
            , @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso")
            , @ApiResponse(code = 403, message = "Proibido")
            , @ApiResponse(code = 404, message = "Recurso não encontrado")})
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CategoriaDto>> listar() {
        List<Categoria> categorias = categoriaService.listar();
        return new ResponseEntity<List<CategoriaDto>>(toCollection(categorias), HttpStatus.OK);
    }

    @ApiOperation("CADASTRAR")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Categoria cadastrada")
            , @ApiResponse(code = 201, message = "Categoria cadastrada com sucesso")
            , @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso")
            , @ApiResponse(code = 403, message = "Proibido")
            , @ApiResponse(code = 404, message = "Recurso não encontrado")})

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody CategoriaInput categoria) {
        Categoria cat = categoriaService.salvar(toDomain(categoria));
        return new ResponseEntity<CategoriaDto>(toDto(cat), HttpStatus.CREATED);
    }

    @ApiOperation("ALTERAR")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Categoria alterada")
            , @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso")
            , @ApiResponse(code = 403, message = "Proibido")
            , @ApiResponse(code = 404, message = "Recurso não encontrado")})
    @PutMapping
    public ResponseEntity<CategoriaDto> alterar(@RequestBody CategoriaInput categoria) {
        Categoria cat = categoriaService.salvar(toDomain(categoria));
        return new ResponseEntity<CategoriaDto>(toDto(cat), HttpStatus.OK);
    }

    @ApiOperation("DELETAR")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Categoria Deletada")
            , @ApiResponse(code = 401, message = "Usuário sem permissão para acessar o recurso")
            , @ApiResponse(code = 403, message = "Proibido")
            , @ApiResponse(code = 404, message = "Recurso não encontrado")})
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idCategoria) {
        categoriaService.delete(idCategoria);
        return new ResponseEntity<String>("ID da Categoria deletada: " + idCategoria, HttpStatus.OK);
    }


    private CategoriaDto toDto(Categoria categoria) {
        CategoriaDto dto = new CategoriaDto();
        dto.setId(categoria.getId());
        dto.setNome(categoria.getNome());
        dto.setDescricao(categoria.getDescricao());

        return dto;
    }

    private Categoria toDomain(CategoriaInput categoriaInput) {
        Categoria cat = new Categoria();

        cat.setId(categoriaInput.getId());
        cat.setDescricao(categoriaInput.getDescricao());
        cat.setNome(categoriaInput.getNome());

        return cat;
    }

    private List<CategoriaDto> toCollection(List<Categoria> categorias) {
        return categorias.stream().map(Categoria -> toDto(Categoria))
                .collect(Collectors.toList());
    }
}
