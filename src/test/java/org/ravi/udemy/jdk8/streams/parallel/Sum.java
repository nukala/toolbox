package org.ravi.udemy.jdk8.streams.parallel;

// FROM https://github.com/dilipsundarraj1/java-8


import org.ravi.udemy.dsa.WorthLooking;

public class Sum {

    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @WorthLooking("uses un-protected state ... not suitable for parallel streams")
    public void performSum(int input){
        total+=input;
        //System.out.println("total: " + total);

    }

}
