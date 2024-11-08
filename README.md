# Reclamala

**Reclamala** é uma plataforma para gestão de comentários e respostas, permitindo que usuários registrem feedbacks e instituições possam responder a esses feedbacks. O projeto é construído com Spring Boot, JPA, e PostgreSQL.

---

## Sumário

- [Sobre o Projeto](#sobre-o-projeto)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Uso](#uso)
- [API Endpoints](#api-endpoints)
- [Contribuições](#contribuições)
- [Licença](#licença)

---

## Sobre o Projeto

**Reclamala** permite que os usuários enviem comentários classificados por categorias (infraestrutura, ensino, suporte, entre outros). Cada comentário pode ser respondido por administradores e as respostas podem ser curtidas pelos usuários.

### Funcionalidades:
- **Cadastro de usuários** com diferentes tipos (Administrador, Desenvolvedor, Aluno).
- **Cadastro de comentários** por usuários, com categorias específicas.
- **Respostas aos comentários**, com possibilidade de curtir.
- **Consulta e gerenciamento** dos comentários e respostas.

---

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** (para construção do backend)
- **Spring Data JPA** (para acesso ao banco de dados)
- **SpringDoc OpenAPI** (para documentação da API)
- **PostgreSQL** (para persistência de dados)

---

## Instalação

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/seu-usuario/reclamala.git
   cd reclamala
  

 Configure o banco de dados:
Instale o PostgreSQL no seu computador e crie um banco de dados.
Defina a URL do banco de dados e as credenciais no arquivo .env.
Instale as dependências:

```bash
Copiar código
mvn spring-boot:run
```

Configuração
Crie um arquivo .env na raiz do projeto e adicione as variáveis de configuração necessárias, como:

env
Copiar código
DB_URL=jdbc:postgresql://localhost:5432/reclamala
DB_USERNAME=seu_usuario
DB_PASSWORD=sua_senha
Uso
Depois de executar o projeto, a aplicação estará disponível em http://localhost:8080.

2. **:API Endpoints**
Acesse os endpoints da API através da URL base http://localhost:8080/api. Você pode consultar a documentação completa da API em http://localhost:8080/swagger-ui.html.

Comentários
POST /api/comentarios - Cria um novo comentário.
GET /api/comentarios - Lista todos os comentários.
PUT /api/comentarios/{id} - Atualiza um comentário existente.
DELETE /api/comentarios/{id} - Exclui um comentário.
Respostas
POST /api/respostas - Cria uma nova resposta.
GET /api/respostas - Lista todas as respostas.
POST /api/respostas/{id}/curtir - Adiciona uma curtida a uma resposta.
Contribuições
Sinta-se à vontade para contribuir com melhorias para o projeto! Para isso, basta seguir as etapas abaixo:

Fork este repositório.
Crie uma branch para a sua feature: git checkout -b minha-feature.
Faça commit das suas alterações: git commit -am 'Adiciona nova feature'.
Envie para o seu fork: git push origin minha-feature.
Crie um Pull Request.

