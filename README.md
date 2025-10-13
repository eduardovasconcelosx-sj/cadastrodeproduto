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

achadinhosdavez/
├─ .gitignore
├─ README.md
├─ pom.xml
├─ src/
│ ├─ main/
│ │ ├─ java/com/achadinhos/
│ │ │ ├─ AchadinhosApplication.java
│ │ │ ├─ controller/
│ │ │ │ └─ ProductController.java
│ │ │ ├─ model/
│ │ │ │ └─ Product.java
│ │ │ ├─ repository/
│ │ │ │ └─ ProductRepository.java
│ │ │ └─ service/
│ │ │ └─ ProductService.java
│ │ ├─ resources/
│ │ │ ├─ application.properties
│ │ │ ├─ static/
│ │ │ │ ├─ css/
│ │ │ │ │ └─ styles.css
│ │ │ │ └─ uploads/ <-- (criada em runtime para imagens)
│ │ │ └─ templates/
│ │ │ ├─ index.html
│ │ │ ├─ product_form.html
│ │ │ └─ product_detail.html
└─ sql/
└─ create_db.sql