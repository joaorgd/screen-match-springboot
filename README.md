# Screen Match 🎬

![Java](https://img.shields.io/badge/Java-21-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-4.0-red.svg)

## 📖 Sobre o Projeto

O **Screen Match** é uma aplicação de console desenvolvida em Java com Spring Boot que consome a API do [OMDB](https://www.omdbapi.com/) para buscar, exibir e analisar dados de séries de TV.

Este projeto foi criado como um exercício prático para aprofundar conhecimentos em Java moderno, incluindo a API de Streams, e para explorar a construção de aplicações robustas com o ecossistema Spring. A aplicação permite que o usuário busque por uma série e receba informações detalhadas sobre suas temporadas, episódios e avaliações.

---

## ✨ Funcionalidades

-   **Busca de Séries**: Busca dados gerais de uma série pelo título.
-   **Processamento de Temporadas e Episódios**: Itera sobre todas as temporadas para buscar e listar todos os episódios.
-   **Ranking de Episódios**: Exibe um Top 5 dos melhores episódios com base na avaliação do IMDb.
-   **Busca por Título**: Permite ao usuário encontrar um episódio específico digitando um trecho do seu título.
-   **Filtro por Ano de Lançamento**: Exibe episódios lançados a partir de um determinado ano.
-   **Estatísticas de Avaliações**: Calcula e exibe estatísticas completas (média, melhor e pior avaliação, total de episódios) e também a média de avaliação por temporada.

---

## 🛠️ Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

-   **Java 21**: Utilização de recursos modernos da linguagem, como Records e a API de Streams.
-   **Spring Boot**: Framework principal para a criação da aplicação de console, gerenciando o ciclo de vida e a execução.
-   **Maven**: Ferramenta para gerenciamento de dependências e automação do build do projeto.
-   **Jackson**: Biblioteca para conversão (parsing) de dados do formato JSON (recebido da API) para objetos Java.

---

## 🚀 Como Executar

Para executar o projeto localmente, siga os passos abaixo:

1.  **Clone o repositório:**
    ```bash
    git clone [https://github.com/joaorgd/screen-match-springboot.git](https://github.com/joaorgd/screen-match-springboot.git)
    ```

2.  **Navegue até o diretório do projeto:**
    ```bash
    cd screen-match-springboot
    ```

3.  **Execute a aplicação com o Maven:**
    ```bash
    mvn spring-boot:run
    ```

4.  Após a inicialização, o menu interativo aparecerá no console. Siga as instruções para buscar e analisar as séries.

---
