# Stack Utilizada

* Java 11
* Spring Boot
* Amazon DynamoDB em ambiente local
* Docker

### Features

* Arquitetura hexagonal
* Diferentes profiles
* Hypermedia As The Engine Of Application State (HATEOAS)
* Spring Exception Handling
* Swagger (http://base-url/swagger-ui/)


### Implantar o DynamoDB localmente no computador

Para instanciar um Amazon dynamoDB local é necessário seguir os passos:

Criar o arquivo e salve-o como docker-compose.yml e deixar na pasta raiz do projeto

* Na pasta raiz rodar o seguinte comando.:
```bash
docker-compose up
```

* Para criar a tabela:

    * Faça a configuração do AWS CLI (pode utilizar dados fake) a partir de um terminal utilizando o seguinte comando:
    * ```bash
         aws configure
         ```

* Acessar a pasta do projeto usando o mesmo CMD acima, e executar o seguinte comando:
    *  ```bash
         aws dynamodb create-table --cli-input-json file://tabelas/Funcionario.json --endpoint-url http://localhost:8000
         ```

* Para listar as tabelas criadas:
    * ```bash
         aws dynamodb list-tables --endpoint-url http://localhost:8000
         ```

* Para deletar a tabela criada:
    * ```bash
          aws dynamodb delete-table --table-name Funcionario --endpoint-url http://localhost:8000
         ```

## REST API

### Listar todos funcionario
    GET /v1/funcionario

### Criar novo funcionario
    POST /v1/funcionario

### Buscar funcionario por id
    GET /v1/funcionario/{id}

### Deletar funcionario por id
    GET /v1/funcionario/{id}

### Guides
Referencias:

* [Implantar o DynamoDB localmente no computador](https://docs.aws.amazon.com/pt_br/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html)


