1. Pull kafka image
    ``` 
    docker pull apache/kafka:3.8.0 
    ```

2. Run kafka cluster
    ```
    docker run -d -p 9092:9092 --name broker apache/kafka:3.8.0
    
    
```
depois listar 

docker container ps

docker exec -it <id> bash

cd opt/kafka/bin/

executar comandos lá

```


- o spring kafka consome ou produz os tópicos
- o comportamento padrão -> `auto.create.topics.enable=true`
- destination -> são os topicos
- transactionConsumer-in-0: padrão estabelecido pelo spring cloud. convensão de nome para input e output
- in-0. -> indica que pode haver mult canais para 1 função
