# Bookstore API

## I. Arquitetura de Solução e Arquitetura Técnica

A Bookstore API foi desenvolvida utilizando o framework **Spring Boot** com Java, seguindo uma arquitetura em camadas (Controller, Service, Repository e Model/DTO). O projeto utiliza o padrão REST para exposição dos endpoints e segue boas práticas de separação de responsabilidades.

**Tecnologias utilizadas:**
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Cache
- Maven
- Banco de dados relacional (ex: H2, PostgreSQL)
- JUnit e Mockito para testes

**Decisões de design:**
- Utilização de DTOs para desacoplar a camada de apresentação da camada de persistência.
- Implementação de paginação e ordenação nos endpoints de listagem.
- Uso de cache para otimizar consultas frequentes.
- Tratamento centralizado de exceções.

---

## II. Explicação sobre o Case Desenvolvido (Plano de Implementação)

A API permite o gerenciamento de livros, incluindo operações de cadastro, consulta, atualização e remoção. A lógica de negócios está concentrada na camada de serviço, que valida regras como unicidade de ISBN e existência de registros.

**Principais pontos da implementação:**
- **Camada Controller:** expõe endpoints REST para operações CRUD e paginação.
- **Camada Service:** contém a lógica de negócio, validações e orquestração das operações.
- **Camada Repository:** utiliza Spring Data JPA para acesso ao banco de dados.
- **DTOs:** utilizados para entrada e saída de dados, evitando exposição direta das entidades.
- **Tratamento de exceções:** exceções customizadas como `ResourceNotFoundException` são lançadas e tratadas para respostas padronizadas.
- **Paginação:** endpoints de listagem retornam dados paginados para garantir a ordenação do JSON.
- **Cache:** operações de leitura utilizam cache para melhorar performance.

---

## III. Melhorias e Considerações Finais

**Possíveis melhorias:**
- Implementação de autenticação e autorização (ex: JWT).
- Implementação de outro mecanismo de cache como por exemplo o Redis.
- implementação do uso do Docker. 
- Possível adição de testes de integração e cobertura de testes mais ampla.
- Documentação automática dos endpoints com Swagger/OpenAPI.
- Internacionalização das mensagens de erro.
- Suporte a filtros dinâmicos de busca.

**Desafios encontrados:**
- Garantir a serialização estável.
- Realizar a inserção de dados fakes ao iniciar o projeto.
- Configuração do cache para cenários de atualização e invalidação eficiente.

O projeto está estruturado para fácil manutenção e extensibilidade, seguindo padrões modernos de desenvolvimento Java e Spring Boot.
