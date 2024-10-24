import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;


// Some company via hacker rank asked me to solve this. 
//   I did not know how to solve containsPoint
//
// rules had special non-degenerate condition
//   |ab| + |bc| > |ac|
//   |bc| + |ac| > |ab|
//   |ab| + |ac| > |bc|
//
// if not non-degenerate return 0
//  if contains only P -> 1
//  contains only q -> 2
//  has both P and q -> 3
//  none of the above -> 4
//
// this code failed 3 (hidden) tests out of 50 total.
class Result {

    static int distance(int ax, int ay, int bx, int by) {
        int x2 = (ax - bx) * (ax - bx);
        int y2 = (ay - by) * (ay - by);
        
        return (int)Math.sqrt((x2 + y2));
    }
    
    static boolean isNonDegenerate(int x1, int y1, int x2, int y2, int x3, int y3) {
        int ab = distance(x1, y1, x2, y2);
        int bc = distance(x2, y2, x3, y3);
        int ac = distance(x1, y1, x3, y3);
        
        return ab + bc > ac
            && bc + ac > ab
            && ab + ac > bc;
    }
    
    static boolean hasOne(int a, int b, int p) {
        boolean hasOne = false;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        if ( (p >= min) && (p <= max) ) {
            hasOne = true;
        }
        
        return hasOne;
    }
    static boolean isInside(int ax, int ay, int bx, int by, int px, int py) {
        boolean hasX = hasOne(ax, bx, px);
        boolean hasY = hasOne(ay, by, py);
        
        return hasX && hasY;
    }
    
    /*
     * Complete the 'pointsBelong' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER x1
     *  2. INTEGER y1
     *  3. INTEGER x2
     *  4. INTEGER y2
     *  5. INTEGER x3
     *  6. INTEGER y3
     *  7. INTEGER xp
     *  8. INTEGER yp
     *  9. INTEGER xq
     *  10. INTEGER yq
     */

    public static int pointsBelong(int x1, int y1, int x2, int y2, int x3, int y3, int xp, int yp, int xq, int yq) {
    // Write your code here
        if (!isNonDegenerate(x1, y1, x2, y2, x3, y3))  {
            return 0;
        }
        
        boolean hasP = isInside(x1, y1, x2, y2, xp, yp) // ab
                        || isInside(x2, y2, x3, y3, xp, yp) //bc
                        || isInside(x1, y1, x3, y3, xp, yp); // ac
                        
        boolean hasQ = isInside(x1, y1, x2, y2, xq, yq) // ab
                        || isInside(x2, y2, x3, y3, xq, yq) //bc
                        || isInside(x1, y1, x3, y3, xq, yq); // ac

        if (debug) {
            System.out.println("hasP=" + hasP + ", hasQ=" + hasQ);
        }
        if (hasP && hasQ) {
            return 3;
        } else if (hasP) {
            return 1;
        } else if (hasQ) {
            return 2;
        } else {
            return 4;
        }
    }

    static boolean debug = false;
}

public class PointsBelongApp {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int x1 = Integer.parseInt(bufferedReader.readLine().trim());

        int y1 = Integer.parseInt(bufferedReader.readLine().trim());

        int x2 = Integer.parseInt(bufferedReader.readLine().trim());

        int y2 = Integer.parseInt(bufferedReader.readLine().trim());

        int x3 = Integer.parseInt(bufferedReader.readLine().trim());

        int y3 = Integer.parseInt(bufferedReader.readLine().trim());

        int xp = Integer.parseInt(bufferedReader.readLine().trim());

        int yp = Integer.parseInt(bufferedReader.readLine().trim());

        int xq = Integer.parseInt(bufferedReader.readLine().trim());

        int yq = Integer.parseInt(bufferedReader.readLine().trim());

        int result = Result.pointsBelong(x1, y1, x2, y2, x3, y3, xp, yp, xq, yq);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
