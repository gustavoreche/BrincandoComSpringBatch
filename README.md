# BrincandoComSpringBatch
Este repositório é destinado a estudos com o Spring Batch

## Requisitos para rodar o projeto:
- `Maven`
  - [Link para instalar GRADLE](https://maven.apache.org/install.html)
- `Docker`
  - [Link para instalar DOCKER](https://docs.docker.com/docker-for-windows/install/)

Não precisa ter o banco de dados MariaDB instalado no seu ambiente, este estudo foi realizado com Docker. E foi configurado para que quando o Docker é "desligado", os dados persistidos no banco de dados sejam mantidos:

  - Para executar o Docker:
    - Entre na pasta raiz deste projeto(onde esta localizado os arquivos `pom.xml` e `docker-compose.yml`);
    - Abra o terminal(ou cmd) neste diretório;
    - Execute o comando `docker-compose up`;
  - Para restaurar a base de dados:
    - Entre na pasta raiz deste projeto(onde esta localizado os arquivos `pom.xml` e `docker-compose.yml`) e exclua o diretório `.docker`;
    - Abra o terminal(ou cmd) neste diretório;
    - Execute o comando `docker-compose down --rmi all`;
