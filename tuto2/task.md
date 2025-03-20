# Java

## Object-Oriented Programming in Java: Objects & Classes

### Introduction to Classes
A class is a blueprint or template for creating objects. It defines the properties (attributes) and behaviors (methods) that objects of that class will have.

#### Basic Class Structure:
```java
public class ClassName {
    // Fields (variables)
    // Constructors
    // Methods
}
```

---

### Objects, Methods & Variables

#### Fields (Instance Variables)
These represent the state or attributes of an object.
```java
public class Circle {
    // Instance variables
    double radius;
    String color;
}
```

#### Methods
Methods define the behaviors or actions that objects can perform.
```java
public class Circle {
    double radius;

    // Method to calculate area
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Method to calculate circumference
    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }
}
```

---

### Creating Objects
Objects are instances of classes created using the `new` keyword.
```java
Circle myCircle = new Circle();
myCircle.radius = 5.0;
double area = myCircle.calculateArea();
```

---

### Constructors
Constructors are special methods that initialize objects when they are created. They have the same name as the class and no return type.
```java
public class Circle {
    double radius;
    String color;

    // Default constructor
    public Circle() {
        radius = 1.0;
        color = "red";
    }

    // Parameterized constructor
    public Circle(double r) {
        radius = r;
        color = "red";
    }

    // Overloaded constructor
    public Circle(double r, String c) {
        radius = r;
        color = c;
    }
}
```

#### Constructor Chaining
Using `this()` to call another constructor within a constructor.
```java
public Circle(double r, String c) {
    this(r); // Calls the Circle(double r) constructor
    color = c;
}
```

---

### Modifiers

#### Access Modifiers
Control the visibility and accessibility of classes, methods, and fields.
- `public`: Accessible from any class
- `private`: Accessible only within the declared class
- `protected`: Accessible within the package and by subclasses
- Default (no modifier): Accessible only within the package

```java
public class Circle {
    private double radius; // Only accessible within Circle class

    public double getRadius() { // Accessible from any class
        return radius;
    }

    public void setRadius(double radius) {
        // Input validation
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 0;
        }
    }
}
```

#### Non-Access Modifiers
- `final`: Cannot be overridden/modified
- `abstract`: Must be implemented by a subclass
- `synchronized`: Important for threads
- `volatile`: Value may change unexpectedly

---

### Static Methods & Variables

#### Static Variables
Variables that belong to the class rather than an object. All instances share the same static variable.
```java
public class Circle {
    private double radius;
    private static int numberOfCircles = 0; // Shared by all Circle objects

    public Circle() {
        radius = 1.0;
        numberOfCircles++; // Increment count when creating a new Circle
    }

    public static int getNumberOfCircles() {
        return numberOfCircles;
    }
}
```

#### Static Methods
Methods that belong to the class rather than an object. They can only access static variables directly.
```java
public static double getPI() {
    return Math.PI;
}
```

#### Static vs. Instance
- Static members belong to the class and are accessed using the class name:  
  `Circle.getPI()`
- Instance members belong to objects and are accessed using object references:  
  `myCircle.calculateArea()`

---

### Objects

#### Object Identity vs. Equality
- `==` compares object references (identity)
- `.equals()` compares object content (equality, if properly implemented)

```java
Circle c1 = new Circle(5.0);
Circle c2 = new Circle(5.0);
Circle c3 = c1;

System.out.println(c1 == c2); // false (different objects)
System.out.println(c1 == c3); // true (same object)
System.out.println(c1.equals(c2)); // depends on implementation of equals()
```

#### Implementing `equals()` and `hashCode()`
```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Circle other = (Circle) obj;
    return Double.compare(radius, other.radius) == 0 &&
           Objects.equals(color, other.color);
}

@Override
public int hashCode() {
    return Objects.hash(radius, color);
}
```

---

### Complete Circle Class Example
```java
public class Circle {
    // Instance variables
    private double radius;
    private String color;

    // Static variable
    private static int numberOfCircles = 0;

    // Default constructor
    public Circle() {
        this(1.0, "red");
    }

    // Parameterized constructor
    public Circle(double radius) {
        this(radius, "red");
    }

    // Fully parameterized constructor
    public Circle(double radius, String color) {
        setRadius(radius);
        this.color = color;
        numberOfCircles++;
    }

    // Getter and setter for radius
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if (radius > 0) {
            this.radius = radius;
        } else {
            this.radius = 0;
        }
    }

    // Getter and setter for color
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    // Instance methods
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public double calculateCircumference() {
        return 2 * Math.PI * radius;
    }

    // Static methods
    public static int getNumberOfCircles() {
        return numberOfCircles;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Circle other = (Circle) obj;
        return Double.compare(radius, other.radius) == 0 &&
               Objects.equals(color, other.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius, color);
    }

    @Override
    public String toString() {
        return "Circle[radius=" + radius + ", color=" + color + "]";
    }
}
```

---

### Exercises

#### Exercise 1: Rectangle Class Design
- **Ex 1. Rectangle Class**
  - Design a class named `Rectangle` to represent a rectangle. The class should have:
    - Two double data fields: `width` and `height` (default values: 1).
    - A no-argument constructor.
    - A constructor with specified `width` and `height`.
    - Methods: `getArea()` and `getPerimeter()`.
  - Write a test program that:
    - Creates two `Rectangle` objects: one with width 4 and height 40, and another with width 3.5 and height 35.9.
    - Displays the width, height, area, and perimeter of each rectangle.

#### Exercise 2: Stock Class Design
- **Ex 2. Stock Class**
  - Design a class named `Stock` to represent a stock. The class should have:
    - String data fields: `symbol` and `name`.
    - Double data fields: `previousClosingPrice` and `currentPrice`.
    - A constructor with specified `symbol` and `name`.
    - A method: `getChangePercent()` to calculate the percentage change from `previousClosingPrice` to `currentPrice`.
    - You can use this formula: `(currentPrice - previousClosingPrice) / previousClosingPrice * 100`
  - Write a test program that:
    - Creates a `Stock` object with symbol "ORCL", name "Oracle Corporation", and previous closing price 34.5.
    - Sets the current price to 34.35.
    - Displays the price-change percentage.

#### Exercise 3: Account Class Design
- **Ex 3. Account Class**
  - Design a class named `Account` that contains:
    - A private `int` data field `id` (default 0).
    - A private `double` data field `balance` (default 0).
    - A private `double` data field `annualInterestRate` (default 0).
    - A private `Date` data field `dateCreated`.
    - A no-argument constructor.
    - A constructor with specified `id` and initial `balance`.
    - Accessor and mutator methods for `id`, `balance`, and `annualInterestRate`.
    - An accessor method for `dateCreated`.
    - Methods: `getMonthlyInterestRate()`, `getMonthlyInterest()`, `withdraw(amount)`, and `deposit(amount)`.
  - Write a test program that:
    - Creates an `Account` object with `id` 1122, balance $20,000, and `annualInterestRate` 4.5%.
    - Withdraws $2,500 using `withdraw()`.
    - Deposits $3,000 using `deposit()`.
    - Prints the balance, monthly interest, and `dateCreated`.

---

### Important Notes:
- Remember that `getMonthlyInterest()` should return the interest amount, not the rate.
- Monthly interest is calculated as `balance * monthlyInterestRate`.
- `monthlyInterestRate` is `annualInterestRate / 12`.
- `annualInterestRate` is a percentage, so divide it by 100 before using it in calculations.

--- 
