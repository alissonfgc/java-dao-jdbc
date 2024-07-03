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

![classes_diagram](https://github.com/alissonfgc/java-dao-jdbc/assets/72516014/6d33a96f-4096-4ad6-aa7c-4686f7c02959)



## Pacote Entities

Lista de métodos de cada classe no pacote entities:
* Constructors
* Getters/Setters
* hashCode and equals
* toString

![entities_classes_diagram](https://github.com/alissonfgc/java-dao-jdbc/assets/72516014/52963df2-4496-47a9-88ef-1a3ac72c83ea)



## UML do Banco de dados

![database_UML](https://github.com/alissonfgc/java-dao-jdbc/assets/72516014/32c7c500-0a94-4595-a390-165354b50627)

## Observações

> [!IMPORTANT]
> Para a implementação do método findByDepartment, da classe sellerDAO, não basta criar um objeto para cada linha retornada do banco de dados.

O método findByDepartment() da implementação da classe sellerDAO execuda a seguinte requisição ao banco de dados:

```mysql
 SELECT seller.*,department.Name as DepName
 FROM seller INNER JOIN department
 ON seller.DepartmentId = department.Id
 WHERE DepartmentId = ?
 ORDER BY Name
```
Onde a interrogação é substituida pelo valor recebido no parametro do método.
Então o método retorna uma lista de objetos do tipo Seller, e para o relacionamento correto entre os objetos, foi necessária a seguinte implementação:

### Forma incorreta

```java
st = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name;");
st.setInt(1, department.getId());
rs = st.executeQuery();

List<Seller> sellers = new ArrayList<>();
Map<Integer, Department> map = new HashMap<>();

while (rs.next()) {
 Department dep = map.get(rs.getInt("DepartmentId"));
 if (dep == null) {
  dep = instanciateDepartment(rs);
  map.put(rs.getInt("DepartmentId"), dep);
 }

 Seller seller = instanciateSeller(rs, dep);
 sellers.add(seller);
}

return sellers;

```

Deste jeito vai ser instanciado um objeto do tipo Department, para cada objeto do tipo Seller.
 conforme o primeiro exemplo da imagem.

![findByDepartment_method_UML](https://github.com/alissonfgc/java-dao-jdbc/assets/72516014/b507ab24-5250-4ffc-834d-3ff4a8f08acb)

### Forma correta

```java
st = conn.prepareStatement("SELECT seller.*,department.Name as DepName FROM seller INNER JOIN department ON seller.DepartmentId = department.Id WHERE DepartmentId = ? ORDER BY Name;");
st.setInt(1, department.getId());
rs = st.executeQuery();

List<Seller> sellers = new ArrayList<>();
Map<Integer, Department> map = new HashMap<>();

while (rs.next()) {
 Department dep = map.get(rs.getInt("DepartmentId"));
 if (dep == null) {
  dep = instanciateDepartment(rs);
  map.put(rs.getInt("DepartmentId"), dep);
 }

 Seller seller = instanciateSeller(rs, dep);
 sellers.add(seller);
}

return sellers;

```

Deste jeito apenas vai ser instanciado um objeto do tipo Department, caso não exista nenhum com o Id correspondente.
 conforme o segundo exemplo da imagem.

## Creditos
* http://educandoweb.com.br/
* Curso: Programação Orientada a Objetos com Java
* Professor: Dr. Nelio Alves
