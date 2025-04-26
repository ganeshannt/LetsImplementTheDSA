package com.practise.designpattern.creational;


class Computer {

    // Fields - make them final for immutability
    private final String cpu;        // Mandatory
    private final String ram;        // Mandatory
    private final String storage;    // Optional
    private final String graphicsCard; // Optional
    private final String os;         // Optional

    // Private constructor - Force use of the Builder
    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.os = builder.os;
    }

    // Getters for the fields (no setters for immutability)
    public String getCpu() {
        return cpu;
    }

    public String getRam() {
        return ram;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getOs() {
        return os;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram +
                ", Storage=" + (storage != null ? storage : "N/A") +
                ", GraphicsCard=" + (graphicsCard != null ? graphicsCard : "Integrated") +
                ", OS=" + (os != null ? os : "None") + "]";
    }

    // --- The Static Nested Builder Class ---
    public static class ComputerBuilder {
        // Mirror fields from Computer, but not final
        private final String cpu; // Mandatory: Set via constructor
        private final String ram; // Mandatory: Set via constructor

        private String storage;    // Optional: Set via methods
        private String graphicsCard; // Optional: Set via methods
        private String os;         // Optional: Set via methods

        // Builder constructor takes mandatory fields
        public ComputerBuilder(String cpu, String ram) {
            if (cpu == null || ram == null) {
                throw new IllegalArgumentException("CPU and RAM are mandatory.");
            }
            this.cpu = cpu;
            this.ram = ram;
        }

        // Setter-like methods for optional fields (Fluent Interface)
        public ComputerBuilder storage(String storage) {
            this.storage = storage;
            return this; // Return builder for chaining
        }

        public ComputerBuilder graphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this; // Return builder for chaining
        }

        public ComputerBuilder os(String os) {
            this.os = os;
            return this; // Return builder for chaining
        }

        // The build method: Creates the final Computer object
        public Computer build() {
            // Can add complex validation here before creating the object
            // For example: if (os != null && storage == null) throw new IllegalStateException("OS requires storage!");

            Computer computer = new Computer(this);
            // Optionally validate the constructed computer object state here if needed
            return computer;
        }
    }
    // --- End of Builder Class ---
}

// --- Example Usage ---
class BuilderPattern {
    public static void main(String[] args) {
        // 1. Basic computer with only mandatory parts
        Computer basicComputer = new Computer.ComputerBuilder("Intel i5", "8GB")
                .build();
        System.out.println("Basic Computer: " + basicComputer);

        // 2. Gaming computer with optional parts using fluent interface
        Computer gamingComputer = new Computer.ComputerBuilder("AMD Ryzen 7", "32GB")
                .storage("1TB NVMe SSD")
                .graphicsCard("NVIDIA RTX 4080")
                .os("Windows 11 Pro")
                .build();
        System.out.println("Gaming Computer: " + gamingComputer);

        // 3. Office computer with specific optional parts
        Computer officeComputer = new Computer.ComputerBuilder("Intel i7", "16GB")
                .storage("512GB SSD")
                .os("Windows 11 Home")
                // No dedicated graphics card specified
                .build();
        System.out.println("Office Computer: " + officeComputer);

        // Example of trying to build without mandatory fields (will throw exception)
        try {
            Computer invalidComputer = new Computer.ComputerBuilder(null, "16GB").build();
        } catch (IllegalArgumentException e) {
            System.out.println("\nError building computer: " + e.getMessage());
        }
    }
}

