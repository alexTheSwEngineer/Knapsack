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

import java.util.List;

/**
 * Entity describing a pastry item.
 */
@Getter
@Setter
@Accessors(chain = true)
@EqualsAndHashCode
public class PastryItem {
    private String name;
    private String code;

    /**
     * All possible packagings for the pastry item
     */
    private List<Packaging> packagings;
}
