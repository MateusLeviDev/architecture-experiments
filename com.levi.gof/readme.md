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




