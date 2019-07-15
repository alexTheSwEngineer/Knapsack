package com.hexad.atrposki.core.logistics;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import com.hexad.atrposki.core.knapsack.DiscretePositiveValueKnapsack;
import com.hexad.atrposki.core.knapsack.PackagingResult;
import com.hexad.atrposki.entities.Packaging;
import com.hexad.atrposki.entities.PastryItem;

import static com.hexad.atrposki.core.knapsack.DiscretePositiveValueKnapsack.fixedWeight;

public class PastryPackagingCalculator {

    public PastryItemPackagingGroup calculateBestPackaging(PastryItem pastryItem, int requiredAmount) {
        PackagingResult<Packaging> packagingPackagingResult = new DiscretePositiveValueKnapsack<Packaging>()
                .withValueMapper(Packaging::getAmountOfItems)
                .withWeightMapper(fixedWeight(1))
                .withPackets(pastryItem.getPackagings())
                .withTargetAmount(requiredAmount)
                .calculatePackaging();

        return new PastryItemPackagingGroup()
                .setPackages(packagingPackagingResult.getPackageInstances())
                .setPastryItem(pastryItem);
    }
}
