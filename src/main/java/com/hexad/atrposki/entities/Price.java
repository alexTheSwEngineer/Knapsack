package com.hexad.atrposki.entities;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.Wither;

import static java.lang.String.format;

@Getter
@Accessors(chain = true)
@EqualsAndHashCode
@Wither
public class Price {
    private final String curency;
    private final double value;

    public Price(String curency, double value) {
        this.curency = curency;
        this.value = value;
    }

    public Price() {
        curency = "";
        value = 0;
    }

    public Price add(Price other) {
        if (!other.curency.equals(this.curency)) {
            throw new IllegalArgumentException(format("Trying to add 2 different currencies %s and %s"));
        }

        return new Price(this.curency, this.value + other.value);
    }

    public Price scaleByFactor(double factor) {
        return new Price(this.curency, this.value * factor);
    }
}
