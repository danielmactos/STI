# STI - Suporte Técnico de Internet

Sistema web desenvolvido por Daniel Costa Matos como MVP educacional para solicitação, registro, triagem e conclusão de solicitações por falta de conexão à internet.

## Sobre o projeto

O **STI - Suporte Técnico de Internet** foi desenvolvido durante a disciplina de **Prática Profissional**, e tem como fundamento a observação da empresa **OLN Internet**.

O objetivo do MVP é demonstrar um fluxo funcional de atendimento técnico, desde a abertura da solicitação pelo cliente até a triagem, encaminhamento para o técnico e finalização do atendimento.

## Objetivo do MVP

Registrar, acompanhar, encaminhar e concluir solicitações relacionados à falta de conexão, permitindo que diferentes perfis profissionais participem do fluxo de atendimento.

## Tecnologias utilizadas

* Java 17
* Spring Boot 4.0.5
* Maven
* PostgreSQL 18.4
* Flyway
* JPA / Hibernate
* HTML
* CSS
* JavaScript

## Funcionalidades implementadas

* Abertura da solicitação pelo cliente.
* Geração automática de protocolo.
* Armazenamento dos chamados no PostgreSQL.
* Listagem de chamados por status.
* Triagem de chamados pelo(a) secretário(a).
* Registro de observação de triagem.
* Encaminhamento da solicitação ao técnico.
* Conclusão do atendimento técnico.
* Registro de data de encaminhamento e data de conclusão.
* Cadastro e listagem de profissionais.
* Dashboard simples para gestão.
* Solicitações concluídas compactadas e expansíveis.
* Interface web em HTML, CSS e JavaScript.

## Perfis do sistema

### Cliente

Permite iniciar uma solicitação informando os dados necessários para o atendimento.

### Secretário(a)

Permite visualizar solicitações abertas, registrar, selecionar técnico responsável e encaminhar a solicitação.

### Técnico

Permite visualizar solicitações direcionadas e alterar o status do atendimento.

### Gestão

Permite visualizar um dashboard simples e acessar o cadastro/listagem de profissionais.

## Estrutura principal do projeto

```text
src/main/java/com/informactos/its
├── controller
├── dto
├── model
├── repository
└── service
```

```text
src/main/resources
├── db/migration
├── static/index.html
└── application.properties
```

## Migrações do banco de dados

As migrações do Flyway estão localizadas em:

```text
src/main/resources/db/migration
```

Migrações implementadas:

```text
V1__DB_ITS_2.sql
V2__DB_ITS_2_PR.sql
V3__DB_ITS_2_ASSIGNMENT.sql
```

## Endpoints principais

```text
POST   /requests/open
GET    /requests
PATCH  /requests/{id}/status
POST   /professionals
GET    /professionals
```

## Configuração do banco

O projeto utiliza PostgreSQL e Flyway.

Banco utilizado durante o desenvolvimento:

```text
DB_ITS_3
```

O arquivo `application.properties` utiliza variáveis de ambiente para evitar exposição de dados sensíveis.

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/DB_ITS_3}
spring.datasource.username=${DB_USERNAME:SITS_DBA}
spring.datasource.password=${DB_PASSWORD}
```

## Execução local

Na raiz do projeto, execute:

```bash
mvn spring-boot:run
```

Depois acesse:

```text
http://localhost:8080
```

## Observação de segurança

O login profissional por nome e sobrenome foi implementado apenas como recurso visual para demonstração do MVP.

Em uma versão de produção, o sistema deve utilizar autenticação real, senha criptografada, controle de sessão, autorização por perfil e auditoria das ações realizadas no sistema.

## Status do projeto

MVP funcional.

O sistema permite executar o fluxo principal:

```text
Cliente faz a solicitação  →
Secretário(a) realiza a triagem →
Técnico realiza o atendimento →
Gestão acompanha informações básicas do processo
```

## Licença

Projeto acadêmico desenvolvido para fins educacionais (por enquanto, o objetivo é escalar e atuar em equipe para à produção).