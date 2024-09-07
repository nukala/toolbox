package org.ravi.udemy.jdk8.streams;

import java.util.ArrayList;

public class CollectionVsStream {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("adam");
        names.add("jim");
        names.add("jenny");
        System.out.println("raw names = " + names);

        names.remove(1);
        System.out.println("after remove(1) = " + names);

//        @WorthLooking("Stream does not allow to remove or add after creation.")
//        names.stream().no add no remove method
    }
}
