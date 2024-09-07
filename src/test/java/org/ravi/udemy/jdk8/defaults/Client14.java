package org.ravi.udemy.jdk8.defaults;

//FROM https://github.com/dilipsundarraj1/java-8


import org.ravi.udemy.dsa.WorthLooking;

public class Client14 implements Interface1,Interface4{

    @WorthLooking("methodA is a conflict. Only way to avoid is - override")
    public void  methodA(){
        // causes stack overflow! ((Interface1)this).methodA();
        System.out.println("Inside method A "+Client14.class);
    }

    public static void main(String[] args) {
        Client14 client14 = new Client14();
        client14.methodA();

    }

}
