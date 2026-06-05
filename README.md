# Desafio Itaú – Cadastro Inteligente de Pessoas

Aplicação Full Stack desenvolvida como solução para o desafio técnico de Engenharia de Software Jr.

O sistema realiza o cadastro de pessoas com validações de negócio, integração com ViaCEP para preenchimento automático de endereço, geração automática de login e persistência dos dados em PostgreSQL.

---

## Demonstração

### Frontend

https://desafio-itau-cadastro.vercel.app

### Backend

https://desafio-itau-cadastro.onrender.com

### Swagger / OpenAPI

https://desafio-itau-cadastro.onrender.com/swagger-ui/index.html

---

## Funcionalidades

* Cadastro de pessoas
* Consulta de pessoas cadastradas
* Integração com ViaCEP
* Preenchimento automático de endereço
* Validação de CPF
* Validação de CEP
* Validação de e-mail
* Validação de data de nascimento
* Geração automática de login
* Tratamento de erros no frontend
* Documentação da API com Swagger/OpenAPI
* Containerização com Docker
* Deploy completo em produção

---

## Arquitetura da Solução

### Backend

* Java 21
* Spring Boot 3
* Spring Data JPA
* Bean Validation
* PostgreSQL
* H2 Database
* OpenAPI / Swagger
* JUnit 5
* Mockito
* Maven

### Frontend

* React
* Vite
* JavaScript
* CSS

### Infraestrutura

* Docker
* Docker Compose
* Render
* Vercel

---

## Regras de Negócio

### Cadastro

O sistema valida:

* Nome completo contendo nome e sobrenome
* E-mail válido
* CPF válido
* CPF único
* Data de nascimento obrigatória
* Data de nascimento não futura
* CEP válido
* CEP existente no ViaCEP
* Número obrigatório

### Tratamento de Dados

Durante o cadastro:

* CPF é armazenado apenas com números
* CEP é armazenado apenas com números
* E-mail é normalizado
* Login é gerado automaticamente a partir do nome informado

---

## Fluxo da Aplicação

1. Usuário preenche o formulário.
2. Sistema valida os campos.
3. CEP é consultado no ViaCEP.
4. Endereço é preenchido automaticamente.
5. Backend valida CPF e regras de negócio.
6. Login é gerado automaticamente.
7. Dados são persistidos no PostgreSQL.
8. Usuário recebe confirmação do cadastro.

---

## API

### Cadastrar Pessoa

```http
POST /pessoas
```

Exemplo:

```json
{
  "nomeCompleto": "Ana Vitoria Silva",
  "cpf": "529.982.247-25",
  "email": "ana@email.com",
  "dataNascimento": "1995-03-15",
  "cep": "01001-000",
  "numero": "123",
  "complemento": "Apto 10"
}
```

### Listar Pessoas

```http
GET /pessoas
```

---

## Documentação da API

Com a aplicação em execução:

```text
http://localhost:8080/swagger-ui/index.html
```

Produção:

```text
https://desafio-itau-cadastro.onrender.com/swagger-ui/index.html
```

---

## Testes

O projeto possui testes unitários utilizando:

* JUnit 5
* Mockito

Executar:

```bash
./mvnw test
```

---

## Executando Localmente

### Backend

```bash
./mvnw spring-boot:run
```

API:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

### Frontend

```bash
cd frontend

npm install

npm run dev
```

Aplicação:

```text
http://localhost:5173
```

---

## Docker

Subir aplicação completa:

```bash
docker compose up --build
```

Serviços disponíveis:

```text
Backend:    http://localhost:8080
PostgreSQL: localhost:5433
```

---

## Variáveis de Ambiente

### Backend

```env
SPRING_PROFILES_ACTIVE=postgres

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/desafio_itau

SPRING_DATASOURCE_USERNAME=desafio_user

SPRING_DATASOURCE_PASSWORD=desafio123
```

### Frontend

```env
VITE_API_URL=http://localhost:8080
```

---

## Evidências

As evidências de execução, testes, integração, Docker e deploy encontram-se documentadas em material complementar entregue junto ao projeto.

---

## Diferenciais Implementados

* Integração com ViaCEP
* Swagger/OpenAPI
* Docker e Docker Compose
* PostgreSQL
* Deploy em produção
* Testes unitários com JUnit e Mockito
* Validações de negócio
* Arquitetura separada Frontend/Backend
* Persistência relacional com JPA

---

## Autora

Ana Vitória 

