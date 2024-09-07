package org.ravi.udemy.jdk8.defaults;

import org.ravi.udemy.dsa.WorthLooking;

import java.util.List;

public interface  Multiplier<T> {

    T multiply(List<T> integerList);

    // for illustration purposes only
    default int size(List<?> list) {
        System.out.print("size - inside interface - ");
        return list.size();
    }

    @WorthLooking("static defaults cannot be overridden")
    static boolean isEmpty(List<?> list) {
        return list != null && list.isEmpty();
    }
}
