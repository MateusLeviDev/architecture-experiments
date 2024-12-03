#### in order to start creating a event driven ms, using the pub/sub pattern

```
      <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-stream-kafka</artifactId>
      </dependency>
        
```

#### Anemic x Rich Domain

- Anemic Domain Model (Modelo de Domínio Anêmico) é um padrão anti-pattern que ocorre quando as classes de domínio (ou entidades) contêm apenas atributos e getters/setters, sem encapsular lógica de negócio. 
- rich-domain model: an object model of the domain that incorportaes both vehaviour and data. follows the object-oriented principles
- rich: bring data and logic together
- anemic-domain: Viola o princípio da orientação a objetos que diz que os dados e os comportamentos relacionados devem estar no mesmo lugar.
- anemic: Violação do princípio de coesão: Um objeto deve ser responsável por gerenciar seus próprios dados e comportamentos, sem depender de outros objetos para manipular seus dados diretamente.
- A falta de encapsulamento ocorre quando os dados e os comportamentos relacionados a esses dados não estão bem organizados juntos em uma única unidade, como em uma classe. Em orientação a objetos (OO), o encapsulamento é um princípio fundamental que estabelece que os dados de um objeto (atributos) devem ser privados e acessados/modificados apenas por métodos (funções) dessa mesma classe, que controlam o comportamento do objeto.
- Se os dados e comportamentos estão dispersos, como quando os dados ficam fora da classe ou são acessados diretamente por outras partes do sistema, o objeto perde seu controle sobre si mesmo.
- instead of having just a class which contain getter and setters we are going to impl a rich domain model. `bring behaviour and data`


###### o que faz sua entity, domain a entity? sem ser apenas a annotation @Entity?

- o `@Id` -> id. identificamos ele usando o id

#### Value Objects

- is immutable. or should be
- have a zero lifespan
- not persisted independently
- we compare two dff value objects (ex: name) by the `value`, ou seja, dois Value Objects com os mesmos valores são considerados iguais, independentemente de sua localização ou instância

###### qual vantagem de uso?

Simplicidade: Ao usar Value Objects, você pode representar conceitos de forma mais simples e natural, sem a sobrecarga de lidar com identidade.

Imutabilidade: A imutabilidade dos Value Objects facilita o controle sobre o sistema e evita efeitos colaterais indesejados, já que você não pode alterar o estado de um Value Object depois que ele é criado.

Reusabilidade: Como os Value Objects são definidos apenas por seus atributos, eles podem ser reutilizados facilmente. Por exemplo, o objeto Address pode ser utilizado em diferentes partes do sistema, sem que seja necessário recriar ou reconfigurar a lógica.


- `we dont wnat to leave validation to our controller, using jakarta ou javax validation????` o controller deve ser responsável apenas por manipular as requisições e delegar a lógica de negócios para serviços ou outras camadas de infraestrutura.
- `por que nao estamos usando os setters. por que ter cuidado onde usar setters methods??` O uso de setters compromete a imutabilidade dos objetos, ou seja, você permite que o estado de um objeto seja alterado após sua criação. Em uma arquitetura orientada a objetos ou de Domain-Driven Design (DDD), as entidades devem ser imutáveis sempre que possível



- ![Screenshot from 2024-11-26 22-25-47](https://github.com/user-attachments/assets/4c906370-8278-4f07-bc61-4138ca017185)


#### DDD

Domain-Driven Design is an approach to software development that centers the development on programming a domain model that has a rich understanding of the processes and rules of a domain.

- https://martinfowler.com/bliki/DomainDrivenDesign.html

- key conceptual elements: At the heart of this was the idea that to develop software for a complex domain, we need to build `Ubiquitous Language`

`Ubiquitous Language` is the term Eric Evans uses in Domain Driven Design for the practice of building up a common, rigorous language between developers and users. This language should be based on the Domain Model used in the software - hence the need for it to be rigorous, since software doesn't cope well with ambiguity.

Evans makes clear that using the ubiquitous language in conversations with domain experts is an important part of testing it, and hence the domain model. He also stresses that the language (and model) should evolve as the team's understanding of the domain grows.


    By using the model-based language pervasively and not being satisfied until it flows, we approach a model that is complete and comprehensible, made up of simple elements that combine to express complex ideas.

    ...

    Domain experts should object to terms or structures that are awkward or inadequate to convey domain understanding; developers should watch for ambiguity or inconsistency that will trip up design.

    -- Eric Evans

- Extreme Programming (XP) is a software development methodology:  XP was one of the first agile methods, indeed XP was the dominant agile method in the late 90s and early 00s before Scrum became dominant as the noughties passed

Kent describes XP by a progression of ideas from broad and abstract values through principles, to concrete practices - a progression that I find useful to apply in many other contexts. It popularized many practices that have since been widely used in software development, including: continuous integration, refactoring, TestDrivenDevelopment, and agile planning. I particularly like its combination of technical and management practices, which make it a good fit for reaching the delivering zone of agile fluency


#### 1 - Usando StreamBridge

- ![Screenshot from 2024-11-26 22-25-58](https://github.com/user-attachments/assets/576e24a8-8cc4-4d13-b892-fab424acf1a9)

- ![Screenshot from 2024-11-26 22-24-09](https://github.com/user-attachments/assets/4ea9f685-ca44-4cc6-a985-8c3e8ab5aac1)

#### 2 - using functional style

Supplier -> use as the source of the data. like the producer. quando queremos produzir data usando supplier
- supplier does not subscribe to any channel.
- has to be triggered by some other mechanism(s)
- usando o Supplier<Object> xxx() { `() -> { }` }   -----> este trigger pooling data every second (creating data)

- pesquisar sobre outbox transaction pattern 


#### 3 - reactive APIs

- creating a new bean to send the data
- app onde os eventos de clientes precisam ser processados reativamente, por exemplo, podemos usar o sink para emitir data
- o que é flux? 

#### como esse `private final Sinks.Many<CustomerEvent> customerProducer;` sabe que vai chamar o customerEventSink?

- garantir que o bean configurado seja injetado corretamente no serviço. O Spring fará o mapeamento automaticamente com base no contexto.
- o método `customerCreatedEventStream` cria um bean do tipo `Sinks.Many<CustomerEvent>`
- bean configurado para emitir eventos usando um ReplayProcessor com o valor mais recente. Ou seja, ele pode reemitir o último evento se necessário.
- ou seja, em seguida esse bean encapsula o `Sinks.Many<CustomerEvent>` em um `Supplier<Flux<CustomerEvent>>`

```

- Sinks.Many é um tipo de sink que pode ser usado para emitir dados para múltiplos consumidores.
- O asFlux() transforma esse sink em um fluxo (Flux). O Flux é a representação de um fluxo de elementos no Reactor, ou seja, uma sequência de valores que pode ser consumida de forma assíncrona e reativa.
- customerCreatedEventStream() retorna um Sinks.Many<CustomerEvent>. Esse sink irá armazenar e emitir os eventos CustomerEvent para quem assinar o fluxo. [ver yaml, testar definir mult topics nesta definition]
- asFlux() é útil quando você quer permitir que múltiplos consumidores possam ouvir e processar os dados emitidos por um único ponto de emissão (o sink)

```

- O Spring Cloud Stream utiliza esse Supplier para vincular o produtor ao tópico Kafka configurado no application.properties. --> .yaml
- Quando o customerProducer chama tryEmitNext, ele envia o evento para o fluxo configurado, que será consumido pelo Supplier<Flux<CustomerEvent>>.
- `@Configuration` --->  indica que a classe contém definições de beans do Spring e que esses beans devem ser registrados no Application Context (container central que gerencia o ciclo de vida dos beans e fornece funcionalidades)
- Classes anotadas com @Configuration são proxies gerenciados pelo Spring. Isso significa que o Spring garante que cada bean seja um singleton (existe apenas uma instância), mesmo que o método seja chamado várias vezes
- use case: No caso de Sinks.Many<CustomerEvent>, se você tivesse várias instâncias dessa classe, cada uma consumiria recursos para gerenciar os fluxos. Usando o escopo singleton, o Spring cria apenas uma instância de Sinks.Many<CustomerEvent>, e ela pode ser reutilizada em toda a aplicação, o que economiza recursos e melhora a eficiência.
- estado mantido consistentemente em toda a aplicação
- No Spring WebFlux a operação pode ser tratada de maneira assíncrona, onde o fluxo de dados vai sendo construído e consumido enquanto a operação de I/O (request HTTP ou acesso ao banco) não está bloqueando o thread do servidor.
- reactive -> paradigma
- Sinks.Many<CustomerEvent> é instanciado diretamente na classe de serviço porque ele é o canal de emissão de eventos. Sua responsabilidade é emitir eventos para os consumidores reativos. A classe de serviço é onde você emite esses eventos com o uso do método tryEmitNext().
- CustomerMessaging é apenas uma classe de configuração e não deve ser usada diretamente em sua lógica de serviço. Sua função é criar e registrar beans no contexto do Spring para que outros componentes, como o Sinks.Many<CustomerEvent>, possam ser utilizados em outras partes do código.

- https://docs.docker.com/guides/kafka/


#### Consumer

![Screenshot from 2024-11-27 15-48-11](https://github.com/user-attachments/assets/3f9d67ba-358d-4555-b913-4956f5cb7efc)


- function: pure java. accepts a message input and producer one or more messages. entender o conceito de transformer, for example enterprise integration patterns or translator 
- podemos transformar as mensagens, ou seja, ajustando conforme nosso payload

- use cases:
![Screenshot from 2024-12-03 18-58-08](https://github.com/user-attachments/assets/882cc403-326d-4933-958c-804002201faf)


![Screenshot from 2024-12-03 19-00-26 (copy)](https://github.com/user-attachments/assets/bb43bb47-7454-49ee-a89c-83fdd2df238e)

![Screenshot from 2024-12-03 18-59-54](https://github.com/user-attachments/assets/ea779b79-fd87-4cf9-a062-9d30d9995983)

