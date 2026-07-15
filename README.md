# 🚀 Gestão de Vagas

API REST para gerenciamento de vagas de emprego, desenvolvida com Java e Spring Boot. O projeto foi pensado para conectar empresas e candidatos em um fluxo simples e seguro, permitindo cadastro, autenticação, publicação de vagas e candidatura a oportunidades.

## 📌 Objetivo do projeto

A aplicação simula um sistema de recrutamento onde:

- empresas podem se cadastrar, autenticar e publicar vagas;
- candidatos podem se cadastrar, autenticar e se candidatar a vagas;
- a API oferece autenticação por JWT e endpoints protegidos por autorização;
- a documentação da API é disponibilizada via Swagger/OpenAPI.

## ✨ Funcionalidades principais

- Cadastro e autenticação de empresas
- Cadastro e autenticação de candidatos
- Publicação de vagas por empresas
- Busca de vagas por filtro de descrição
- Candidatura de candidatos a vagas
- Perfil do candidato com dados básicos e contexto de acesso
- Validação de dados de entrada com Bean Validation
- Segurança com Spring Security e tokens JWT
- Documentação interativa com Swagger UI

## 🧱 Arquitetura do projeto

A estrutura do projeto está organizada em módulos principais:

- `src/main/java/com/eduardasnz/gestao_vagas/modules/company` — recursos relacionados a empresas, vagas e fluxo de contratação
- `src/main/java/com/eduardasnz/gestao_vagas/modules/candidate` — recursos relacionados a candidatos e inscrições
- `src/main/java/com/eduardasnz/gestao_vagas/security` — configuração de segurança e filtros de autenticação
- `src/main/java/com/eduardasnz/gestao_vagas/providers` — providers responsáveis pela geração e validação de tokens JWT
- `src/main/java/com/eduardasnz/gestao_vagas/expections` — tratamento centralizado de exceções

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Security
- JWT (Auth0 Java JWT)
- PostgreSQL
- Swagger / OpenAPI
- Docker e Docker Compose
- Maven
- JUnit 5 e Spring Test

## 📋 Pré-requisitos

Antes de iniciar, certifique-se de ter instalado:

- [Git](https://git-scm.com)
- [Java JDK 21+](https://www.oracle.com/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) e [Docker Compose](https://docs.docker.com/compose/install/)

## ⚙️ Configuração de ambiente

A aplicação lê as seguintes variáveis de ambiente:

```bash
export DATABASE_URL=jdbc:postgresql://localhost:5432/gestao_vagas
export DATABASE_USERNAME=admin
export DATABASE_PASSWORD=admin
```

O arquivo de configuração do projeto já espera essas variáveis no ambiente.

## ▶️ Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/eduardasnz/gestao_vagas.git
cd gestao_vagas
```

### 2. Suba o banco de dados com Docker Compose

```bash
docker compose up -d postgres
```

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A aplicação ficará disponível em:

- http://localhost:8080

## 📚 Documentação da API

A documentação interativa do Swagger pode ser acessada em:

- http://localhost:8080/swagger-ui/index.html

## 🔐 Endpoints principais

Alguns dos principais fluxos da API são:

- Cadastro de empresas: `POST /company/`
- Autenticação de empresas: `POST /company/auth`
- Cadastro de candidatos: `POST /candidate/`
- Autenticação de candidatos: `POST /candidate/auth`
- Perfil do candidato autenticado: `GET /candidate/`
- Listagem de vagas por filtro: `GET /candidate/job`
- Candidatura a uma vaga: `POST /candidate/job/apply`
- Cadastro de vagas pela empresa: `POST /company/jobs/`

## 🧪 Testes

Para executar os testes do projeto:

```bash
./mvnw test
```

## 🐳 Docker

O projeto utiliza Docker Compose para subir o banco PostgreSQL. O arquivo de configuração está disponível em `docker-compose.yml`.

## 📝 Observações

Este projeto é uma API backend voltada para estudo e prática de desenvolvimento com Spring Boot, segurança, autenticação JWT e arquitetura modular. Ele pode servir como base para evoluções futuras, como:

- integração com frontend;
- upload de currículo;
- painel administrativo;
- sistema de notificações;
- deploy em ambientes cloud.
