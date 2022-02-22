# microorder


## Link your service to spring boot admin
Add the dependency in your `pom.xml`
```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>2.6.2</version>
</dependency>
```

Add this in your application.yml :
```yml
spring:
  boot:
    admin:
      client:
        url: http://localhost:9999 # Or change the port if needed
  application:
    name: customer-service # Change with the name of your service
management:
  endpoints:
    web:
      exposure:
        include: "*"
    health:
      show-details: always
```

![](http://www.plantuml.com/plantuml/svg/ZP4nJyCm48Lt_mfB34mM0QJITchL0GOgaDe58GPJNzCJzhbo79N-UowXY2BP3hTaoFlUdT-RDL67s3ehDjxjFG55-QgDGv9ApYdkF1tbT2edF6_ouX7hNRmF07IkttZQOjq15ZurcG0TSZUkgYnIwmW7tLkFgt5Ujs0xD3fsvYOWq8cuOU85hFM7S_NJTikxpIMulVN8p0DGm1DocqGM5ldxvx5yztLQwvtZUu3LIzqqH_ljNF4nAVawj9iFrgu3Sie_bwUjbq2jl98BBgEpNw7pFJ3YIGBFli68B68e085kkpUy6x1zzZn9R1LzVzESTFGM-XMJqUHNAqgHgy68nM07IkMJ4VawL35-KVfPN9WjIPLBAQBgN_s8WTPLlYuXPa2cTVOB)
