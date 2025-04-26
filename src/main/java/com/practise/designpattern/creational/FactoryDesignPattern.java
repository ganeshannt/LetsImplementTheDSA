package com.practise.designpattern.creational;


// Product Interface
interface Car {
    String getDescription();
}

// Factory Interface
interface CarFactory {
    Car createCar(String type);
}

// Concrete Products
class Sedan implements Car {
    @Override
    public String getDescription() {
        return "Sedan car";
    }
}

class SUV implements Car {
    @Override
    public String getDescription() {
        return "SUV car";
    }
}

// Concrete Factory
class ConcreteCarFactory implements CarFactory {
    @Override
    public Car createCar(String type) {
        switch (type.toLowerCase()) {
            case "sedan":
                return new Sedan();
            case "suv":
                return new SUV();
            default:
                throw new IllegalArgumentException("Invalid car type: " + type);
        }
    }
}

// Client
public class FactoryDesignPattern {
    public static void main(String[] args) {
        CarFactory carFactory = new ConcreteCarFactory();

        Car sedan = carFactory.createCar("sedan");
        System.out.println(sedan.getDescription());

        Car suv = carFactory.createCar("SUV");
        System.out.println(suv.getDescription());
    }
}