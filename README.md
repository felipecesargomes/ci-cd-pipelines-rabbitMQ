# CI/CD Pipelines com RabbitMQ

Este projeto demonstra a implementação de um sistema distribuído usando RabbitMQ para comunicação entre microsserviços, juntamente com a integração de pipelines CI/CD (no gitlab e github) para automação de testes e deploy.

## 📖 Descrição do Projeto

Este projeto implementa um sistema distribuído baseado em microsserviços, utilizando o **RabbitMQ** como sistema de mensageria para comunicação entre serviços. Cada serviço realiza tarefas específicas, e a troca de mensagens entre eles é gerenciada pelo RabbitMQ.

Os pipelines CI/CD configurados automatizam o processo de integração, teste e entrega do sistema.

---

## 💻 Tecnologias Utilizadas

As principais tecnologias e ferramentas utilizadas neste projeto são:

- **Node.js**: Para criação dos microsserviços.
- **Express.js**: Framework para desenvolvimento de APIs.
- **RabbitMQ**: Sistema de mensagens para comunicação assíncrona.
- **Docker**: Containerização dos microsserviços.
- **Docker Compose**: Gerenciamento de múltiplos serviços Docker.
- **PostgreSQL**: Banco de dados relacional utilizado por alguns serviços para auth-api e products-api.
- **Sequelize**: ORM para PostgreSQL.
- **GitLab CI/CD | GitHub Actions**: Configuração de pipelines CI/CD.
- **bcryptjs**: Para hashing de senhas.
- **mongodb**: Banco de Dados NOSQL para sales-api.

---

## 📂 Estrutura do Projeto

A estrutura do projeto segue uma organização modular:

```
tree -I "node_modules|dist|coverage"
```

```plaintext
├── auth-api
│   ├── app.js
│   ├── config
│   │   ├── constants
│   │   │   └── httpStatus.js
│   │   └── db
│   │       ├── dbConfig.js
│   │       └── initialData.js
│   ├── Dockerfile
│   ├── modules
│   │   └── user
│   │       ├── controller
│   │       │   └── UserController.js
│   │       ├── model
│   │       │   └── User.js
│   │       ├── repository
│   │       │   └── UserRepository.js
│   │       ├── routes
│   │       │   └── UserRoutes.js
│   │       ├── service
│   │       │   └── UserService.js
│   │       └── userException
│   │           └── UserException.js
│   ├── package.json
│   ├── package-lock.json
│   └── seeds.js
├── docker-compose.yml
├── products-api
│   ├── Dockerfile
│   ├── HELP.md
│   ├── mvnw
│   ├── mvnw.cmd
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── br
│   │   │   │       └── com
│   │   │   │           └── felipe
│   │   │   │               └── products_api
│   │   │   │                   ├── ProductsApiApplication.java
│   │   │   │                   └── StatusController.java
│   │   │   └── resources
│   │   │       ├── application.yml
│   │   │       ├── static
│   │   │       └── templates
│   │   └── test
│   │       └── java
│   │           └── br
│   │               └── com
│   │                   └── felipe
│   │                       └── products_api
│   │                           └── ProductsApiApplicationTests.java
│   └── target
│       ├── classes
│       │   ├── application.yml
│       │   └── br
│       │       └── com
│       │           └── felipe
│       │               └── products_api
│       │                   ├── ProductsApiApplication.class
│       │                   └── StatusController.class
│       ├── generated-sources
│       │   └── annotations
│       ├── generated-test-sources
│       │   └── test-annotations
│       └── test-classes
│           └── br
│               └── com
│                   └── felipe
│                       └── products_api
│                           └── ProductsApiApplicationTests.class
├── README.md
└── sales-api
    ├── app.js
    ├── Dockerfile
    ├── package.json
    └── package-lock.json
