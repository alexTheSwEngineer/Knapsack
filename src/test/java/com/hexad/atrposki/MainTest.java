package com.hexad.atrposki;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import com.hexad.atrposki.core.logistics.PastryItemPackagingGroup;
import com.hexad.atrposki.core.logistics.PastryPackagingCalculator;
import com.hexad.atrposki.core.logistics.PastryPackagingPrinter;
import com.hexad.atrposki.entities.Packaging;
import com.hexad.atrposki.entities.PastryItem;
import org.junit.Before;
import org.junit.Test;

import static java.util.Arrays.asList;

public class MainTest {
    PastryPackagingCalculator calculator;
    PastryPackagingPrinter printer;
    PastryItem vegemiteScroll;
    PastryItem blueberryMuffin;
    PastryItem croissant;

    @Test
    public void showcase() {
        runExample(vegemiteScroll, 10);
        runExample(blueberryMuffin, 14);
        runExample(croissant, 13);
    }

    private void runExample(PastryItem pastry, int requiredAmount) {
        PastryItemPackagingGroup res = calculator.calculateBestPackaging(pastry, requiredAmount);
        printer.print(res, System.out);
    }


    @Before
    public void init() {
        calculator = new PastryPackagingCalculator();
        printer = new PastryPackagingPrinter();
        vegemiteScroll = new PastryItem().setName("Vegemite Scroll")
                .setCode("VS5")
                .setPackagings(asList(new Packaging("$", 6.99, 3),
                        new Packaging("$", 8.99, 5)));
        blueberryMuffin = new PastryItem().setName("Blueberry Muffin")
                .setCode("MB11")
                .setPackagings(asList(new Packaging("$", 9.95, 2),
                        new Packaging("$", 16.95, 5),
                        new Packaging("$", 24.95, 8)));

        croissant = new PastryItem().setName("croissant")
                .setCode("CF")
                .setPackagings(asList(new Packaging("$", 5.95, 3),
                        new Packaging("$", 9.95, 5),
                        new Packaging("$", 16.95, 9)));
    }

}
