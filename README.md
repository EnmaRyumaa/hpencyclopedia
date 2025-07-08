
# 🧙‍♂️ Wizard’s Encyclopedia

**Wizard’s Encyclopedia** é um sistema full-stack inspirado no universo de Harry Potter, criado para exercitar arquitetura, padrões de projeto e mensageria com RabbitMQ.

> “It does not do to dwell on dreams and forget to live.” — *Albus Dumbledore*

---

## 🎯 Objetivo

Praticar:
- Spring Boot
- Mensageria (RabbitMQ)
- Consumo de APIs externas
- Boas práticas de arquitetura

Tudo isso em um contexto divertido baseado no mundo mágico!

---

## 📚 Índice

- [Visão Geral](#-visão-geral)
- [Arquitetura](#-arquitetura)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Estrutura de Dados](#-estrutura-de-dados)
- [Funcionalidades](#-funcionalidades)
- [Fluxo do Comentário](#-fluxo-do-comentário)
- [Segurança](#-segurança)
- [Modelagem de Dados](#-modelagem-de-dados)
- [Como Rodar o Projeto](#-como-rodar-o-projeto)
- [Scripts Úteis](#-scripts-úteis)
- [Próximas Melhorias](#-próximas-melhorias)
- [Créditos](#-créditos)

---

## 🌐 Visão Geral

O sistema permite:

✅ Consultar personagens, feitiços ou casas através da Harry Potter API  
✅ Criar comentários sobre personagens, feitiços ou casas  
✅ Processar comentários de forma assíncrona via RabbitMQ  
✅ Editar ou excluir comentários sem login (usando token secreto)  
✅ (Opcional) Armazenar dados em cache Redis para performance  

---

## 🏗️ Arquitetura



                         ┌─────────────────────┐
                         │     Frontend        │
                         │ (HTML/CSS/JS        │
                         │  ou React/Vue)      │
                         └─────────┬───────────┘
                                   │
                                   │ HTTP
                                   │
                         ┌─────────▼───────────┐
                         │      Backend        │
                         │ (REST API)          │
                         │ ┌─────────────────┐ │
                         │ │ Controller      │ │
                         │ │ Layer           │ │
                         │ ├─────────────────┤ │
                         │ │ Service Layer   │ │
                         │ ├─────────────────┤ │
                         │ │ Repository      │ │
                         │ │ Layer           │ │
                         │ └─────────────────┘ │
                         └─────────┬───────────┘
                                   │
                     ┌─────────────┼──────────────┐
           External API         Publish         Query/Save
           (Harry Potter)      message           Comments
              (HTTP)          to RabbitMQ         in DB
                 │                  │                  │
   ┌─────────────▼─────────────┐    │    ┌─────────────▼─────────────┐
   │   Harry Potter API        │    │    │         Database         │
   │  (characters, spells,     │    │    │       (PostgreSQL)       │
   │      houses etc.)         │    │    └─────────────▲────────────┘
   └───────────────────────────┘    │                  │
                                    │                  │
                                    │                  │
                             ┌──────▼───────┐          │
                             │   RabbitMQ   │──────────┘
                             │  Queue:      │
                             │ comments.create │
                             └──────┬───────┘
                                    │
                                    │ Consumes messages
                                    │
                         ┌──────────▼───────────┐
                         │      Worker          │
                         │  - Consumes Queue    │
                         │  - Validates text    │
                         │  - Saves to DB       │
                         └──────────┬───────────┘
                                    │
                                    │ Optional
                                    │ publishes
                                    │
                         ┌──────────▼───────────┐
                         │     Redis Cache      │
                         │ (Popular lookups)    │
                         └──────────────────────┘
⚙️ **Tecnologias Utilizadas**

**Backend:**

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- RabbitMQ (Spring AMQP)
- PostgreSQL
- Redis (opcional)
- Lombok
- Flyway (migrações DB)

**Frontend:**

- A definir (qualquer framework ou HTML/CSS/JS puro)

**API Externa:**

- Harry Potter API

---

🗃️ **Estrutura de Dados**

Exemplo de personagem retornado pela API:

```json
{
  "name": "Harry Potter",
  "house": "Gryffindor",
  "species": "human",
  "gender": "male",
  "dateOfBirth": "31-07-1980",
  "wand": {
    "wood": "holly",
    "core": "phoenix feather",
    "length": 11
  }
}
```

## 📝 Funcionalidades

**Buscar dados**

- /characters

- /characters/house/{house}

- /spells

**Comentários**

- Criar comentário → POST /comments

- Buscar comentários → GET /comments?entityId=...

- Editar comentário → PUT /comments/{id}

- Excluir comentário → DELETE /comments/{id}

**📨 Como funciona o fluxo do comentário?**

- Frontend chama o endpoint POST /comments.

- Backend não grava direto no banco → publica mensagem na fila RabbitMQ comments.create.

- Worker consome a fila:

- Valida texto (palavras proibidas, spam etc.).

- Salva no banco se válido.

- (Opcional) publica evento para atualizar cache.

- Frontend lista somente comentários validados.

**🔐 Segurança sem login**

Para editar ou excluir comentários, o sistema gera um token secreto salvo no frontend (localStorage ou cookies).

**🗄️ Modelagem de Dados**

Tabela: Comment

| Campo       | Tipo         | Descrição                                  |
|-------------|--------------|--------------------------------------------|
| id          | bigint       | Chave primária                             |
| entityId    | varchar      | ID do personagem/feitiço/casa externo      |
| entityType  | varchar      | “character”, “spell”, “house”              |
| authorName  | varchar      | Nome do autor (opcional)                   |
| commentText | text         | Texto do comentário                        |
| createdAt   | timestamp    | Data de criação                            |
| editToken   | varchar(36)  | Token secreto para edição/exclusão         |

## 🚀 Como rodar o projeto

**Pré-requisitos**

- ✅ Java 17+
- ✅ Maven ou Gradle
- ✅ PostgreSQL rodando
- ✅ RabbitMQ rodando

**Subir RabbitMQ via Docker**

```bash
docker run -d --name rabbitmq \
  -p 5672:5672 -p 15672:15672 \
  rabbitmq:3-management
  ```
**Acesse a interface RabbitMQ em:**

```bash
http://localhost:15672
Usuário: guest
Senha: guest
```

**Configuração application.yml**

```bash
yaml
Copiar
Editar
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/wizard_db
    username: postgres
    password: yourpassword

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest
    ```
**Build & Run**

```bash

# Compile o projeto
mvn clean install

# Execute o backend
mvn spring-boot:run
```
🛠️ Scripts úteis

## 🌟 Próximas melhorias

- Testes unitários (JUnit, Mockito)

- Implementar análise de toxicidade nos comentários

- Melhorar o frontend (UI/UX)

- Internacionalização (i18n)

**💖 Créditos**

- Harry Potter API: https://hp-api.onrender.com/ 

- Ícones do projeto: Flaticon
