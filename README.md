# Achadinhos da Vez

Projeto web para cadastro e visualização de produtos usados (inspirado em classificados locais). Desenvolvido com **Spring Boot 3.1.4**, **Thymeleaf**, **MySQL** e **Maven**. Layout inspirado em uma página de produto com cores laranja/bege, cards e upload de até 5 fotos.

## Funcionalidades
- **Lista de produtos** (página inicial com cards).
- **Cadastro de produto** (formulário com upload de imagens, detalhes como preço, tamanho, descrição, contato do vendedor).
- **Detalhes do produto** (página com imagem principal, miniaturas, infos e contato).
- **Integração com MySQL** (armazena produtos e caminhos de imagens).
- **Upload de imagens** (salva em pasta local, exibe via paths relativos).
- **API** (`/api/products` retorna para integrações futuras).

## Tecnologias
- Backend: Java 17, Spring Boot (Web, JPA, Thymeleaf, Validation).
- Banco: MySQL 8+.
- Frontend: HTML/Thymeleaf + CSS customizado (responsivo).
- Ferramentas: Maven, VS Code (recomendado).

## Estrutura do Projeto:

Arquivos da raiz:

## .gitignore

Lista de arquivos e pastas que o Git deve ignorar (ex: /target, .idea, arquivos temporários).
Evita enviar arquivos desnecessários ao repositório.

## README.md

Arquivo de documentação do projeto.
Serve para explicar o propósito do sistema, tecnologias usadas, instruções de execução e estrutura de pastas.

## pom.xml

Arquivo de configuração do Maven.
Define as dependências, plugins e versões usadas no projeto Spring Boot (como Spring Web, Spring Data JPA, MySQL Driver, Thymeleaf, etc).

## Código-fonte principal (src/main/java)
AchadinhosApplication.java

Classe principal do projeto.
Contém o método main() e a anotação @SpringBootApplication, que inicializa todo o sistema Spring Boot.

## Camada MVC (Model - View - Controller)
 controller/

Contém as classes responsáveis por receber as requisições HTTP do usuário e retornar respostas (páginas HTML ou dados JSON).

ProductController.java
Controlador principal do sistema de produtos.
Gerencia as rotas como:

/ → Página inicial

/product/new → Formulário de cadastro

/product/{id} → Detalhes do produto

/product/save → Salvar produto
Utiliza o ProductService para acessar a lógica de negócio.

## model/

Contém as entidades (classes de modelo) que representam as tabelas do banco de dados.

Product.java
Classe que define o modelo do produto, com atributos como:

id, name, description, price, images etc.
É anotada com @Entity para ser reconhecida pelo JPA.

## repository/

Contém as interfaces de acesso ao banco de dados.

ProductRepository.java
Interface que herda de JpaRepository<Product, Long>.
Permite executar operações como save(), findAll(), findById(), delete() etc.
O Spring gera automaticamente as implementações.

## service/

Contém a lógica de negócio da aplicação (processamentos e validações antes de salvar ou buscar dados).

ProductService.java
Responsável por centralizar as regras de negócio dos produtos, interagindo com o ProductRepository.
Pode conter métodos como:

saveProduct(Product p)

getAllProducts()

getProductById(Long id)

## Recursos da aplicação (src/main/resources)
application.properties

Arquivo de configuração da aplicação.