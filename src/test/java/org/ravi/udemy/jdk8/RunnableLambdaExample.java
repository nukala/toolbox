package org.ravi.udemy.jdk8;

/**
 * explains ease on the eyes - after you get used to it. Else there is nothing here
 */
public class RunnableLambdaExample {
    public static void dbg(String str) {
        System.out.println(Thread.currentThread().getName() + " - " + str);
        System.out.flush();
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                dbg("run - inside runnable");
            }
        };
        new Thread(runnable).start();

        Runnable lambda = () -> {
            dbg("main" + " - inside lambda");
        };
        new Thread(lambda).start();

        Runnable shrt = () ->
                dbg("main" + " - inside shrt");
        new Thread(shrt).start();

        new Thread(() -> dbg("main" + " - inline")).start();
    }
}
