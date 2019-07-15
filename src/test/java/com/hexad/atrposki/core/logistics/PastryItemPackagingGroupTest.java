package com.hexad.atrposki.core.logistics;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import com.hexad.atrposki.entities.Packaging;
import com.hexad.atrposki.entities.Price;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PastryItemPackagingGroupTest {

    public static final String arbitraryCurency = "";
    public static final int arbitraryPrice = 0;
    public static final double DELTA = 0.001;

    @Test
    public void givenMultiplePackages_PackagingGroupCalculatesCorrectTotalNumberOfItems() {
        HashMap<Packaging, Integer> packages = new HashMap<>();
        packages.put(new Packaging(arbitraryCurency, arbitraryPrice, 10), 10);
        packages.put(new Packaging(arbitraryCurency, arbitraryPrice, 20), 5);
        int count = new PastryItemPackagingGroup().setPackages(packages).count();
        Assert.assertEquals(200, count);
    }


    @Test
    public void givenMultiplePackages_PackagingGroupCalculatesCorrectTotalPrice() {
        HashMap<Packaging, Integer> packages = new HashMap<>();
        packages.put(new Packaging(arbitraryCurency, 1, 10), 10);
        packages.put(new Packaging(arbitraryCurency, 2, 10), 10);
        Price price = new PastryItemPackagingGroup().setPackages(packages).totalCost();
        Assert.assertEquals(30.0, price.getValue(), DELTA);
    }
}
