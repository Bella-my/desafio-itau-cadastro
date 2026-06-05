<img width="892" height="184" alt="image" src="https://github.com/user-attachments/assets/959442df-4115-419d-bac1-5b3de996e8fc" />

---
Aplicação Full Stack desenvolvida como solução para o desafio técnico de Engenharia de Software Jr.  
O sistema realiza o cadastro de pessoas com validações de negócio, integração com ViaCEP para preenchimento automático de endereço, geração automática de login e persistência dos dados em PostgreSQL.

---
## Links da Aplicação

### Frontend
https://desafio-itau-cadastro.vercel.app

### Swagger
https://desafio-itau-cadastro.onrender.com/swagger-ui/index.html

---
## Tecnologias Utilizadas

### Backend ☕

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

### Frontend ⚛️

* React
* Vite
* JavaScript
* CSS

### Infraestrutura 🐳

* Docker
* Docker Compose
* Render
* Vercel

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
## Regras de Negócio

### Cadastro

* Nome completo obrigatório

* CPF obrigatório e válido

* CPF único

* E-mail válido

* Data de nascimento válida

* Data de nascimento não futura

* CEP válido

* CEP existente no ViaCEP

* Número obrigatório

### Login

O login gerado automaticamente deve:

* Possuir exatamente 7 caracteres

* Conter apenas letras

* Não possuir números

* Não possuir espaços

* Ser único

* Ser derivado do nome informado


Exemplo:

```text

Ana Vitória Silva
↓

anavito

```

### Massa Inicial

O desafio forneceu uma massa inicial de registros já cadastrados.

Para atender ao requisito e permitir a validação da lógica de geração de login, a aplicação inicializa automaticamente registros de exemplo na primeira execução através de um DataLoader.

A carga ocorre apenas quando a base está vazia, evitando duplicidades em reinicializações da aplicação.

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

#### Arquitetura
```
Usuário
   ↓
Frontend (React/Vite)
   ↓
Backend (Spring Boot)
   ↓
PostgreSQL
```

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

## ▶️ Como Rodar o Projeto

Antes de executar o projeto, clone o repositório:

```bash
git clone https://github.com/Bella-my/desafio-itau-cadastro.git
cd desafio-itau-cadastro
```

O projeto pode ser executado localmente utilizando H2 ou através do Docker Compose com PostgreSQL.

---

## 💻 Executando Localmente

### Backend

Por padrão, a aplicação utiliza banco H2 em memória para facilitar a execução local.

Execute:

```bash
./mvnw spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

Console H2:

```text
http://localhost:8080/h2-console
```

Configuração padrão:

```text
JDBC URL: jdbc:h2:mem:desafioitau
User: sa
Password:
```

---

### Frontend

Abra um novo terminal e acesse a pasta do frontend:

```bash
cd frontend
```

Instale as dependências:

```bash
npm install
```

Execute a aplicação:

```bash
npm run dev
```

A aplicação ficará disponível em:

```text
http://localhost:5173
```

Caso a porta esteja em uso, o Vite poderá iniciar automaticamente em outra porta disponível.

---

## 🐳 Executando com Docker

O projeto também pode ser executado utilizando Docker Compose.

Este modo sobe automaticamente:

* Backend Spring Boot
* Banco PostgreSQL

Execute:

```bash
docker compose up --build
```

Serviços disponíveis:

```text
Backend: http://localhost:8080
PostgreSQL: localhost:5433
```

Para interromper os containers:

```bash
docker compose down
```

---

## 🧪 Executando os Testes

Para executar os testes automatizados do backend:

```bash
./mvnw test
```

Os testes foram desenvolvidos utilizando:

* JUnit 5
* Mockito

```
```
---
## Documentação Complementar

### Documentação da API

```text
https://desafio-itau-cadastro.onrender.com/swagger-ui/index.html
```

### Doc Arquitetura

A documentação de arquitetura contendo decisões técnicas, estrutura da solução, lógica ultilizada na geração de login, validações, regras de negócio e fluxo da aplicação encontram-se documentadas em material complementar disponivel no link:

```text
https://precious-reaper-055.notion.site/Documenta-o-de-Arquitetura-376518e245a380798b98d5e3d0ac7da9
```
---

### Doc Evidências

As evidências de execução, testes, integração, Docker e deploy encontram-se documentadas em material complementar disponivel no link:

```text
https://precious-reaper-055.notion.site/Evid-ncias-de-Teste-Desafio-T-cnico-Ita-375518e245a380e98f75cce93beb237c 
```
---

## Diferenciais Implementados

* Swagger/OpenAPI
* Docker e Docker Compose
* PostgreSQL
* Deploy em produção
* Testes unitários com JUnit e Mockito
* Documentação de Arquitetura

---

## 👩‍💻 Autora

| |
|:---:|
| [![Ana Vitória](https://github.com/Bella-my.png?size=120)](https://github.com/Bella-my) |
| **Ana Vitória Silva** |
| Desenvolvedora Backend \| Java & Spring Boot |
| [LinkedIn](https://linkedin.com/in/ana-silva-880931178) |

