package org.ravi.java.util;

import org.junit.Test;

import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.assertj.core.api.Assertions.assertThat;

// p291 of Java_8_in_Action_v2.pdf
public class NestedOptionalsTest {
    private Insurance insurance = new Insurance("Geico");

    private String getCarInsuranceName(Person person) {
        return getCarInsuranceName(ofNullable(person));
    }

    private String getCarInsuranceName(Optional<Person> personOptional) {
        return personOptional
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    @Test
    public void withAll() {
        Person withAll = new Person(new Car(insurance));

        assertThat(getCarInsuranceName(withAll))
                .isEqualTo("Geico");
    }

    @Test
    public void noCar() {
        assertThat(getCarInsuranceName(new Person()))
                .isEqualTo("Unknown");
    }

    @Test
    public void noPerson() {
        assertThat(getCarInsuranceName((Person)null))
                .isEqualTo("Unknown");
    }


    @Test
    public void noInsurance() {
        assertThat(getCarInsuranceName(new Person(new Car())))
                .isEqualTo("Unknown");
    }

    class Person {
        private Optional<Car> carOptional;

        public Person() {
            carOptional = Optional.empty();
        }

        public Person(Car car) {
            this.carOptional = ofNullable(car);
        }

        public Optional<Car> getCar() {
            return carOptional;
        }
    }

    class Car {
        private Optional<Insurance> insuranceOptional;

        public Car() {
            insuranceOptional = Optional.empty();
        }

        public Car(Insurance insurance) {
            this.insuranceOptional = ofNullable(insurance);
        }

        public Optional<Insurance> getInsurance() {
            return insuranceOptional;
        }
    }

    class Insurance {
        private String name;

        public Insurance(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
