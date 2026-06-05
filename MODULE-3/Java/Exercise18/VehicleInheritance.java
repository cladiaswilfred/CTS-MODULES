class Vehicle {
    void start() {
        System.out.println("Vehicle engine starts...");
    }
}

class Car extends Vehicle {
    void drive() {
        System.out.println("Car is moving on the highway...");
    }
}

public class VehicleInheritance {

    public static void main(String[] args) {
        Car myCar = new Car();

        myCar.start();
        myCar.drive();
    }
}
