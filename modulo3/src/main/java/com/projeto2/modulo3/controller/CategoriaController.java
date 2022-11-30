package com.projeto2.modulo3.controller;

import com.projeto2.modulo3.dto.CategoriaDto;
import com.projeto2.modulo3.input.CategoriaInput;
import com.projeto2.modulo3.model.Categoria;
import com.projeto2.modulo3.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @GetMapping
    @ResponseBody
    public ResponseEntity<List<CategoriaDto>> listar(){
        List<Categoria> categorias = categoriaService.listar();
        return new ResponseEntity<List<CategoriaDto>>(toCollection(categorias), HttpStatus.OK);
    }

@ApiOperation("CADASTRAR")
    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody CategoriaInput categoria) {
        Categoria cat = categoriaService.salvar(toDomain(categoria));
        return new ResponseEntity<CategoriaDto>(toDto(cat), HttpStatus.CREATED);
    }
    @ApiOperation("ALTERAR")
    @PutMapping
    public ResponseEntity<CategoriaDto> alterar(@RequestBody CategoriaInput categoria){
        Categoria cat = categoriaService.salvar(toDomain(categoria));
        return new ResponseEntity<CategoriaDto>(toDto(cat), HttpStatus.OK);
    }
    @ApiOperation("DELETAR")
    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idCategoria) {
        categoriaService.delete(idCategoria);
        return new ResponseEntity<String>("Categoria de ID: " + idCategoria, HttpStatus.OK);
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
