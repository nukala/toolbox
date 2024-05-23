package org.ravi.ivquiz.others;


/// given logs as TimeStamp|UserID|Path array=
// [ "1|30|/login",
//        "2|30|buy",
//                "3|30|logout",
//                "4|22|login",
//                "5|44|login"
//
// print N highest occurring triples as an array like [ [ "login,buy,logout" 3],
//           ["login","login","login",2] ] and such.

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * WIP
 */
public class LogFrequencyTest {
    void addToMap(Map<Integer, List<Triple>> map, Triple t) {
        if (map.get(t.userId) == null) {
            List<Triple> lst = new ArrayList<>();
            lst.add(t);
            map.put(t.userId, lst);
        } else {
            map.get(t.userId).add(t);
        }
    }

    String[][] findMostCommonTriplets(String[] log_lines, int n) {
        // validation
        Map<Integer, List<Triple>> map = new HashMap<>();

        for (String line : log_lines) {
            Triple t = Triple.of(line);

            addToMap(map, t);

            //
            // when 3 occurances
            //   adding to a Map<threePathsString, Integer> map

            if (map.get(t.userId).size() == 3) {

            }

        }

        // when all lines are done ...
        //   sort the map
        //   print the first n
        return null;
    }

    static class Triple {
        int userId;
        long time;
        String path;

        private Triple(String usr, String timeStr, String path) {
            super();

            this.userId = Integer.parseInt(usr);
            this.time = Long.parseLong(timeStr);
            this.path = path;
        }

        public static Triple of(String str) {
            String[] ary = str.split("|");

            return new Triple(ary[0], ary[1], ary[2]);
        }
    }

    static class Occurance {
        String path;
        int count;


    }


}
