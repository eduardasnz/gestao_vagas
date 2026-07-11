# 🚀 Gestão de Vagas

API REST para gerenciamento de vagas de emprego, desenvolvida com Java e Spring Boot. 
O sistema permite criar empresas(company), candidatos(candidate) e vagas (jobs), autenticar empresas e candidatos, visualizar oportunidades de trabalhos.

## ✨ Funcionalidades

- **Gerenciamento de Vagas**: Criação e listagem de vagas
- **Validação de Dados**: Schemas com validação de campos obrigatórios (`requiredMode`)
- **Descrição Detalhada**: Suporte a descrições completas das vagas
- **Containerização**: Ambiente configurado com Docker e Docker Compose

## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Validation** - Validação de entradas
- **Docker** - Containerização da aplicação
- **Maven** - Gerenciamento de dependências
- **Banco de Dados** (a ser definido no `application.properties`)

## 📋 Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:

- [Git](https://git-scm.com)
- [Java JDK 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/products/docker-desktop/) e [Docker Compose](https://docs.docker.com/compose/install/) (opcional, mas recomendado)

## 🚀 Como Executar o Projeto

### Usando Docker (Recomendado)

```bash
# Clone o repositório
git clone https://github.com/eduardasnz/gestao_vagas.git

# Acesse a pasta do projeto
cd gestao_vagas

# Execute com Docker Compose
docker-compose up --build