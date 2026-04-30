# VRaptor - Online Loja

Aplicação web Java para gerenciamento de produtos ("Online Loja") utilizando o framework VRaptor MVC. Fornece interfaces para criação, listagem e exclusão de produtos.

## Pré-requisitos

- Java 7 (JDK 1.7)
- Apache Tomcat 7.0
- Eclipse IDE (recomendado)

## Instalação

1. Clone o repositório:
```bash
git clone https://github.com/rafaelakio/vraptor.git
```

2. Importe o projeto no Eclipse como "Existing Projects into Workspace"
3. Configure o Tomcat 7.0 no Eclipse
4. Execute o projeto no servidor

## Como Usar

- Acesse `http://localhost:8080/vraptor/` no navegador
- Use a interface para gerenciar produtos (criar, listar, excluir)

## Arquitetura

- **`src/br/com/caelum/online/loja/controlador/`**: Controllers MVC (VRaptor Resources)
- **`WebContent/WEB-INF/jsp/`**: Views JSP
- **`src/messages.properties`**: Internacionalização e mensagens de validação

Tecnologias: Java 7, VRaptor Framework, JSP, JSTL, jQuery.

## Como Contribuir

Veja [CONTRIBUTING.md](CONTRIBUTING.md) para diretrizes de contribuição.

## Licença

Este projeto está licenciado sob a licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.
