![Last Commit](https://img.shields.io/github/last-commit/MateusLeviDev/springboot3-jpa-postgres)

<details>
  <summary>feature/BLUE20250604-google-cloud-pub-sub-pattern</summary>

  ### o módulo implementa um subscriber GCP Pub/Sub

  - PubSubConfig: responsável por configurar o mecanismo de consumo da fila. Define os canais de comunicação MessageChannel ("caminho") usados pela Spring Integration. Configura o adaptador PubSubInboundChannelAdapter para conectar a subscription do Pub/Sub à pipeline Spring Integration.
  - PubSubInboundChannelAdapter: faz a ponte entre Google Pub/Sub e o Spring Integration, lê a mensagem, converte para DTO, envia para um canal Spring.
  - MessageChannel (DirectChannel): canal que entrega a mensagem para o método consumidor.
  - @ServiceActivator: método que processa a mensagem, com acesso ao payload e à mensagem original (para ack/nack manual).
  - usando Strategy pattern para delegar comportamentos a classes com interfaces em comum. (Open/Closed Principle?)
  - usando Registry para dispatch centralizado 

</details>

---


<details>
  <summary>eature/BLUE20250717-aws-s3-lambda</summary>

  - simulando AWS localmente
  - exemplos de uso
  - base para estudo de open tofu e automatizações a partir disso
  - confira branch

</details>
