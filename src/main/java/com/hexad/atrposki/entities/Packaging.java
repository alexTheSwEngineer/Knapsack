package com.hexad.atrposki.entities;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Entity describing a packaging.
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class Packaging {
    private Price price;
    private int amountOfItems;

    public Packaging(Price price, int amountOfItems) {
        this.price = price;
        this.amountOfItems = amountOfItems;
    }

    public Packaging(String curency, double price, int amountOfItems) {
        this.price = new Price(curency, price);
        this.amountOfItems = amountOfItems;
    }


}
