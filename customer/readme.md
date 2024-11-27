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



- ![Screenshot from 2024-11-26 22-25-47.png](..%2F..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-11-26%2022-25-47.png)


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

- ![Screenshot from 2024-11-26 22-25-58.png](..%2F..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-11-26%2022-25-58.png)
- ![Screenshot from 2024-11-26 22-24-09.png](..%2F..%2F..%2F..%2FPictures%2FScreenshot%20from%202024-11-26%2022-24-09.png)


