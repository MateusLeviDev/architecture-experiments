## Apache Kafka 

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

cd opt/bitnami/kafka/bin


- f you want to run apache cli scripts within Docker container, then you might want use this DNS name 
- create a env file 

HOSTNAME:host.docker.internal ----> use this DNS name, it should automatically resolve to internal IP address of the host.

Por padrão, os contêineres Docker são isolados do host e uns dos outros em termos de rede. Isso significa que eles têm suas próprias interfaces de rede e IPs.
host.docker.internal é um hostname especial fornecido pelo Docker para permitir que os contêineres acessem serviços que estão sendo executados no host. É uma forma conveniente de resolver o IP do host a partir de dentro de um contêiner.
Esse case ocorre por que o locahost pode não estar available no Docker Container 

> docker compose --env-file environment.env up

#### Adicionando a entrada dentro do contêiner Docker garante que o hostname host.docker.internal seja corretamente resolvido dentro do ambiente isolado do contêiner. -> isso deve funcionar para o trecho antigo abaixo, o novo yml rodou sem precisar dessas configs

```
version: '3.8'  -> testar rodar normalmente sem a version 

services:
  kafka-1:
    image: bitnami/kafka:latest
    user: 'root'
    ports: 
      - "9092:9092"
    environment:
      - KAFKA_CFG_NODE_ID=1
      - KAFKA_KRAFT_CLUSTER_ID=WnLkTHhk
      - KAFKA_CFG_PROCESS_ROLES=controller,broker
      - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka-1:9091,2@kafka-2:9091,3@kafka-3:9091
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9090,CONTROLLER://:9091,EXTERNAL://:9092
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka-1:9090,EXTERNAL://${HOSTNAME:-localhost}:9092
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,EXTERNAL:PLAINTEXT,PLAINTEXT:PLAINTEXT
      - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
      - KAFKA_CFG_INTER_BROKER_LISTENERS_NAME=PLAINTEXT
    volumes:
      - kafka-data:/bitnami/kafka

  kafka-2:
    image: bitnami/kafka:latest
    user: 'root'
    ports: 
      - "9094:9094"
    environment:
      - KAFKA_CFG_NODE_ID=2
      - KAFKA_KRAFT_CLUSTER_ID=WnLkTHhk
      - KAFKA_CFG_PROCESS_ROLES=controller,broker
      - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka-1:9091,2@kafka-2:9091,3@kafka-3:9091
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9090,CONTROLLER://:9091,EXTERNAL://:9094
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka-2:9090,EXTERNAL://${HOSTNAME:-localhost}:9094
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,EXTERNAL:PLAINTEXT,PLAINTEXT:PLAINTEXT
      - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
      - KAFKA_CFG_INTER_BROKER_LISTENERS_NAME=PLAINTEXT
    volumes:
      - kafka-data:/bitnami/kafka

  kafka-3:
    image: bitnami/kafka:latest
    user: 'root'
    ports: 
      - "9096:9096"
    environment:
      - KAFKA_CFG_NODE_ID=3
      - KAFKA_KRAFT_CLUSTER_ID=WnLkTHhk
      - KAFKA_CFG_PROCESS_ROLES=controller,broker
      - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=1@kafka-1:9091,2@kafka-2:9091,3@kafka-3:9091
      - KAFKA_CFG_LISTENERS=PLAINTEXT://:9090,CONTROLLER://:9091,EXTERNAL://:9096
      - KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka-3:9090,EXTERNAL://${HOSTNAME:-localhost}:9096
      - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,EXTERNAL:PLAINTEXT,PLAINTEXT:PLAINTEXT
      - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
      - KAFKA_CFG_INTER_BROKER_LISTENERS_NAME=PLAINTEXT
    volumes:
      - kafka-data:/bitnami/kafka

volumes:
  kafka-data:
```

> ip addr show docker0

dentro do container

> echo "172.17.0.1 host.docker.internal" >> /etc/hosts

- must work


link: https://stackoverflow.com/questions/75562702/kafka-3-4-with-kraft-without-zookeeper-with-3-brokers

O parâmetro --bootstrap-server é utilizado para especificar o broker do Kafka ao qual o cliente se conectará inicialmente. Este broker serve como ponto de entrada para o cliente descobrir o restante dos brokers no cluster Kafka. Basicamente, é o endereço do broker que o cliente Kafka utilizará para começar a comunicação com o cluster.

Listeners: Cada broker precisa de portas distintas para os listeners EXTERNAL e CONTROLLER. Isso garante que cada broker possa ser acessado individualmente e que a comunicação interna do cluster (KRaft) funcione corretamente.
Evitando Conflitos: Usar portas diferentes no host (9092, 9094, 9096 para EXTERNAL e 9091, 9093, 9095 para CONTROLLER) garante que não haja conflitos de porta quando os contêineres estão rodando simultaneamente na mesma máquina.

O uso de HOSTNAME=host.docker.internal em variáveis de ambiente dentro de containers Docker é uma prática comum para facilitar a comunicação entre containers e com o host onde o Docker está sendo executado.

Quando você define HOSTNAME=host.docker.internal em uma variável de ambiente dentro de um container Docker, o Docker automaticamente traduz isso para o endereço IP do host Docker. Isso é útil especialmente quando seus containers precisam se comunicar com serviços que estão sendo executados no host Docker (por exemplo, Kafka, banco de dados, serviços externos, etc.).

Por exemplo, no seu caso com o Kafka, você configurou KAFKA_CFG_ADVERTISED_LISTENERS=PLAINTEXT://kafka-1:9090,EXTERNAL://${HOSTNAME:-localhost}:9092. Aqui, ${HOSTNAME:-localhost} será substituído por host.docker.internal, permitindo que o Kafka anuncie um listener acessível de dentro do container Docker usando o endereço IP do host Docker.

Essa prática evita a necessidade de configurar manualmente IPs estáticos ou DNS personalizados, o que simplifica bastante o gerenciamento de redes em ambientes Dockerizados.