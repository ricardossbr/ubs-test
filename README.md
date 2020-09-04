###ubsbank

---

## Descrição do projeto
Esse é um projeto de teste, usando Java 12 para leitura, gravação e procesamento de arquivos.

---

## Começando

Para executar o projeto, será necessário instalar os seguintes programas:
```yaml
1. JDK 8+
2. Apache Maven 3.6. 3
3. MongoDB 3.8 ou Docker version 19.03.12
```

---

## Desenvolvimento

Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:

```yaml
1. cd /pasta-de-preferencia
2. git clone https://github.com/ricardossbr/ubs-test.git
```

---

## Construção

Para construir o projeto com o Maven, executar os comando abaixo:
```yaml
1- mvn clean install
```
O comando irá baixar todas as dependências do projeto e criar um diretório target com os artefatos construídos, que incluem o arquivo jar do projeto. Além disso, serão executados os testes unitários, e se algum falhar, o Maven exibirá essa informação no console.

---

## Configuração

Para executar o projeto, é necessário utilizar sua IDE de preferência, e acessar a pasta `infrastructure` dentro do projeto, e executar o seguinte comando, via terminal:
```yaml
docker-compose up
````

Caso já tenha o MongoDB local, configurar o arquivo: **application.properties** dentro da pasta: **src/main/resources** do projeto, e alterar as informações, conforme abaixo:

```yaml
spring.data.mongodb.database=${SPRING_MONGODB_DATABASE:ubs-bank}
spring.data.mongodb.host=${SPRING_MONGODB_HOST:localhost}
spring.data.mongodb.port=${SPRING_MONGODB_PORT:27017}
spring.data.mongodb.username=${SPRING_MONGODB_USERNAME:ubs-bank}
spring.data.mongodb.password=${SPRING_MONGODB_PASSWORD:ubs-bank}
````
*Obs* mudar as configurações abaixo: 
   *SPRING_MONGODB_USERNAME:* usuario local do mongo, *SPRING_MONGODB_PASSWORD:* senha local do mongo

---
    
## Testes
Para rodar os testes, utilize o comando abaixo:
```yaml
mvn test
```

## Links
Utilize as url`s abaixo:
```yaml
1 - localhost:8080/getfile 
2 - localhost:8080/product?product-name=EMMS&quantity-store=20 
```
1 - Url para leitura e gravação do arquivo

2 - Para calculo do dados... *Obs*: e preciso passar os parametros **produc-name**:nome do produto  e **quantity-store**: quantidade de lojas que será distribuido o produto

