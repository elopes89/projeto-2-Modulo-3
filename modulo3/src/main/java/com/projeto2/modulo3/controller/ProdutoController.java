package com.projeto2.modulo3.controller;

import com.projeto2.modulo3.dto.ProdutoDto;
import com.projeto2.modulo3.input.ProdutoInput;
import com.projeto2.modulo3.model.Produto;
import com.projeto2.modulo3.service.CategoriaService;
import com.projeto2.modulo3.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "Produtos")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/")
    public String teste() {
        return "Borrrraa";
    }

    @ApiOperation("LISTAR")
    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        List<Produto> produtos = produtoService.list();
        return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody ProdutoInput produto) {
        Produto prod = toDomain(produto);
        produtoService.salvar(prod);
        return new ResponseEntity<ProdutoDto>(toModel(prod), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idProduto) {
        produtoService.delete(idProduto);
        return new ResponseEntity<String>("Produto Deletado: " + idProduto, HttpStatus.OK);
    }

    @GetMapping(value = "/total")
    public ResponseEntity<String> getValorComprado() {
        return new ResponseEntity<String>("Total comprado: " + produtoService.getTotalComprado().toString(), HttpStatus.OK);
    }

    private Produto toDomain(ProdutoInput input) {
        Produto produto = new Produto();
        produto.setId(input.getId());
        produto.setStatusProduto(input.getStatusProduto());
        produto.setValorProduto(input.getValorProduto());
        produto.setNome(input.getNome());
        produto.setCategoria(categoriaService.getCategoriaById(input.getId_categoria()));

        return produto;
    }
    private ProdutoDto toModel(Produto produto) {
        ProdutoDto dto = new ProdutoDto();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setValor(produto.getValorProduto());
        dto.setStatus(produto.getStatusProduto());
        dto.setId_categoria(produto.getCategoria().getId());
        return dto;
    }

    private List<ProdutoDto> toCollection(List<Produto> produtos) {
        return produtos.stream().map(Produto -> toModel(Produto)).collect(Collectors.toList());
    }

}
