### Projeto de Teste de Automação

Este projeto contém testes automatizados para cenários de API e Web. Abaixo estão as instruções para configuração do ambiente e execução dos testes.

#### Configuração do Ambiente

1. **Java Development Kit (JDK)**:
    - Certifique-se de ter o JDK instalado em sua máquina. Você pode baixar e instalar a versão mais recente do [site oficial da Oracle](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

2. **Gradle**:
    - O projeto utiliza o Gradle como gerenciador de dependências e construtor. Certifique-se de ter o Gradle instalado. Você pode seguir as instruções de instalação disponíveis na [documentação oficial do Gradle](https://gradle.org/install/).

3. **Configuração do Build**:
    - Copie o conteúdo do arquivo `build.gradle` disponibilizado neste projeto para o arquivo `build.gradle` do seu projeto.

4. **Allure**:
    - O projeto utiliza o Allure para geração de relatórios de testes. Certifique-se de ter o Allure instalado. Você pode encontrar as instruções de instalação na [documentação oficial do Allure](https://docs.qameta.io/allure/#_installing_a_commandline).

#### Execução dos Testes

Para executar os testes automatizados, siga os passos abaixo:

1. Abra um terminal na pasta raiz do projeto.

2. Execute o seguinte comando para rodar os testes:

```shell
./gradlew test
```

Este comando irá executar os testes configurados no projeto.

#### Utilização do Allure

O Allure é utilizado para geração de relatórios de testes com informações detalhadas sobre os resultados dos testes. Após a execução dos testes, siga os passos abaixo para visualizar os relatórios gerados:

1. Após a execução dos testes, execute o seguinte comando para gerar os relatórios Allure:

```shell
./gradlew allureReport
```

2. Após a conclusão da geração do relatório, execute o seguinte comando para abrir o relatório no navegador:

```shell
./gradlew allureServe
```

Isso abrirá o relatório no navegador padrão, onde você poderá visualizar os resultados dos testes de forma interativa.

Com estas instruções, você estará pronto para configurar o ambiente, executar os testes automatizados e visualizar os relatórios de testes gerados pelo Allure.
