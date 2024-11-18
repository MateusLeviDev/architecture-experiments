#### Introduction

- modern data processing and real-time event streaming
- muito usado no contexto de ms e EDA
- powerful and versatile tool
- nesse caso fazendo o Deploy with Docker Compose

Imagine you’re at a busy restaurant. There are lots of orders coming in, and the kitchen needs to keep track of them all. Each order is like a piece of information or a message.

Now, think of Apache Kafka as a super organized waiter. Instead of taking one order at a time and rushing to the kitchen, this waiter collects all the orders from different tables and groups them together. Then as previous orders get processed in the kitchen, the waiter delivers the new orders one by one.

- core: `a publish-subscribe model where messages are organized into logical divisions called topics`
- Producers publish messages on these topics, and consumers subscribe to them to receive the messages.

This decouples the production and consumption of data, allowing for asynchronous communication between different components of a distributed system.
- O producer não precisa saber quem vai consumir as mensagens.
- O consumer não precisa saber de onde exatamente vêm as mensagens.
- Eles só precisam concordar sobre o tópico que vão usar.
- O producer pode enviar mensagens para o Kafka sem esperar que o consumer as processe imediatamente.
- O consumer pode processar mensagens no seu próprio ritmo.

```

$ cd docker
$ docker compose -f docker-compose.kafka.yaml up -d

```


- useful link: https://itnext.io/kafka-cluster-without-zookeeper-ca40d5f22304
- useful link: https://medium.com/@ben.meehan_27368/spring-boot-3-crash-course-part-8-integrating-apache-kafka-dec884f4b5fb

#### Why does Kafka depend on Zookeeper?

Kafka uses Zookeeper as a Service Discovery tool, meaning each Kafka node that wants to find other nodes needs to discover their addresses and status through service discovery. Kafka also uses Zookeeper for other purposes such as Leader Election, Broker Discovery, Configuration Management, and Health Monitoring.

#### What is KRaft?

KRaft is a new algorithm developed by Kafka based on the Raft Consensus algorithm, which is suitable for achieving consensus between trusted parties. Kafka no longer needs Zookeeper because it can achieve its goals using the Raft consensus algorithm


```

em seguida, após criar os containers

curl http://localhost:8080


poderá ver o cluster criado

```

- o cluster Kafka será o conjunto dos três brokers configurados (kafka-1, kafka-2 e kafka-3).
- Kafka’s architecture is distributed, consisting of multiple brokers that form a Kafka cluster. Each broker is responsible for storing and managing a subset of the data across partitions. Partitions allow messages within a topic to be spread across multiple servers, enabling parallel processing and scalability.