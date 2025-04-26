package com.practise.java.oop;

// Interface for electric capabilities
// Used because:
// 1. It allows multiple inheritance (a class can implement multiple interfaces)
// 2. It defines a contract for electric functionality without implementation details
interface Electric {
    //Static method: utility method that can be called on the interface itself
    static void connectToChargingStation() {
        System.out.println("Connecting to charging station...");
    }

    void charge();

    // Default method: Provides a default implementation that can be overridden
    default void showBatteryStatus() {
        System.out.println("Battery status is unknown.");
    }
}

// Interface for autonomous capabilities
// Separates autonomous functionality from vehicle type, allowing for flexible combinations
interface Autonomous {
    void engageAutoPilot();

    void disengageAutoPilot();
}

// Interface extending another interface
// Shows how interfaces can be used to create a hierarchy of capabilities
interface FullyAutonomous extends Autonomous {
    void selfPark();
}

// Interface for off-road capabilities
// Allows this capability to be added to any vehicle type independently of its other features
interface OffRoadCapable {
    void enableFourWheelDrive();
}

// Abstract base class for all vehicles
// Used because:
// 1. It provides a common structure for all vehicle types
// 2. It allows for the definition of common attributes and methods
// 3. It can include both concrete and abstract methods
abstract class Vehicle {
    protected String model;
    protected int year;

    public Vehicle(String model, int year) {
        this.model = model;
        this.year = year;
    }

    // Abstract method: Each vehicle type must implement its own driving behavior
    public abstract void drive();

    // Concrete method: Common for all vehicles
    public void displayDetails() {
        System.out.println("Model: " + model + ", Year: " + year);
    }
}

// Abstract class for cars, extending Vehicle
// Used because:
// 1. It provides a more specific base for car types
// 2. It can implement some methods common to all cars
abstract class Car extends Vehicle {
    public Car(String model, int year) {
        super(model, year);
    }

    // Concrete methods common to all cars
    public void start() {
        System.out.println(model + " is starting.");
    }

    public void stop() {
        System.out.println(model + " is stopping.");
    }
}

// Concrete class for standard cars
// Extends Car to inherit common car functionality
class StandardCar extends Car {
    public StandardCar(String model, int year) {
        super(model, year);
    }

    @Override
    public void drive() {
        System.out.println(model + " is driving normally.");
    }
}

// Concrete class for electric cars
// Extends Car and implements Electric to combine car functionality with electric capabilities
class ElectricCar extends Car implements Electric {
    private final int batteryLevel;

    public ElectricCar(String model, int year, int batteryLevel) {
        super(model, year);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void drive() {
        System.out.println(model + " is driving silently on electric power.");
    }

    @Override
    public void charge() {
        System.out.println(model + " is charging.");
    }

    @Override
    public void showBatteryStatus() {
        System.out.println("Battery level of " + model + " is at " + batteryLevel + "%.");
    }
}

// Concrete class for autonomous electric cars
// Demonstrates multiple interface implementation along with class inheritance
class AutonomousElectricCar extends Car implements Electric, FullyAutonomous {
    private int batteryLevel;

    public AutonomousElectricCar(String model, int year, int batteryLevel) {
        super(model, year);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void drive() {
        System.out.println(model + " is driving autonomously and silently.");
    }

    @Override
    public void charge() {
        System.out.println(model + " is charging autonomously.");
    }

    @Override
    public void showBatteryStatus() {
        System.out.println("Battery level of " + model + " is at " + batteryLevel + "%.");
    }

    @Override
    public void engageAutoPilot() {
        System.out.println(model + " has engaged its autonomous driving system.");
    }

    @Override
    public void disengageAutoPilot() {
        System.out.println(model + " has disengaged its autonomous driving system.");
    }

    @Override
    public void selfPark() {
        System.out.println(model + " is parking itself.");
    }
}

// Concrete class for SUVs
// Implements OffRoadCapable to add off-road functionality to a standard car
class SUV extends Car implements OffRoadCapable {
    public SUV(String model, int year) {
        super(model, year);
    }

    @Override
    public void drive() {
        System.out.println(model + " is driving on rough terrain.");
    }

    @Override
    public void enableFourWheelDrive() {
        System.out.println(model + " has enabled four-wheel drive.");
    }
}

// Concrete class for Electric SUVs
// Demonstrates how multiple interfaces can be combined with class inheritance
class ElectricSUV extends Car implements Electric, OffRoadCapable {
    private int batteryLevel;

    public ElectricSUV(String model, int year, int batteryLevel) {
        super(model, year);
        this.batteryLevel = batteryLevel;
    }

    @Override
    public void drive() {
        System.out.println(model + " is driving silently on rough terrain.");
    }

    @Override
    public void charge() {
        System.out.println(model + " is charging at a remote station.");
    }

    @Override
    public void showBatteryStatus() {
        System.out.println("Battery level of " + model + " is at " + batteryLevel + "%.");
    }

    @Override
    public void enableFourWheelDrive() {
        System.out.println(model + " has enabled electric four-wheel drive.");
    }
}

// LambdaTester class to demonstrate the vehicle system
public class VehicleSystemStimulation {
    public static void main(String[] args) {
        // Creating instances of different vehicle types
        StandardCar sedan = new StandardCar("Sedan Model X", 2023);
        ElectricCar electricHatchback = new ElectricCar("E-Hatch Y", 2023, 80);
        AutonomousElectricCar autoElectric = new AutonomousElectricCar("AutoE Delta", 2023, 95);
        SUV offRoader = new SUV("SUV Zeta", 2023);
        ElectricSUV electricOffRoader = new ElectricSUV("E-SUV Omega", 2023, 85);

        // Demonstrating individual vehicle behaviors
        System.out.println("=== Standard Car Demonstration ===");
        sedan.displayDetails();
        sedan.start();
        sedan.drive();
        sedan.stop();

        System.out.println("\n=== Electric Car Demonstration ===");
        electricHatchback.displayDetails();
        electricHatchback.start();
        electricHatchback.drive();
        electricHatchback.showBatteryStatus();
        electricHatchback.charge();
        electricHatchback.stop();
        Electric.connectToChargingStation();

        System.out.println("\n=== Autonomous Electric Car Demonstration ===");
        autoElectric.displayDetails();
        autoElectric.start();
        autoElectric.engageAutoPilot();
        autoElectric.drive();
        autoElectric.showBatteryStatus();
        autoElectric.selfPark();
        autoElectric.disengageAutoPilot();
        autoElectric.stop();

        System.out.println("\n=== SUV Demonstration ===");
        offRoader.displayDetails();
        offRoader.start();
        offRoader.drive();
        offRoader.enableFourWheelDrive();
        offRoader.stop();

        System.out.println("\n=== Electric SUV Demonstration ===");
        electricOffRoader.displayDetails();
        electricOffRoader.start();
        electricOffRoader.drive();
        electricOffRoader.showBatteryStatus();
        electricOffRoader.enableFourWheelDrive();
        electricOffRoader.charge();
        electricOffRoader.stop();

        // Demonstrating polymorphism
        // This shows how abstract classes and interfaces allow for flexible type handling
        System.out.println("\n=== Polymorphism Demonstration ===");
        Vehicle[] vehicles = {sedan, electricHatchback, autoElectric, offRoader, electricOffRoader};
        for (Vehicle vehicle : vehicles) {
            vehicle.displayDetails();
            vehicle.drive();
            // Using instanceof to check and cast to specific interfaces
            // This demonstrates how interfaces allow for flexible behavior invocation
            if (vehicle instanceof Electric) {
                ((Electric) vehicle).showBatteryStatus();
            }
            if (vehicle instanceof Autonomous) {
                ((Autonomous) vehicle).engageAutoPilot();
            }
            if (vehicle instanceof OffRoadCapable) {
                ((OffRoadCapable) vehicle).enableFourWheelDrive();
            }
            System.out.println();
        }
    }
}
