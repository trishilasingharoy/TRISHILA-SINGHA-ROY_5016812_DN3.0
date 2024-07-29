// Computer.java
class Computer {
    // Computer attributes
    private final String CPU;
    private final int RAM; // in GB
    private final int Storage; // in GB
    private final String GPU;
    private final String OS;

    // Private constructor that takes a Builder as a parameter
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.Storage = builder.Storage;
        this.GPU = builder.GPU;
        this.OS = builder.OS;
    }

    // Static nested Builder class
    public static class Builder {
        private String CPU;
        private int RAM;
        private int Storage;
        private String GPU;
        private String OS;

        // Method to set CPU
        public Builder setCPU(String CPU) {
            this.CPU = CPU;
            return this;
        }

        // Method to set RAM
        public Builder setRAM(int RAM) {
            this.RAM = RAM;
            return this;
        }

        // Method to set Storage
        public Builder setStorage(int Storage) {
            this.Storage = Storage;
            return this;
        }

        // Method to set GPU
        public Builder setGPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        // Method to set OS
        public Builder setOS(String OS) {
            this.OS = OS;
            return this;
        }

        // Build method to return a new Computer instance
        public Computer build() {
            return new Computer(this);
        }
    }


    public String toString() {
        return "Computer [CPU=" + CPU + ", RAM=" + RAM + "GB, Storage=" + Storage + "GB, GPU=" + GPU + ", OS=" + OS + "]";
    }
}

// BuilderPatternTest.java
class BuilderPatternTest {
    public static void main(String[] args) {
        // Create a Computer instance using the Builder
        Computer workstationPC = new Computer.Builder()
                .setCPU("Intel Xeon W-3375 ")
                .setRAM(64)
                .setStorage(2000)
                .setGPU("NVIDIA RTX A4000")
                .setOS("Windows 10 pro")
                .build();

        Computer midRangeDesktop = new Computer.Builder()
                .setCPU("Intel Core i7-13700K ")
                .setRAM(16)
                .setStorage(1000)
                .setGPU("NVIDIA GeForce RTX 3070")
                .setOS("Windows 11")
                .build();

        Computer ultrabook = new Computer.Builder()
                .setCPU("Intel Core i7-1260P ")
                .setRAM(16)
                .setStorage(512)
                .setGPU("Integrated Intel Iris Xe")
                .setOS("Windos 10")
                .build();

        // Print the configurations
        System.out.println("Workstation PC Configuration: " + workstationPC);
        System.out.println("Mid Range Desktop Configuration: " + midRangeDesktop);
        System.out.println("Ultrabook Configuration: " + ultrabook);
    }
}
