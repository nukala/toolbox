package org.ravi.udemy.dsa;

import java.lang.annotation.*;

// Help remind that I-should-look-before-interview as this implementation may have
// something really cool worth
@Retention(RetentionPolicy.SOURCE)
@Documented
@Target(ElementType.METHOD)
public @interface WorthLooking {
    String value();
}
