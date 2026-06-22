# 📦 Gerenciador de Pedidos

Gerenciamento de pedidos, produtos, categorias e fornecedores — construída com **Java**, **Spring Boot** e **PostgreSQL**.

> Projeto pessoal desenvolvido para praticar arquitetura backend real com Spring Boot, JPA/Hibernate e relacionamentos entre entidades.

---

## 🚀 Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring MVC | — |
| JPA / Hibernate | — |
| PostgreSQL | 15+ |
| Maven | 3.x |

---

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas, separando responsabilidades de forma clara:

```
Controller  →  recebe as requisições HTTP
Service     →  contém as regras de negócio
Repository  →  acesso ao banco de dados (Spring Data JPA)
Entity      →  mapeamento das tabelas
```

```
src/
└── main/
    └── java/
        └── com/jhohannes/gerenciadorpedidos/
            ├── controller/
            │   ├── ProdutoController.java
            │   ├── CategoriaController.java
            │   ├── PedidoController.java
            │   └── FornecedorController.java
            ├── service/
            │   ├── ProdutoService.java
            │   ├── CategoriaService.java
            │   ├── PedidoService.java
            │   └── FornecedorService.java
            ├── repository/
            │   ├── ProdutoRepository.java
            │   ├── CategoriaRepository.java
            │   ├── PedidoRepository.java
            │   └── FornecedorRepository.java
            └── model/
                ├── Produto.java
                ├── Categoria.java
                ├── Pedido.java
                └── Fornecedor.java
```

---

## 🗂️ Entidades e Relacionamentos

```
Fornecedor  ──(1:N)──▶  Produto  ──(N:M)──▶  Pedido
                ▲
                │
            Categoria ──(1:N)──▶  Produto
```

| Relacionamento | Tipo | Detalhe |
|---|---|---|
| Fornecedor → Produto | `@OneToMany` | Um fornecedor tem vários produtos |
| Categoria → Produto | `@OneToMany` | Uma categoria agrupa vários produtos |
| Pedido ↔ Produto | `@ManyToMany` | Um pedido tem vários produtos; um produto pode estar em vários pedidos |

**Tabela de junção:** `pedido_produto` (lado proprietário: Pedido)

---

## ⚙️ Como rodar localmente

### Pré-requisitos

- Java 17+
- Maven 3.x
- PostgreSQL rodando localmente

### 1. Clone o repositório

```bash
git clone https://github.com/jhohannessf/gerenciador-pedidos.git
cd gerenciador-pedidos
```

### 2. Configure o banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE gerenciador_pedidos;
```

### 3. Configure o `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gerenciador_pedidos
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4. Rode o projeto

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`

---

## 📚 O que aprendi construindo esse projeto

- Arquitetura em camadas com Spring Boot e separação clara de responsabilidades
- Mapeamento de relacionamentos com JPA/Hibernate: `@OneToMany`, `@ManyToMany` e `@JoinTable`
- Tratamento de `LazyInitializationException` em relacionamentos lazy
- Validações de negócio na camada de serviço (duplicidade de nomes, IDs inexistentes)
- Diferença prática entre criar e atualizar entidades com `id == 0` em tipos primitivos
- Configuração de banco PostgreSQL com Spring Data JPA

---

## 🔧 Melhorias planejadas

- [ ] Tratamento global de exceções com `@ControllerAdvice`
- [ ] DTOs para desacoplar a camada de apresentação das entidades JPA
- [ ] Validações com Bean Validation (`@NotNull`, `@NotBlank`)
- [ ] Testes unitários com JUnit 5 e Mockito
- [ ] Documentação automática com Swagger / OpenAPI
- [ ] Containerização com Docker

---

## 📡 Endpoints (Melhoria planejada - Não codado)

### Produtos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/produtos` | Lista todos os produtos |
| GET | `/produtos/{id}` | Busca produto por ID |
| POST | `/produtos` | Cadastra novo produto |
| PUT | `/produtos/{id}` | Atualiza produto |
| DELETE | `/produtos/{id}` | Remove produto |

### Categorias

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/categorias` | Lista todas as categorias |
| GET | `/categorias/{id}` | Busca categoria por ID |
| POST | `/categorias` | Cadastra nova categoria |
| PUT | `/categorias/{id}` | Atualiza categoria |
| DELETE | `/categorias/{id}` | Remove categoria |

### Pedidos

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/pedidos` | Lista todos os pedidos |
| GET | `/pedidos/{id}` | Busca pedido por ID |
| POST | `/pedidos` | Cria novo pedido |
| PUT | `/pedidos/{id}` | Atualiza pedido |
| DELETE | `/pedidos/{id}` | Remove pedido |

### Fornecedores

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/fornecedores` | Lista todos os fornecedores |
| GET | `/fornecedores/{id}` | Busca fornecedor por ID |
| POST | `/fornecedores` | Cadastra novo fornecedor |
| PUT | `/fornecedores/{id}` | Atualiza fornecedor |
| DELETE | `/fornecedores/{id}` | Remove fornecedor |

---

## 💡 Exemplos de uso

### Cadastrar um produto

**POST** `/produtos`

```json
{
  "nome": "Notebook Dell",
  "preco": 3500.00,
  "categoria": { "id": 1 },
  "fornecedor": { "id": 2 }
}
```

### Criar um pedido com produtos

**POST** `/pedidos`

```json
{
  "produtos": [
    { "id": 1 },
    { "id": 3 }
  ]
}
```

---

## 👨‍💻 Autor

**Jhohannes Freitas**  
Desenvolvedor Java Jr | Spring Boot · JPA · REST APIs  
[LinkedIn](https://www.linkedin.com/in/jhohannes-freitas) · [GitHub](https://github.com/jhohannessf)
