package org.ravi.java.jdk8;


import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import org.ravi.udemy.dsa.WorthLooking;

import java.time.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;


/**
 * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html">lambdas</a>
 * and
 * <a href="https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html">method references</a>
 */

enum Gender {MALE, FEMALE};

interface HelloWorld {
    public void greet();

    public void greet(String someone);
}

//@Builder
@Getter
class Person {
    private final LocalDate birthday;
    private final Gender gender;
    private final String name;

    public Person(String name, Gender gender, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
    }

    public static Person maleNamed(String name, int year) {
        return new Person(name, Gender.MALE, LocalDate.of(year, 4, 28));
    }

    public static Person femaleNamed(String name, int year) {
        return new Person(name, Gender.FEMALE, LocalDate.of(year, 10, 28));
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    /**
     * naive implementation
     */
    public int getAge() {
        return LocalDate.now().getYear() - birthday.getYear();
    }

    public boolean isMale() {
        return gender == Gender.MALE;
    }

    public boolean isFemale() {
        return gender == Gender.FEMALE;
    }

    public static <T, S extends Collection<T>, D extends Collection<T>>
    D transferElements(
            S sCollection,
            Supplier<D> collectionFactory) {

        D result = collectionFactory.get();
        for (T t : sCollection) {
            result.add(t);
        }
        return result;
    }

    public String toString() {
        return String.format("%x>  %s age=%d", System.identityHashCode(this), getName(), getAge());
    }
}

class IdentityHashSet<E> extends HashSet<E> {
    public IdentityHashSet() {
        super();
        System.out.println("Constructor called for " + System.identityHashCode(this));
    }

    public String toString() {
        return "MyHash>" + System.identityHashCode(this);
    }
}

// in syntax section
class Calculator {

    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }

    public static void main(String... args) {

        Calculator myApp = new Calculator();
        IntegerMath addition = (a, b) -> {
            int answer = a + b;
            System.out.printf("LAMBDA-block> %d + %d = %d%n", a, b, answer);
            //System.out.flush();
            return answer;
        };
        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));
    }
}

class LambdaScopeTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {

            int z;
            z = 2;

            Consumer<Integer> myConsumer = (y) ->
            {
                // The following statement causes the compiler to generate
                // the error "Local variable z defined in an enclosing scope
                // must be final or effectively final"
                //
                //z = 99;

                System.out.println("x = " + x);
                // see accept
                System.out.println("y = " + y);
                System.out.println("z = " + z);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " +
                        LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);

        }
    }

    public static void main(String... args) {
        LambdaScopeTest st = new LambdaScopeTest();
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}


public class LambdaExpressionTest {
    private static final List<Person> roster = mkRoster();

    static List<Person> mkRoster() {
        List<Person> roster = new ArrayList<>();

        roster.add(Person.maleNamed("Rishi", 1980));
        roster.add(Person.maleNamed("Sanders", 1941));
        roster.add(Person.maleNamed("Modi", 1950));
        roster.add(Person.femaleNamed("KaleyK", 1985));
        roster.add(Person.femaleNamed("AOC", 1989));
        roster.add(Person.femaleNamed("Haley", 1970));
        roster.add(Person.maleNamed("Hancock", 1737));
        roster.add(Person.femaleNamed("Taylor", 1989));
        roster.add(Person.maleNamed("KurtC", 1967));
        roster.add(Person.femaleNamed("JuliaR", 1967));

        return roster;
    }

    @Test
    public void ctorRef() {
        Supplier<Collection> supplier = IdentityHashSet::new;
        Set<Person> set = (Set<Person>) Person.transferElements(
                roster,
                supplier
        );

        System.err.println("size=" + set.size() + ", set=" + set.toString());
        Assert.assertTrue("size>1", set.size() > 1);
    }

    @Test
    public void withStream() {
        roster
                .stream()
                .filter(p ->/* p.getGender() == Gender.MALE
                        && */p.getAge() > 18
                        && p.getAge() < 50)
                .forEach(p -> System.out.printf("Person(%s) %s, id=%x%n", p.getName(), p,
                        System.identityHashCode(p)));
    }

    @Test
    public void maleAverage() {
        double avg = roster
                .stream()
                .filter(p -> p.isMale())
                .mapToInt(Person::getAge)
                .average()
                .getAsDouble();
        System.out.printf("Average age = %f %n", avg);
    }

    @Test
    public void femaleNames() {
        roster
                .stream()
                .filter(p -> p.isFemale())
                .forEach(p -> System.out.printf("%s %n", p));
    }
}
