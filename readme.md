## Apache KAfka 

#### kRaft Configuration
mecanismo de consenso original baseado no Apache ZooKeeper. Zookeeper será descontinuado

- Usando volumes dentro da pasta, neste caso específico. necessário dar as permissões 
❯ sudo chmod -R 777 /{your_path}/architecture-experiments/volumes/server-1  

- must work

- Usando os volumes gerenciados pelo docker -> recomendado

```
    volumes:
      - kafka-data:/bitnami/kafka

volumes:
  kafka-data:
```

#### Container - bash
❯ docker exec -it <id> /bash or /bin/bash
