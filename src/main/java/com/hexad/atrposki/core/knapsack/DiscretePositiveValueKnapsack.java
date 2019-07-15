package com.hexad.atrposki.core.knapsack;
/*
 * @author aleksandartrposki@gmail.com
 * @since 16.07.19
 *
 *
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/***
 * An immutable implementation class for the knapsack algorithm over discrete values. 
 * The algorithm (and class) are generic and can operate on any entities representing packets as long as value mapping and weight mapping functions are provided.
 * Complexity of computations is O(n*k) n being the value, k being the different packages. * 
 * The input entities must have a correctly functioning hash() and equals() methods.
 * The weight and value mappers must not be null, and is adviced to be pure/stateless.
 */
public class DiscretePositiveValueKnapsack<T> {

    public static final int EMPTY = -1;
    private Function<T, Integer> entityToValueMapper;
    private Function<T, Integer> entityToWeightMapper;
    private List<T> packets;
    private int targetAmount;

    public DiscretePositiveValueKnapsack() {
        entityToValueMapper = x -> (Integer) x;
        entityToWeightMapper = x -> 1;
        packets = new ArrayList<>();
        targetAmount = 0;

    }

    public DiscretePositiveValueKnapsack(Function<T, Integer> entityToValueMapper, Function<T, Integer> entityToWeightMapper, Collection<T> input, int targetAmount) {
        this.entityToValueMapper = entityToValueMapper;
        this.entityToWeightMapper = entityToWeightMapper;
        this.packets = new ArrayList<>(input);
        this.targetAmount = targetAmount;
    }

    /**
     * Runs the packaging calculation and returns the result, see {@link PackagingResult<T>}
     *
     * @return Packagin result
     * @throws IllegalArgumentException if the required target value cannot be reached.
     */
    public PackagingResult<T> calculatePackaging() {
        List<T> chosePackets = createListOfLasChosenPackagesNulls();
        int[] minWeight = getMinimalWeightsForSignlePackets();

        for (int curentValue = 0; curentValue <= targetAmount; curentValue++) {
            if (minWeight[curentValue] == EMPTY) {
                continue;
            }

            for (T packet : packets) {
                int combinedValue = curentValue + valueOf(packet);
                if (combinedValue >= minWeight.length) {
                    continue;
                }

                Integer packetWeight = weightOf(packet);
                if (minWeight[combinedValue] == EMPTY || minWeight[curentValue] + packetWeight < minWeight[combinedValue]) {
                    minWeight[combinedValue] = minWeight[curentValue] + packetWeight;
                    chosePackets.set(combinedValue, packet);
                }
            }
        }

        if (minWeight[targetAmount] != EMPTY) {
            return new PackagingResult<T>(chosePackets, targetAmount, minWeight[targetAmount], entityToValueMapper);
        }

        throw new IllegalArgumentException(String.format("Target value %d cannot be achieved with provided packets", targetAmount));
    }


    /**
     * Configures a new immutable instance of {@link DiscretePositiveValueKnapsack} with the new value mapper.
     * The value returned must be positive.
     *
     * @return Returns a new instance of DiscretePositiveValueKnapsack with the the new entityToValue mapping.
     */
    public DiscretePositiveValueKnapsack<T> withValueMapper(Function<T, Integer> entityToValue) {
        return new DiscretePositiveValueKnapsack<>(entityToValue, this.entityToWeightMapper, this.packets, this.targetAmount);
    }

    /**
     * Configures a new immutable instance of {@link DiscretePositiveValueKnapsack} with the new weight mapper.
     * The value returned for weight must be positive.
     *
     * @return Returns a new instance of DiscretePositiveValueKnapsack with the the new entityToWeight mapping.
     */
    public DiscretePositiveValueKnapsack<T> withWeightMapper(Function<T, Integer> entityToWeight) {
        return new DiscretePositiveValueKnapsack<>(this.entityToValueMapper, entityToWeight, this.packets, this.targetAmount);
    }

    /**
     * Configures a new immutable instance of {@link DiscretePositiveValueKnapsack} with the parameter input.
     * Each packet must have a proper equals and hash method.
     *
     * @return Returns a new instance of DiscretePositiveValueKnapsack with the the new entityToWeight mapping.
     */
    public DiscretePositiveValueKnapsack<T> withPackets(Collection<T> input) {
        return new DiscretePositiveValueKnapsack<>(this.entityToValueMapper, this.entityToWeightMapper, input, this.targetAmount);
    }


    /**
     * Configures a new immutable instance of {@link DiscretePositiveValueKnapsack} with the parameter targetAmount.
     * Target amount must be positive.
     *
     * @return Returns a new instance of DiscretePositiveValueKnapsack with the the new entityToWeight mapping.
     */
    public DiscretePositiveValueKnapsack<T> withTargetAmount(int targetAmount) {
        return new DiscretePositiveValueKnapsack<>(this.entityToValueMapper, this.entityToWeightMapper, this.packets, targetAmount);
    }

    private int[] getMinimalWeightsForSignlePackets() {
        int[] minWeight = new int[targetAmount + 1];
        for (int i = 0; i < minWeight.length; i++) {
            minWeight[i] = EMPTY;
        }
        packets.stream()
                .filter(packet -> valueOf(packet) < minWeight.length)
                .filter(packet -> valueOf(packet) >= 0)
                .forEach(packet -> minWeight[valueOf(packet)] = weightOf(packet));
        return minWeight;
    }

    private List<T> createListOfLasChosenPackagesNulls() {
        ArrayList<T> res = new ArrayList<>(targetAmount + 1);
        for (int i = 0; i < targetAmount + 1; i++) {
            res.add(null);
        }

        packets.forEach(x -> {
            if (valueOf(x) < res.size()) {
                res.set(valueOf(x), x);
            }
        });
        return res;
    }

    private boolean isEmpty(int i) {
        return i == EMPTY;
    }

    private int valueOf(T packet) {
        int res = entityToValueMapper.apply(packet);
        if (res < 0) {
            throw new IllegalArgumentException("Entity weight must non negative " + packet);
        }
        return res;
    }

    private Integer weightOf(T packet) {
        int res = entityToWeightMapper.apply(packet);
        if (res < 0) {
            throw new IllegalArgumentException("Entity weight must non negative " + packet);
        }
        return res;
    }

    public static <T> Function<T, Integer> fixedWeight(int fixedWeigthAmount) {
        return x -> fixedWeigthAmount;
    }
}
