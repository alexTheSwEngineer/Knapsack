package com.hexad.atrposki.core.knapsack;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;

public class DiscretePositiveValueKnapsackTest {
    @Test
    public void givenScenarioThatFailsGreedyAlgorithms_knapsackCorrectlyCalculatesResult2() {
        PackagingResult<Integer> result = new DiscretePositiveValueKnapsack<Integer>()
                .withValueMapper(Integer::valueOf)
                .withWeightMapper(x -> 1)
                .withPackets(asList(1, 15, 25))
                .withTargetAmount(30)
                .calculatePackaging();

        Assert.assertEquals(2, result.getMinimumRequiredWeight());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyInput_knapsackThrowsIllegalArgumentException() {
        PackagingResult<Integer> result = new DiscretePositiveValueKnapsack<Integer>()
                .withValueMapper(Integer::valueOf)
                .withPackets(new ArrayList<>())
                .withTargetAmount(0)
                .calculatePackaging();

        Assert.assertEquals(2, result.getMinimumRequiredWeight());
    }

    @Test
    public void givenSizes2_5_8_whenExpecting14_knapsacComputes3x2and1x8() {
        PackagingResult<Integer> result = new DiscretePositiveValueKnapsack<Integer>()
                .withValueMapper(Integer::valueOf)
                .withWeightMapper(x -> 1)
                .withPackets(asList(2, 5, 8))
                .withTargetAmount(14)
                .calculatePackaging();

        assertPackageOccurances(result, 2, 3);
        assertPackageOccurances(result, 8, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenSizesNegativeWeight_knapsacThowsIllegalArgumentExc() {
        PackagingResult<Integer> result = new DiscretePositiveValueKnapsack<Integer>()
                .withValueMapper(Integer::valueOf)
                .withWeightMapper(x -> -1)
                .withPackets(asList(2, 5, 8))
                .withTargetAmount(14)
                .calculatePackaging();
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenSizesNegativeValue_knapsacThowsIllegalArgumentExc() {
        PackagingResult<Integer> result = new DiscretePositiveValueKnapsack<Integer>()
                .withValueMapper(x -> -1)
                .withWeightMapper(x -> 1)
                .withPackets(asList(2, 5, 8))
                .withTargetAmount(14)
                .calculatePackaging();
    }


    private void assertPackageOccurances(PackagingResult<Integer> result, int packaging, int occurances) {
        int actualOccurances = result.getPackageInstances().get(packaging);
        Assert.assertEquals(occurances, actualOccurances);
    }

}
