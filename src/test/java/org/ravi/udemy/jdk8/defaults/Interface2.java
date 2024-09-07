package org.ravi.udemy.jdk8.defaults;

//FROM https://github.com/dilipsundarraj1/java-8

public interface Interface2 extends Interface1{

    default void methodB(){
        System.out.println("Inside method B");
    }

    default void methodA(){
        System.out.println("Inside method A "+ Interface2.class);
    }
}
