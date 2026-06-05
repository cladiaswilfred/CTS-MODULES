interface Movable {
    void go();
}

class Car implements Movable {

    @Override
    public void go() {
        System.out.println("Car is going down the road...");
    }
}

public class InterfaceDemo {

    public static void main(String[] args) {
        Car c = new Car();
        c.go();
    }
}
