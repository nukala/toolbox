package org.ravi.udemy.jdk8.defaults;

//FROM https://github.com/dilipsundarraj1/java-8


import org.ravi.udemy.dsa.WorthLooking;

@WorthLooking("default methods allow for multiple inheritance")
public class Client123 implements Interface1,Interface2,Interface3 {

    public void methodA(){ //overriding the default method in the implementation class.
        System.out.println("Inside method A "+ Client123.class);
    }

    @WorthLooking("resolves to this class first, then sub-classes, finally default")
    public static void main(String[] args) {
        Client123 client123 = new Client123();

        client123.methodA(); // resolves to child Interface Implementation
        client123.methodB();
        client123.methodC();
    }
}
