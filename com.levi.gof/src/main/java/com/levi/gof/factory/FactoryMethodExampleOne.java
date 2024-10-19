package com.levi.gof.factory;

/*
   Solution using interface
 */

interface Product {
    void display();
}

class ConcreteProductA implements Product {

    @Override
    public void display() {
        System.out.println("This is Concrete Product A.");
    }
}

class ConcreteProductB implements Product {

    @Override
    public void display() {
        System.out.println("This is Concrete Product B.");
    }
}

interface Factory {
    Product factoryMethod();
}

class ConcreteCreatorA implements Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}

class ConcreteCreatorB implements Factory {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}

public class FactoryMethodExampleOne {
    public static void main(String[] args) {
        ConcreteCreatorA concreteCreatorA = new ConcreteCreatorA();
        Product productA = concreteCreatorA.factoryMethod();
        productA.display();

        ConcreteCreatorB concreteCreatorB = new ConcreteCreatorB();
        Product productB = concreteCreatorB.factoryMethod();
        productB.display();
    }
}
