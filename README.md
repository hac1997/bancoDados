# Projeto de Acesso a Banco de Dados com Java e JDBC (SQLite)

Este projeto Java demonstra diferentes abordagens e boas práticas para interagir com um banco de dados SQLite usando JDBC (Java Database Connectivity) e a ferramenta de build Gradle. O foco é apresentar operações CRUD (Create, Read, Update, Delete) e conceitos como Padrões de Projeto (Factory) e `PreparedStatement` para segurança e desempenho.

## Estrutura do Projeto

O projeto está organizado da seguinte forma:
<pre>
.
├── build.gradle              # Configurações do Gradle e dependências
├── settings.gradle           # Configurações de multiprojeto do Gradle
├── gradlew                   # Wrapper do Gradle para Linux/macOS
├── gradlew.bat               # Wrapper do Gradle para Windows
├── gradle/                   # Arquivos do Wrapper do Gradle
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── lab01.sqlite              # Banco de dados SQLite utilizado nos exemplos
└── src/
├── main/
│   └── java/
│       ├── bcd/
│       │   └── Principal.java     # Classe principal com o menu da aplicação
│       ├── exemplo01/
│       │   └── ExemploMuitoSimples.java # Exemplo de JDBC básico (Statement)
│       ├── exemplo02/
│       │   ├── db/
│       │   │   └── ConnectionFactory.java # Padrão Factory para conexões
│       │   └── PadroesDeProjeto.java # Exemplo usando ConnectionFactory
│       └── exemplo03/
│           └── UsandoPreparedStmt.java # Exemplo usando PreparedStatement
└── test/
└── java/
└── bcd/
└── TesteExemplo01SQLite.java # Testes unitários para o Exemplo 01
</pre<

## Tecnologias Utilizadas

* **Java Development Kit (JDK) 24**
* **Gradle** (Sistema de Build)
* **JDBC** (Java Database Connectivity)
* **SQLite** (Banco de dados embutido)
* **JUnit 5** (Para testes unitários)

## Como Rodar o Projeto

Siga os passos abaixo para configurar e executar a aplicação em seu ambiente local.

### Pré-requisitos

* **Java Development Kit (JDK) 24** instalado.
* **Git** instalado (para clonar o repositório).

### Passos para Execução

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/hac1997/bancoDados.git](https://github.com/hac1997/bancoDados.git)
    cd bancoDados
    ```

2.  **Configurar o `gradle.properties` (Muito Importante!):**
    Para garantir que o Gradle utilize o JDK 24 para a execução do build, é essencial configurar o arquivo `gradle.properties`.

    * Na pasta raiz do projeto (`bancoDados/`), crie um arquivo chamado `gradle.properties` (se ele já não existir).
    * Adicione a seguinte linha ao arquivo, substituindo o caminho pelo local onde seu JDK 24 está instalado:
        ```properties
        org.gradle.java.home=C:/Users/heric/Downloads/Oracle_JDK-24
        # Ou, se estiver no Linux/WSL e seu JDK estiver em um diretório diferente, ajuste o caminho
        # org.gradle.java.home=/path/to/your/jdk-24
        ```
        **Nota:** Use barras normais (`/`) no caminho mesmo no Windows para melhor compatibilidade com o Gradle.

3.  **Executar a Aplicação:**
    Abra um terminal na pasta raiz do projeto (`bancoDados/`) e execute o seguinte comando para construir e rodar a aplicação:

    * **No Windows:**
        ```bash
        .\gradlew.bat run
        ```
    * **No Linux/macOS (ou WSL):**
        ```bash
        ./gradlew run
        ```

    O programa exibirá um menu interativo no terminal, permitindo que você explore os diferentes exemplos.

4.  **Executar os Testes (Opcional):**
    Para rodar os testes unitários do Exemplo 01:

    * **No Windows:**
        ```bash
        .\gradlew.bat test
        ```
    * **No Linux/macOS (ou WSL):**
        ```bash
        ./gradlew test
        ```

## Explicação dos Exemplos

O projeto é dividido em três exemplos principais, cada um ilustrando um aspecto diferente do acesso a banco de dados com JDBC:

### Exemplo 01: `ExemploMuitoSimples`
* **Localização:** `src/main/java/exemplo01/ExemploMuitoSimples.java`
* **Conceito:** Demonstra as operações CRUD (Create, Read, Update, Delete) básicas em um banco de dados SQLite utilizando instruções SQL diretas e a interface `java.sql.Statement`. É uma abordagem simples, mas pode ser suscetível a problemas como SQL Injection se não for usada com cuidado.
* **Funcionalidades:** Cadastrar, alterar, excluir, listar por email e listar todas as pessoas.

### Exemplo 02: `PadroesDeProjeto` e `ConnectionFactory`
* **Localização:** `src/main/java/exemplo02/PadroesDeProjeto.java` e `src/main/java/exemplo02/db/ConnectionFactory.java`
* **Conceito:** Introduz o padrão de projeto **Factory** para centralizar a criação de conexões com o banco de dados (`ConnectionFactory`). Isso promove a separação de responsabilidades, o encapsulamento dos detalhes de conexão e a reutilização de código, melhorando a organização e manutenção do projeto.
* **Funcionalidades:** Demonstra a listagem de pessoas utilizando a conexão fornecida pela Factory.

### Exemplo 03: `UsandoPreparedStmt`
* **Localização:** `src/main/java/exemplo03/UsandoPreparedStmt.java`
* **Conceito:** Foca no uso da interface `java.sql.PreparedStatement` do JDBC. Esta é a forma recomendada de executar comandos SQL, pois oferece:
    * **Segurança:** Previne ataques de **SQL Injection** ao tratar os parâmetros como dados, e não como parte da instrução SQL.
    * **Desempenho:** Prepara (pré-compila) a instrução SQL uma vez e permite a execução repetida com diferentes parâmetros, otimizando o acesso ao banco.
* **Funcionalidades:** Listar todas as pessoas, listar uma pessoa por ID e atualizar o e-mail de uma pessoa de forma segura.

## Aluno

* **Herick Ac**
* GitHub: [hac1997](https://github.com/hac1997)