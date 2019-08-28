package org.ravi.inaction;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


public class Potpurri {
    @Test
    public void hackerRankPlusMinus() {
        int arr[] = new int[]{-4, 3, -9, 0, 4, 1};
        int length = arr.length;
        int pos = 0;
        int neg = 0;
        int zero = 0;

        for (int i = 0; i < length; i++) {
            int a = arr[i];
            if (a == 0) {
                zero++;
            } else if (a > 0) {
                pos++;
            } else {
                neg++;
            }
        }

        System.out.printf("%.6f%n%.6f%n%.6f%n", (float) pos / length, (float) neg / length, (float) zero / length);
    }

    // www.java67.com/2019/05/how-to-convert-stream-to-list-set-map-in-java.html
    @Test
    public void java67_StreamsToMaps() {
        // Stream of Integers
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 78, 9, 10, 3, 2, 34, 5, 3);

        // 1. Stream to List
        List<Integer> listOfIntegers = input.stream()
                .collect(Collectors.toList());
        System.out.println("Stream to List: " + listOfIntegers);

        // Stream to ArrayList
        ArrayList<Integer> aList = input.stream()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("Stream to ArrayList: " + Joiner.on(",").join(aList));


        // Stream to LinkedNode
        LinkedList<Integer> linkedList = input.stream()
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("Stream to LinkedNode: " + linkedList);


        // 2. Stream to Set
        Set<Integer> aSet = input.stream().collect(Collectors.toSet());
        System.out.println("Stream to Set: " + aSet);

        // Stream to HashSet
        HashSet<Integer> anHashSet = input.stream()
                .collect(Collectors.toCollection(HashSet::new));
        System.out.println("Stream to HashSet: " + anHashSet);

        // Stream to LinkedHashSet
        LinkedHashSet<Integer> aLinkedHashSet = input.stream()
                .collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println("Stream to LinkedHashSet: " + aLinkedHashSet);


        // 3. Stream to Map
        Map<Integer, String> aMap = input.stream()
                .collect(Collectors.toMap(
                        Function.identity(), String::valueOf, (k1, k2) -> k1));
        System.out.println("Stream to Map: " + aMap);

        // Stream to HashMap
        HashMap<Integer, String> anHashMap = input.stream()
                .collect(Collectors.toMap(
                        Function.identity(), String::valueOf, (k1, k2) -> k1, HashMap::new));
        System.out.println("Stream to HashMap: " + anHashMap);

        // Stream to LinkedHashMap
        LinkedHashMap<Integer, String> aLinkedHashMap
                = input.stream()
                .collect(Collectors.toMap(
                        Function.identity(), String::valueOf, (k1, k2) -> k1, LinkedHashMap::new));
        System.out.println("Stream to LinkedHashMap: " + aLinkedHashMap);


        // 4. Stream to ConcurrentMap
        ConcurrentMap<Integer, String> aConcurrentMap
                = input.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Function.identity(), String::valueOf, (k1, k2) -> k1));
        System.out.println("Stream to ConcurrentMap: " + aConcurrentMap);

        // Stream to ConcurrentHashMap
        ConcurrentHashMap<Integer, String> aConcurrentHashMap
                = input.parallelStream()
                .collect(Collectors.toConcurrentMap(
                        Function.identity(), String::valueOf, (k1, k2) -> k1, ConcurrentHashMap::new));
        System.out.println("Stream to ConcurrentHashMap: " + aConcurrentHashMap);
    }

    //https://www.hackerrank.com/challenges/diagonal-difference/problem
    @Test
    public void diagonalSum() {
        int arr[][] = new int[][] {
                {11,2,4},
                {4,5,6},
                {10,8,-12}
        };

        int last = arr[0].length-1;
        int leftToRight = 0;
        for (int i = 0; i <= last; i++) {
            leftToRight += arr[i][i];
        }
        assertThat(leftToRight)
                .isEqualTo(4);
        int rightToLeft = 0;
        for (int j = last, i = 0; j >= 0 && i <= last; j--, i++) {
            rightToLeft += arr[i][j];
        }

        assertThat(rightToLeft)
                .isEqualTo(19);
    }

    private void incr(int i) {
        i++;
    }
    private void incr(Integer i) {
        i = new Integer(i.intValue() + 1);
        i.intValue();
    }
    @Test public void incrTest() {
        int i = 9;
        incr(i);
        assertThat(i).isEqualTo(9);
    }
    @Test public void incrTestObj() {
        Integer i = Integer.valueOf(9);
        incr(i);
        assertThat(i.intValue()).isEqualTo(9);
    }
}
