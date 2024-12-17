# CI/CD Pipelines com RabbitMQ

Este projeto demonstra a implementação de um sistema distribuído usando RabbitMQ para comunicação entre microsserviços, juntamente com a integração de pipelines CI/CD para automação de testes e deploy.

## 📖 Descrição do Projeto

Este projeto implementa um sistema distribuído baseado em microsserviços, utilizando o **RabbitMQ** como sistema de mensageria para comunicação entre serviços. Cada serviço realiza tarefas específicas, e a troca de mensagens entre eles é gerenciada pelo RabbitMQ.

Os pipelines CI/CD configurados automatizam o processo de integração, teste e entrega do sistema.

---

## 💻 Tecnologias Utilizadas

As principais tecnologias e ferramentas utilizadas neste projeto são:

- **Node.js**: Para criação dos microsserviços.
- **Express.js**: Framework minimalista para desenvolvimento de APIs.
- **RabbitMQ**: Sistema de mensagens para comunicação assíncrona.
- **Docker**: Containerização dos microsserviços.
- **Docker Compose**: Gerenciamento de múltiplos serviços Docker.
- **PostgreSQL**: Banco de dados relacional utilizado por alguns serviços.
- **Sequelize**: ORM para PostgreSQL.
- **Jenkins/GitHub Actions**: Configuração de pipelines CI/CD.
- **bcryptjs**: Para hashing de senhas.

---

## 📂 Estrutura do Projeto

A estrutura do projeto segue uma organização modular:

```plaintext
ci-cd-pipelines-rabbitMQ/
├── auth-api/               # Microsserviço de autenticação
├── sales-api/              # Microsserviço de vendas
├── products-api/           # Microsserviço de produtos
├── config/                 # Configurações globais
│   └── db/                 # Configuração do banco de dados
├── docker-compose.yml      # Configuração de containers Docker
├── Jenkinsfile             # Pipeline de CI/CD (exemplo Jenkins)
├── README.md               # Documentação do projeto
