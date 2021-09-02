# API de Agenda de Contatos

Projeto construido no bootcamp Satander, disponibilizado pela Digital Innovation One (DIO). O projeto consiste em uma API para controle de ponto, e foi construida utilizando o *framework [Spring Boot](https://spring.io/projects/spring-boot)*.



Esse projeto inclui:

- Criação da API para *CRUD* (Create, Read, Update e Delete) de uma pessoa e seus telefones;

- Testes unitários.



Para executar o projeto no terminal, digite o seguinte comando:

```shell script
mvn spring-boot:run 
```



Após executar o comando acima, basta apenas abrir o seguinte endereço `http://localhost:8080` utilizando as rotas abaixo:



#### Rotas

Criar usuário

<div style="display:flex; flex-direction:row">
    <div style="background-color:#936ae4; border-radius:5px; width: 100px; color:white; text-align:center; ">
        <b>POST</b>
    </div> 
    <code>
        /api/v1/people
    </code>
</div>

**Corpo da requisição**

```json
{
	"firstName": "Lucas",
	"lastName":"Sargeiro",
	"cpf": "142.269.727-40",
	"birthDate": "03-10-2021",
	"phones":[
		{
			"type": "MOBILE",
			"number": "(11)8888-8888"
 		},
		{
			"type": "MOBILE",
			"number": "(11)7777-7777"
 		}
	]
}
```



Buscar todos os usuários

<div style="display:flex; flex-direction:row">
    <div style="background-color:green; border-radius:5px; width: 100px; color:white; text-align:center; ">
        <b>
            GET
        </b>
    </div>
    <code>
        /api/v1/people
    </code>
</div>



Buscar um usuário

<div style="display:flex; flex-direction:row">
    <div style="display:flex; flex-direction:row">
    <div style="background-color:green; border-radius:5px; width: 100px; color:white; text-align:center; ">
        <b>
            GET
        </b>
    </div>
    <code>
        /api/v1/people/{id}
    </code>
</div>

- *{id}* - Identificador do usuário que está sendo buscado



Atualizar um usuário

<div style="display:flex; flex-direction:row">
    <div style="background-color:orange; border-radius:5px; width: 100px; color:white; text-align:center; ">
        <b>
            PUT
        </b>
    </div>
    <code>
        /api/v1/people/{id}
    </code>
</div>

- *{id}* - Identificador do usuário que está sendo buscado

```json
{
  "id": 1,
  "firstName": "Lucas",
  "lastName": "Sargeiro",
  "cpf": "142.269.737-12",
  "birthDate": "03-06-2021",
  "phones": [
    {
      "id": 1,
      "type": "MOBILE",
      "number": "(11)8888-8888"
    },
    {
      "id": 2,
      "type": "MOBILE",
      "number": "(11)7777-7777"
    }
  ]
}
```



Excluir um usuário

<div style="display:flex; flex-direction:row">
    <div style="background-color:red; border-radius:5px; width: 100px; color:white; text-align:center; ">
        <b>
            DELETE
        </b>
    </div> 
    <code>
        /api/v1/people/{id}
    </code>
</div>

- *{id}* - Identificador do usuário que está sendo buscado



------

### Banco de Dados

Para o banco foi utilizado o H2. A página de administração do banco pode ser acessada por [aqui](http://localhost:8080/h2).

