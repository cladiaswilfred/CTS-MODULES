public class Car {

    String brand;
    String model;
    int yearMade;

    void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Year:  " + yearMade);
    }

    public static void main(String[] args) {
        Car vehicle = new Car();

        vehicle.brand = "Toyota";
        vehicle.model = "Camry";
        vehicle.yearMade = 2022;

        vehicle.displayInfo();
    }
}
