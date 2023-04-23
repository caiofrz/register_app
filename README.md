
# register_app

Projeto de API REST de cadastro de usuários em Java

![preview](https://devporai.com.br/wp-content/uploads/2021/01/O-que-e-CRUD-740x414.jpg)

## Stack utilizada

**Back-end:** Java 17, Spring e MySQL.

**Front-end:** HTML5, CSS3 e JavaScript.

**Apps auxiliares:** VSCode e Postman.




## Documentação da API


### Recuperar usuários

```http
  GET localhost:8080/users
```
### Recuperar usuário específico

```http
  GET localhost:8080/user/{id}
```
##### Exemplo de retorno
```bash
    {
        "id": 1,
        "name": "Caio",
        "email": "caiofa@email.com",
        "password": "$2a$10$c5tWiKU7WQLMN.V2UL0NY.OxRI33ntRREBN5c2KeK649/ek/7Hbtm",
        "phone": "33988237292"
    },
```
### Cadastrar usuário

```http
  POST localhost:8080/user
```

#### O cadastro pode ser feito através da página raíz da aplicação
### Atualizar cadastro de usuário

```http
  PUT localhost:8080/user/{id}
```
##### Exemplo de corpo para requisição POST e PUT
```bash
    {
        "name": "Caio Ferraz",
        "email": "caio@email.com",
        "password": "12345678",
        "phone": "33988230000"
    },
```
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `name`      | `string` | **Obrigatório**. O nome do usuário|
| `email`      | `string` | **Obrigatório**. O email do usuário|
| `password`      | `string` | **Obrigatório**. A senha do usuário|
| `phone`      | `string` | **Obrigatório**. O número de telefone do usuário|

### Deletar usuário
```http
  DELETE localhost:8080/user/{id}
```


## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/caiofrz/register_app.git
```

Entre no diretório do projeto

```bash
  cd register_app
```
### Configure o banco

É preciso que você tenha o MySQL instalado na sua máquina. Caso não tenha, acesse o [site](https://www.mysql.com) e instale.

O segundo passo é criar o banco. Para isso utilize o comando: 
```bash
  create database nomeDoBanco;
```

### Configurando a aplicação

Vá até o arquivo **application.properties** e altere as configurações. O caminho do arquivo é *src/main/resources*:

```bash
    spring.datasource.url=jdbc:mysql://localhost:porta/nomeDoBanco
```

| Parâmetro   | Exemplo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `porta`      | `3306` | Esse propriedade da url se refere a porta em que o MySQL estará rodando. A porta default é 3306|
| `nomeDoBanco`      | `usuarios` | Esse propriedade da url se refere ao nome do banco de dados que você criou|


```bash
    spring.datasource.username=root
    spring.datasource.password=root
```
| Parâmetro   | Exemplo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `username`      | `root` | Esse propriedade se refere ao seu nome de usuario que foi criado. O nome default é root|
| `password`      | `root` | Esse propriedade se refere a sua senha de usuario que foi criada|


Agora basta dar start na aplicação. O seu navegador deve abrir uma aba com a aplicação rodando.

Caso isso não aconteça a app estará rodando na porta *8080* da sua máquina. Acesse o navegador e digite: http://localhost:8080 e pronto.


## Feedback

Se você tiver algum feedback, por favor não deixe de dá-lo. 

Me contate através do [github](https://github.com/caiofrz) 
ou [LinkedIn](https://www.linkedin.com/in/caio-ferraz-almeida/) 


## Referência

 - [Template Figma](https://www.figma.com/file/UvTTs9kvgM8YKfpIauHzXc/Tela-de-Login%2FCadastro-(Community?node-id=1-2&t=Bol6xH58nwJOTYGj-0)) que usei como base para criar a tela de cadastro

- [Documentação do Spring](https://spring.io)
## Funcionalidades que pretendo implementar ao projeto

- Autenticação JWT
- Login: tela e autenticação do usuário

