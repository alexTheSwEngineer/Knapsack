package com.hexad.atrposki.core.knapsack;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Getter
@Setter
@Accessors(chain = true)
public class PackagingResult<T> {

    /**
     * The minimum weight required to achieve the goal
     */
    private final int minimumRequiredWeight;


    /**
     * The target amount achievable by at least {@link PackagingResult#getMinimumRequiredWeight()}
     */
    private final int targetAmount;

    /**
     * A mapping of what package was chosen last in order to achieve a value. The package on position i in this list, has been the last coin chosen to achieve the value i
     */
    private final List<T> chosenPackages;

    /**
     * A mapping of the number of instances required of each packed entity
     */
    private final Map<T, Integer> packageInstances;


    public PackagingResult(List<T> chosenPackages, int targetAmount, int minimumRequiredWeight, Function<T, Integer> valueMapper) {
        this.chosenPackages = new ArrayList<>(chosenPackages).subList(0, targetAmount + 1);
        this.minimumRequiredWeight = minimumRequiredWeight;
        this.targetAmount = targetAmount;
        packageInstances = calculatePackageInstances(this.chosenPackages, targetAmount, valueMapper);
    }

    private Map<T, Integer> calculatePackageInstances(List<T> chosenPackages, int targetAmount, Function<T, Integer> valueMapper) {
        Map<T, Integer> occurances = new HashMap<>();
        int amount = targetAmount;
        T chosenPackage;
        do {
            chosenPackage = chosenPackages.get(amount);
            if (!occurances.containsKey(chosenPackage)) {
                occurances.put(chosenPackage, 1);
            } else {
                Integer oldValue = occurances.get(chosenPackage);
                occurances.put(chosenPackage, oldValue + 1);
            }
            amount -= valueMapper.apply(chosenPackage);
        } while (amount > 0);
        return occurances;
    }

}
