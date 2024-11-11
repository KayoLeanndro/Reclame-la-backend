# Reclama la - Plataforma de Feedbacks

Este é um projeto de uma plataforma de feedbacks para alunos e instituições, construído utilizando Java e Spring Boot, com PostgreSQL como banco de dados

## Sumário

- [Tecnologias](#tecnologias)
- [Configurações](#configurações)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Entidades Principais](#entidades-principais)
- [Autenticação e Autorização](#autenticação-e-autorização)
- [Docker e Deploy](#docker-e-deploy)
- [Documentação](#documentação)
- [Contribuição](#contribuição)

## Tecnologias

- **Linguagem**: Java
- **Framework**: Spring Boot
- **Banco de Dados**: PostgreSQL (com suporte a SQLite para persistência offline(Feature))
- **Cloud**: Railway


## Configurações

- Configuração das variáveis de ambiente no `.env` para gerenciamento seguro de credenciais.
- Conexão com Railway usando PostgreSQL 16.3.
- Implementação de Docker para deploy e controle de dependências.

## Estrutura do Projeto

A aplicação é dividida em módulos para separar as responsabilidades:

- **Configurações**: Arquivos e classes de configuração para integração com Twilio e banco de dados.
- **Entidades**: Representações das entidades `Usuario`, `Comentario`, `Resposta`, etc.
- **Serviços**: Camada de lógica de negócio.
- **Controllers**: Pontos de entrada da API RESTful.

## Entidades Principais

### Usuario
- **Atributos**: `matricula`, `usuarioCpf`, `username`, `password`, `tipoUsuario`
- **Enums**: `UsuarioTipo` (com valores como `DESENVOLVEDOR`, `ADMINISTRADOR`, `ALUNO`)

### Comentario
- **Atributos**: `id`, `texto`, `categoriaComentario`
- **Relacionamentos**: Um usuário pode ter vários comentários.
- **Enum**: `ComentarioTipo` (com valores `INFRAESTRUTURA`, `ENSINO`, `SUPORTE`, `OUTRO`)

### Resposta
- Entidade separada para responder aos comentários dos usuários.

## Autenticação e Autorização

- Implementação de autenticação com criptografia de senha e integração com Firebase.
- Metodologia de login com validação de usuário e permissões.

## Docker e Deploy

- Configuração de Docker para contêinerizar a aplicação.
- Deploy usando Railway, com variáveis de ambiente configuradas para PostgreSQL.

## Documentação

- Documentação gerada com **SpringDoc** para facilitar a navegação pelas rotas e funcionalidades da API.

## Contribuição

1. Faça um fork do projeto.
2. Crie uma nova branch: `git checkout -b minha-nova-feature`
3. Commit suas alterações: `git commit -m 'Adiciona nova feature'`
4. Faça um push para a branch: `git push origin minha-nova-feature`
5. Envie um Pull Request

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
