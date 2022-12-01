package com.projeto2.modulo3.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "produto")
public class Produto implements Serializable {
    @EqualsAndHashCode.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @NotBlank(message = "O nome é obrigatório!")
    @Column(nullable = false, unique = true)
    private String nome;
    @NotBlank(message = "O nome é obrigatório!")
     @Column(nullable = false, unique = true)
    private Double valorProduto;

    @Column(nullable = false, unique = true)
    private Boolean StatusProduto;

    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_categoria"))
    private Categoria categoria;


    @JsonBackReference
    public Categoria getCategoria() {
        return categoria;
    }

}


