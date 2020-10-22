# Web Solution
==============


Requisitos de software
---

 - Java JDK 1.8+
 - Maven 3+
 - Eclipse/Intellij
 

Comando do maven para build do projeto

```javascript
  mvn clean install
```

Comando para iniciar o projeto 

```javascript
   mvn spring-boot:run
```

Requisição listagem de serviços disponíveis:

```javascript
   http://localhost:8080/WebSolution/swagger-ui/index.html
```

Exemplo para listar os usuários

```javascript
curl -X GET "http://localhost:8080/WebSolution/api/users/" -H  "accept: application/json"
```
Exemplo para criar um user 

```javascript
   curl -X POST "http://localhost:8080/WebSolution/api/users/create" -H  "accept: application/json" -H  "Content-Type: application/json" -d "{\"id\":0,\"name\":\"Daniel\",\"email\":\"teste@teste.com\",\"status\":\"A\"}"
```





