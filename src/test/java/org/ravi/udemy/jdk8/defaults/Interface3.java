package org.ravi.udemy.jdk8.defaults;

//FROM https://github.com/dilipsundarraj1/java-8

public interface Interface3 extends Interface1,Interface2 {

    default void methodC(){
        System.out.println("Inside method C");
    }
    default void methodA(){
        System.out.println("Inside method A "+ Interface3.class);
    }
}
