package org.ravi.udemy.dsa;

import java.lang.annotation.*;

/**
 * Interesting details
 * OR
 * Mistakes I made while writing up this code.
 * <br/>
 * Either way to remind that I-should-look-before-interview as this implementation may have something really cool worth
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface WorthLooking {
    String value();
}
