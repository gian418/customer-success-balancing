# Customer Success Balancing API [![Build Status](https://travis-ci.org/gian418/customer-success-balancing.svg?branch=main)](https://travis-ci.org/gian418/customer-success-balancing)

#### Instruções de execução
API foi desenvolvida utilizando JAVA 11 e Maven. Para rodar a aplicação através do console basta executar o comando na raiz do projeto:
```
mvn spring-boot:run
```

#### Documentação da API
Está sendo utilizado o Swagger para documentar esta API. Para acessar, basta abrir o seguinte link com o projeto rodando:
```
http://localhost:8080/swagger-ui.html
```

#### Integração Continua [![Build Status](https://travis-ci.org/gian418/customer-success-balancing.svg?branch=main)](https://travis-ci.org/gian418/customer-success-balancing)
Está sendo utilizado o Travis CI para realizar o deploy no Heroku.
Travis CI da API:
```
https://travis-ci.org/github/gian418/customer-success-balancing
```
Aplicação rodando no Heroku:
```
https://customer-success-balacing.herokuapp.com/swagger-ui.html
```
Obs: é possivel que o primeiro acesso demore um pouco, pois a aplicação "adormece" quando fica um tempo inativa.

#### Utilizando a API
O endpoint para fazer a requisição se estiver rodando local:
```
POST http://localhost:8080/rest/balancear
```
ou para o ambiente no Heroku:
```
POST https://customer-success-balacing.herokuapp.com/rest/balancear
```

Exemplo do JSON para adicionar ao corpo do POST:
```
{
  "gerentes": [
      {
          "id": 1,
          "score": 60
      },
      {
          "id": 2,
          "score": 20
      },
      {
          "id": 3,
          "score": 95
      },
      {
          "id": 4,
          "score": 75
      }
  ],
  "clientes": [
    {
          "id": 1,
          "score": 90
      },
      {
          "id": 2,
          "score": 20
      },
      {
          "id": 3,
          "score": 70
      },
      {
          "id": 4,
          "score": 40
      }
      ,
      {
          "id": 5,
          "score": 60
      }
      ,
      {
          "id": 6,
          "score": 10
      }
  ],
  "gerentesIndisponiveis": [
    2,
    4
  ]
}
```
Este JSON é baseado no primeiro cenário de testes

É possível executar pelo proprio Swagger ou utilizando uma ferramenta como o Postman

#### Bibliotecas e frameworks utilizados
* Spring Boot 2.3.4
* Swagger 2.9.2
* Google GSON 2.86