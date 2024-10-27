[Java Design Patterns Tutorial](https://www.geeksforgeeks.org/java-design-patterns/)

<br>


### Use Cases of the Factory Method Design Pattern

Below are the use cases of factory method:

    JDBC uses factories to create connections and statements. Frameworks like Spring and Guice utilize factories for managing beans.
    Swing and JavaFX uses factories to produce UI components such as buttons and text fields, offering flexibility in design.
    Tools like Log4j and Logback employ factories to create loggers with various configurations, allowing for control over logging levels.
    Serialization frameworks use factories to generate objects from serialized data, accommodating different formats and versions.
    to encapsulate object creation
    to centralize class selection code 
    when you dont know ahead of time what class object you need

<br>

### Template method Case

A expressão T extends CustomTariff no contexto de public abstract class AbstractCustomTariffService<T extends CustomTariff> é um exemplo de generics 

- Generics permitem que você defina classes, interfaces e métodos com parâmetros de tipo. evitando cast

A classe abstrata: Reutilização de Código.  pode lidar com diferentes tipos de objetos.

- Restrições de Tipo: Ao impor que T deve estender CustomTariff, você garante que qualquer implementação concreta da classe AbstractCustomTariffService só possa ser usada com classes que herdam de CustomTariff
- Polimorfismo: Isso também permite o uso de polimorfismo. Como T é garantido para ser ou uma instância de CustomTariff ou uma de suas subclasses, você pode chamar métodos de CustomTariff em T sem a necessidade de conversões explícitas (casting).


estamos lidando com o conceito de herança e implementação de interfaces no Java, combinados com generics. 

Herança e abstração: A classe TariffContractService está herdando (extending) a classe abstrata AbstractCustomTariffService. A classe abstrata provavelmente define métodos e talvez implemente algumas funcionalidades comuns para classes que trabalham com tarifas customizadas, mas deixa algumas coisas em aberto (ou seja, abstratas) para que as subclasses implementem.

Generics aplicados ao tipo TariffContract: Através do uso de T extends CustomTariff, a classe genérica AbstractCustomTariffService trabalha de forma genérica, mas TariffContractService a especializa para trabalhar apenas com o tipo TariffContract.

Interface implementada: TariffContractService também está implementando a interface CustomTariffService, o que obriga a classe a fornecer implementações para os métodos definidos na interface. Essa interface é provavelmente um contrato que todas as classes que fornecem serviços relacionados a tarifas customizadas devem seguir, garantindo um certo comportamento.

- o que é template method? template é um behavioral pattern que permite definir contratos padrao que seu algortimo ira usar.
- o que é classe abstrata? classe que define um contrato. nao pode ser instanciada e pode ter implementação. diferente de uma interface. fornecer uma Estrutura Comum
a classe pode conter métodos publicos abstratos para que suas classes que herdam implementem
- o que é o protected? pode usar no mesmo pacote, classe e subclasse. til quando você quer permitir que subclasses acessem certos métodos ou atributos
- o que é o polimorfismo? permite que métodos com o mesmo nome se comportem de maneira diferente. ou seja chamar os metodos porem por classes diferentes.  ocorre quando as classes estendem uma classe base ou implementam uma interface.


### Command method Case 
Use the Command pattern when you want to:

    Parameterize objects with actions to perform, offering an object-oriented alternative to callbacks found in procedural languages. Commands can be registered and executed later.
    Specify, queue, and execute requests at different times, allowing commands to exist independently of the original request and even be transferred across processes.
    Support undo functionality, where the Command’s execute operation stores state and includes an un-execute operation to reverse previous actions. This allows for unlimited undo and redo capabilities by maintaining a history list.
    Log changes to reapply them after a system crash. By adding load and store operations to the Command interface, you can maintain a persistent log of changes and recover by reloading and re-executing commands from this log.
    Structure a system around high-level operations built on primitive operations, which is common in transaction-based systems. The Command pattern models transactions by providing a common interface for invoking and extending operations.
    Keep a history of requests.
    Implement callback functionality.
    Implement undo functionality.


The Command design pattern is a behavioral pattern used in Java programming. It encapsulates a request as an object, allowing for parameterization of clients with queues, requests, and operations. This pattern also supports undoable operations, enhancing flexibility in managing and executing commands.

- `invoker`: i consider it a callback functionality.
permite definir uma função ou método a ser executado em resposta a um evento ou ao término de outra operação. executa determinado processo (executar procedures, por exemplo, dentre outras possibilidades) em resposta a eventos assíncronos, por exemplo consumindo data de um pub sub, dentre outras possibilidades
- você cria uma classe de comando específica para cada ação. Em vez de chamar diretamente o método para realizar uma tarefa, você cria um objeto dessa classe de comando e passa esse objeto para o "invoker" (o objeto que sabe como e quando executar o comando). Dessa forma, o comando em si está "encapsulado" em um objeto — ele contém a solicitação e os dados necessários para executá-la.

<br>

- `undoredo`: a Wizard casts spells on a Goblin. Each spell is a command object that can be executed and undone, demonstrating the core principles of the Command pattern in Java. The spells are executed on the goblin one by one. The first spell shrinks the goblin and the second makes him invisible. Then the wizard reverses the spells one by one. Each spell here is a command object that can be undone.
<br>

- o que é o command? o command é um behavioral pattern. que encapsula a request (o command, a key) como um objeto e a partir disso executa determinada ação. como um algoritmo de callback, onde recebe na request um objeto X o codigo processa (faz o parse pra qual command) aquele objeto atribuindo assim uma key pra ele, ou seja, montamos o Invoker com base nessas infos, que vai determinar qual ação será feita. 
- Qual é o papel do "Receiver" no padrão Command, e por que ele é separado do "Invoker"? O "Receiver" no padrão Command é o objeto que executa a ação real solicitada pelo comando. Ele possui os métodos específicos que realizam o trabalho