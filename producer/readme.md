#### *init...*

- Docker

```
docker compose up -d
docker compose down ----> to down containers
docker compose down --volumes  ----> remover os volumes e os dados associados .yml

docker exec -it <id> bash
cd opt/bitnami/kafka/bin/ 

./kafka-topics.sh --bootstrap-server localhost:9092 --list
./kafka-topics.sh --bootstrap-server localhost:9092 --describe --topic product-created-events-topic
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic product-created-events-topic

```

- start pelo intellij ou executar um `mvn spring-boot:run`

- Request

```
curl -X POST http://localhost:8080/products \
-H "Content-Type: application/json" \
-d '{
  "title": "Senhor dos Anéis",
  "price": 10.00,
  "quantity": 2
}'

```

