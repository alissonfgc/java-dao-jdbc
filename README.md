# Acesso a banco de dados com JDBC
<a id="readme-top"></a>

### Objetivo geral:
* Conhecer os principais recursos do JDBC na teoria e prática
* Elaborar a estrutura básica de um projeto com JDBC
* Implementar o padrão DAO manualmente com JDBC

## Visão geral do JDBC

* JDBC (Java Database Connectivity): API padrão do Java para acesso a dados
* Páginas oficiais:
  * https://docs.oracle.com/javase/8/docs/technotes/guides/jdbc/
  * https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html
* Pacotes utilizados: java.sql e javax.sql (API suplementar para servidores)

![JDBC_operation](https://github.com/alissonfgc/java-dao-jdbc/assets/72516014/cd368662-44f4-42bf-a12c-ef0719581751)


## Padrão de projeto DAO (Data Access Object)

Ideia geral do padrão DAO:
* Para cada entidade, haverá um objeto responsável por fazer acesso a dados relacionado a esta entidade. Por exemplo:
  * Cliente: ClienteDao
  * Produto: ProdutoDao
  * Pedido: PedidoDao
* Cada DAO será definido por uma interface.
* A injeção de dependência pode ser feita por meio do padrão de projeto Factory

Referências:
* https://www.devmedia.com.br/dao-pattern-persistencia-de-dados-utilizando-o-padrao-dao/30999
* https://www.oracle.com/technetwork/java/dataaccessobject-138824.html








Project Java connecting Java to MySQL using the JDBC Library and using the DAO pattern.
