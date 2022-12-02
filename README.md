# Projeto 2 Módulo 3 Futuro DEV[Pinheira]

Projeto desenvolvido em java com Spring Boot.

## Ferramentas necessárias para uso do projeto:

  - Intellij 
  - Postman
  - PgAdmin4 ou DBeaver
  - Google Chrome

## Como usar a aplicação

- No Intellij abra o arquivo pom.xml como projeto 
- Inicie a aplicação
- Na ferramenta de banco de dados verifique se foram criadas as tabelas
- No Postman ou no Swagger execute os endpoints
- Retorne ao DBeaver e veja os resultados da manipulação obtida com os endpoints
  
## Projeto documentado com a ferramenta Swagger.
O projeto pode ser utilizado com a ferrmenta no link: 
 [Swagger](http://localhost:4000/swagger-ui.html#/)


## Foto do modelo Relacional

## Exemplos de execução no Postman ou no Swagger

![](/modulo3/src/main/resources/modeloRelacional.png)

### Exemplo de como cadastrar uma categoria:
### Método POST
### Url http://localhost:4000/categorias

    {
        "nome": "biscoitos",
        "descricao": "bolachas, biscoitos e wafer" 
    }

### Exemplo de como cadastrar um produto:
### Método POST
### Url http://localhost:4000/produtos

    {
        "nome": "bolacha recheada",
        "valorProduto":5.0,
        "statusProduto": true,
        "id_categoria":1
    }


#### Obs.: É necessário ter no mínimo uma categoria registrada antes de cadastrar um  produto.


## Funcionalidade da aplicação
Com a aplicação é possível fazer um crud completo tanto de produtos quanto de suas categoarias. Ou seja, é possível: salvar, deletar, editar e listar ambas as entidades do projeto.











