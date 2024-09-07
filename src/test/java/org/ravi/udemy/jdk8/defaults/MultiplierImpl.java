package org.ravi.udemy.jdk8.defaults;

import java.util.List;

public class MultiplierImpl implements Multiplier<Integer> {
    @Override
    public Integer multiply(List<Integer> integerList) {
        return integerList.stream()
                .reduce(1, (x, y) -> x * y);
    }

    public int size(List<?> list) {
        System.out.print("inside MultiplierImpl - ");
        return list.size();
    }
}
