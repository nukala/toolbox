package org.ravi.udemy.jdk8.defaults;

//FROM https://github.com/dilipsundarraj1/java-8

public interface Interface1 {

    default void methodA(){
        System.out.println("Inside method A "+Interface1.class);
    }
}
