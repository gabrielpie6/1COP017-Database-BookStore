# Livraria

Uma livraria tem a intenção de armazenar a informação de seus produtos e clientes em um banco de dados conforme seus dados

* Produto: são exclusivamente dos tipos Livro ou Revista
  * Livro: é um produto
    * Todo livro está associado com uma única categoria que o classifica dentro da livraria entre outros livros
    * Todo livro está associado com um ou mais autores
    * Todo livro possui:
      * **Título**: cadeida de caracteres, é único dentro do contexto da livraria no problema
      * **Nome(s) do autor(es)**: cadeia de caracteres
      * **ISBN**: número identificador único  de 13 dígitos (*International Standard Book Number*)
      * **Edição**: número inteiro
      * **Número de páginas**: número inteiro
      * **Nome da editora**: cadeia de caracteres
      * **Idioma**: cadeia de caracteres
      * **Ano de publicação**: inteiro que representa um ano de calendário, isto é, positivo e menor ou igual a 2024
      * **Quantidade em estoque**:
      * **Preço**:
  * **Revista**: é um produto
    * Toda revista está associada com uma editora que a publica
    * Toda revista uma única temática que a classifica dentro da livraria entre outras revistas
    * Toda revista possui:
      * **Nome**: cadeia de caracteres
      * **Número de páginas**: número inteiro
      * **Data de publicação**: data que contém dia, mês e ano da publicação
      * **ISSN**: número de identificador único de 8 dígitos (*International Standard Serial Number*)
* **Editora**: é quem publica livros e revistas
  * Uma editora pode publicar tanto livros, quanto revistas
  * Toda editora possui:
    * Nome: cadeia de caracteres, é único.
* **Cliente**: quem realiza compras na livraria
  * Um cliente pode fazer a compra de múltiplos produtos distintos e em qualquer quantidade, desde que a loja possua em estoque
  * Quando efetivada, a compra realizada gera um número único de nota fiscal, data e hora da compra
  * No cadastro de clientes da loja, armazena-se:
    * **Primeiro nome**: cadeia de caracteres
    * **Último nome**: cadeia de caracteres
    * **CPF**: identificador de 11 dígitos único do usuário no território brasileiro
