# Gateway SMS
==============


Requisitos de software
---

 - Java JDK 1.8+
 - Maven 3+
 - Eclipse
 

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
   curl localhost:8080/
```

Console H2

```javascript
   curl localhost:8080/console
```

Exemplo para enviar um SMS 

```javascript
   curl -i -X POST -H "Content-Type:application/json" -d '{"from":"999999999","to":"888888888","body":"teste"}' http://localhost:8080/sms/send
```
Exemplo para enviar um SMS 

```javascript
   curl -i -X POST -H "Content-Type:application/json" -d '{"from":"999999999","to":"888888888","body":"teste","validade":"02/12/2017"}' http://localhost:8080/sms/send
```


