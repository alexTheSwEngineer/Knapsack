package com.hexad.atrposki.core.logistics;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import com.hexad.atrposki.entities.Packaging;
import com.hexad.atrposki.entities.PastryItem;
import com.hexad.atrposki.entities.Price;

import java.io.PrintStream;
import java.util.Map;

import static java.lang.String.format;
import static java.util.Comparator.comparing;

public class PastryPackagingPrinter {
    public void print(PastryItemPackagingGroup items, PrintStream out) {
        PastryItem pastryItem = items.getPastryItem();
        Price totalCost = items.totalCost();
        out.println(format("%4d %s %s%4.2f", items.count(), pastryItem.getCode(), totalCost.getCurency(), totalCost.getValue()));
        items.getPackages()
                .entrySet().stream()
                .sorted(comparing((Map.Entry<Packaging, Integer> x) -> x.getKey().getPrice().getValue()).reversed())
                .forEach((pair) -> {
                    int instances = pair.getValue();
                    Packaging packaging = pair.getKey();
                    Price pricePerPackage = packaging.getPrice();
                    out.println(format("     %d x %d %s%4.2f", instances, packaging.getAmountOfItems(), pricePerPackage.getCurency(), pricePerPackage.getValue()));
                });
    }
}
