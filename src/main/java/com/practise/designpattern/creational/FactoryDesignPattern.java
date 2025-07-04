package com.practise.designpattern.creational;



/**
 * Factory: As name suggests it is a factory where we can create objects.
 * Since it creates an object, it falls in a creational design pattern
 * Factory pattern has two important elements in its design.
 *
 * 1. Interface/Abstract class: This is a base element for which we are making factory i.e. we are going to get object of this type
 * In this case it is "Car" which has type-available SUV and Sedan.
 *
 * 2. Factory: This will have nothing but Object creation logic. Let's say as a library you introduce one more subtype that is
 * Off_Road. Now your caller is automatically extended to this facility of third type; also we have taken responsibility of
 * creating of an object.
 *
 * In java, this pattern is heavily used:
 * 1. Calendar.getInstance: Calendar is abstract class and based on Locale and Timezone we provided it is giving calendar instance.
 * Note in this case, we really don't know what type of calendar we got.
 * 2. In reflection Class.forname: the type of class you pass it gets loaded.
 *
 * Best link to learn java and patterns used: https://stackoverflow.com/questions/1673841/examples-of-gof-design-patterns-in-javas-core-libraries
 */




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

    public String getSedanDescription() {
        return "Sedan specific car method";
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
        return switch (type.toLowerCase()) {
            case "sedan" -> new Sedan();
            case "suv" -> new SUV();
            default -> throw new IllegalArgumentException("Invalid car type: " + type);
        };
    }
}

// Client
public class FactoryDesignPattern {
    public static void main(String[] args) {
        CarFactory carFactory = new ConcreteCarFactory();

        Car sedan = carFactory.createCar("sedan");

        if (sedan instanceof Sedan s) {
            System.out.println(s.getSedanDescription());
        }

        System.out.println(sedan.getDescription());

        Car suv = carFactory.createCar("SUV");
        System.out.println(suv.getDescription());
    }
}