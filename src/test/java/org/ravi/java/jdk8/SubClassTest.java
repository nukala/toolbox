package org.ravi.java.jdk8;

import org.junit.Assert;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html>subclasses</a>
 */
class Bicycle {

    // the Bicycle class has three fields
    public int cadence;
    public int gear;
    public int speed;

    // the Bicycle class has one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }

    // the Bicycle class has four methods
    public void setCadence(int newValue) {
        cadence = newValue;
    }

    public void setGear(int newValue) {
        gear = newValue;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

}
class MountainBike extends Bicycle {

    // the MountainBike subclass adds one field
    public int seatHeight;

    // the MountainBike subclass has one constructor
    public MountainBike(int startHeight,
                        int startCadence,
                        int startSpeed,
                        int startGear) {
        super(startCadence, startSpeed, startGear);
        seatHeight = startHeight;
    }

    // the MountainBike subclass adds one method
    public void setHeight(int newValue) {
        seatHeight = newValue;
    }
}

public class SubClassTest {
    @WorthLooking("constructors cannot be invoked from subclass user if un-overridden in subclass")
    // RNTODO - add more detail here!
    @Test
    public void constructorTest() {
        MountainBike mb3 = new MountainBike(5, 3, 2 ,1);

        System.out.printf("mb3 = [%s]%n", mb3);
    }
}

// override.html
class Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
}
class Cat extends Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
    }

    public static void main(String argv[]) {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.testClassMethod();
        Cat.testClassMethod();
        myAnimal.testInstanceMethod();

        Animal baseAnimal = (Animal)myCat;
        baseAnimal.testInstanceMethod();
    }
}
// override.html