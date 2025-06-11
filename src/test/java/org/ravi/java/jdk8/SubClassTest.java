package org.ravi.java.jdk8;

import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/java/IandI/subclasses.html>subclasses</a>
 */
class Bicycle {

    // the Bicycle class has four methods
    // the Bicycle class has three fields
    @Setter
    public int cadence;
    @Setter
    public int gear;
    @Setter
    public int speed;

    public Bicycle() {
        System.out.println("ctor: no arg Bicycle");
    }
    // the Bicycle class has one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        System.out.println("ctor: 3 arg");
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }

    public void applyBrake(int decrement) {
        speed -= decrement;
    }

    public void speedUp(int increment) {
        speed += increment;
    }

}

class Trike extends Bicycle {
    public Trike(int cadence, int speed) {
        this.setCadence(cadence);
        this.setSpeed(speed);
        this.setGear(0);
    }

    public Trike(int cadence, int speed, int gear) {
        super(cadence, speed, gear);
        System.out.println("ctor: 3-arg-trike, after 3-arg-super");
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

    @Test
    public void trikeTestCallsSuperNoArgCtor() {
        Trike trike = new Trike(2, 1);
        System.out.println("trike=" + trike);
        Trike threeArgTrike = new Trike(2, 3, 0);
        System.out.println("threeArgTrike lacking noarg-super = " + threeArgTrike);
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

    public static void main(String[] argv) {
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