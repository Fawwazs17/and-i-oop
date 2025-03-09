# Lab Exercise 1: Creating Your First Java Class

## Objective
Introduce students to the concept of classes and objects in Java. Students will learn to create a class in a separate file and interact with it using an Application class.

## Instructions

### Part 1: Create the Car Class in Car.java
1. Create a new Java file named `Car.java`
2. Define a public class with:
```java
public class Car {
    // Instance variables
    private String brand;
    private String model; 
    private int year;
```
3. Add constructor:
```java
    public Car(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
```
4. Implement getter methods:
```java
    public String getBrand() { return brand; }
    public String getModel() { return model; }
    public int getYear() { return year; }
```
5. Create display method:
```java
    public void displayDetails() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model); 
        System.out.println("Year: " + year);
    }
}
```

### Part 2: Create the Application Class in Application.java
1. Create a new Java file named `Application.java`
2. Main class structure:
```java
public class Application {
    public static void main(String[] args) {
        // Create car instances
        Car myCar = new Car("Toyota", "Corolla", 2020);
        Car oldCar = new Car("Ford", "Model T", 1920);
        
        // Display details
        myCar.displayDetails();
        oldCar.displayDetails();
    }
}
```

## Expected Directory Structure
```
week1/
├── Car.java
└── Application.java
```

## Example Output
```text
Brand: Toyota
Model: Corolla
Year: 2020
Brand: Ford
Model: Model T 
Year: 1920
