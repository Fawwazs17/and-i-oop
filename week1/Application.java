public class Application {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota", "Corolla", 2020);
        myCar.displayDetails();
        
        // Additional test instances
        Car anotherCar = new Car("Honda", "Civic", 2022);
        anotherCar.displayDetails();
    }
}
