### 1. Pub/Sub Pattern e Conceitos

O Pub/Sub é um modelo de comunicação assíncrona em sistemas distribuídos.
Ele permite que produtores (publishers) enviem mensagens para um tópico, e consumidores (subscribers) recebam essas mensagens por meio de subscriptions.

- Publisher: O sistema que publica mensagens em um tópico.
- Subscriber: O sistema que consome mensagens de um tópico usando uma subscription.
- Tópico: Um canal de comunicação que recebe mensagens do publisher.
- Subscription: A "assinatura" de um tópico que entrega mensagens para os consumidores.
- Comunicação Assíncrona: não precisa esperar por uma resposta direta do consumidor (subscriber).

Vantagens:

    Desacoplamento entre os sistemas.
    Maior escalabilidade.
    Tolerância a falhas (se um consumidor estiver temporariamente offline, ele ainda pode processar mensagens posteriormente).

- Push: O GCP envia as mensagens para um endpoint HTTP configurado.
- Pull: Os consumidores solicitam as mensagens ativamente.
- Suporte a dead-letter topics para mensagens que falham no processamento.
- cod atual -> abordagem Pull Delivery no GCP Pub/Sub

No Pull Delivery, o consumidor (seu microserviço) puxa as mensagens do Pub/Sub ativamente. Ou seja:

    A aplicação solicita mensagens manualmente da subscription.
    As mensagens não são enviadas automaticamente para um endpoint; é o consumidor que controla o fluxo.

```
adapter.setAckMode(AckMode.MANUAL);

```

- AckMode.MANUAL significa que você confirma manualmente a mensagem após processá-la, o que é comum no Pull Delivery.


ara usar Push Delivery:

    Configure a subscription no GCP Console ou via CLI, apontando para um endpoint HTTP/S.
    O servidor deve expor um endpoint REST (HTTP POST) para receber mensagens.
    Ideal para endpoints HTTP já existentes.

Atributo "googclient_deliveryattempt"

O atributo googclient_deliveryattempt é um atributo especial adicionado automaticamente pelo Pub/Sub para rastrear o número de tentativas de entrega de uma mensagem. Esse atributo:

    Indica quantas vezes a mensagem foi entregue ao cliente (consumidor).
    É especialmente útil quando o cliente (consumidor) falha ao processar a mensagem e ela precisa ser reentregue.
    É especialmente útil quando o cliente (consumidor) falha ao processar a mensagem e ela precisa ser reentregue.



- service account
- dar permissoes IAM - pubsub admin
- pode ser pelo terminal
- ajustar yamnl com as credentials
- criar DLQ e associar a sub principal