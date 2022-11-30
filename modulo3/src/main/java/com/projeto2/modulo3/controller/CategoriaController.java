package com.projeto2.modulo3.controller;

import com.projeto2.modulo3.dto.CategoriaDto;
import com.projeto2.modulo3.dto.ProdutoDto;
import com.projeto2.modulo3.input.CategoriaInput;
import com.projeto2.modulo3.model.Categoria;
import com.projeto2.modulo3.model.Produto;
import com.projeto2.modulo3.service.CategoriaService;
import com.projeto2.modulo3.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaDto> cadastrar(@RequestBody CategoriaInput categoria) {
        Categoria cat = categoriaService.salvar(toDomain(categoria));
        return new ResponseEntity<CategoriaDto>(toDto(cat), HttpStatus.CREATED);
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long idCategoria) {
        categoriaService.delete(idCategoria);
        return new ResponseEntity<String>("Categoria de ID: " + idCategoria, HttpStatus.OK);
    }


//    private PedidoRepresentationModel toRepresentatioModel(Pedido pedido){
//        PedidoRepresentationModel pedidoRepresentationModel = new PedidoRepresentationModel();
//        pedidoRepresentationModel.setId(pedido.getId());
//        pedidoRepresentationModel.setIdCliente(pedido.getCliente().getId());
//        pedidoRepresentationModel.setNomeCliente(pedido.getCliente().getNome());
//        pedidoRepresentationModel.setIdFormaPagamento(pedido.getFormaPagamento().getId());
//        pedidoRepresentationModel.setFormaPagamentoDescricao(pedido.getFormaPagamento().getDescricao());

//            for (int i = 0; i < pedido.getItensPedido().size(); i++) {
//        ItemPedidoRepresentationModel itemPedidoRepresentationModel = new ItemPedidoRepresentationModel();
//        itemPedidoRepresentationModel.setId(pedido.getItensPedido().get(i).getId());
//        itemPedidoRepresentationModel.setIdProduto(pedido.getItensPedido().get(i).getProduto().getId());
//        itemPedidoRepresentationModel.setDescricaoProduto(pedido.getItensPedido().get(i).getProduto().getDescricao());
//        itemPedidoRepresentationModel.setQuantidade(pedido.getItensPedido().get(i).getQuantidade());
//        itemPedidoRepresentationModel.setValorItem(pedido.getItensPedido().get(i).getValorItem());
//
//        pedidoRepresentationModel.getItensPedidoRepresentationModel().add(itemPedidoRepresentationModel);


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
