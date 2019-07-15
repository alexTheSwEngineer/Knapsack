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
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Setter
@Accessors(chain = true)
public class PastryItemPackagingGroup {
    private PastryItem pastryItem;
    private Map<Packaging, Integer> packages;

    /**
     * @return the total amount of instances of the pastryItems {@link PastryItemPackagingGroup#getPastryItem()}
     * */
    public int count() {
        return packages.entrySet().stream()
                .mapToInt(packageInstances -> {
                    Packaging packaging = packageInstances.getKey();
                    return packaging.getAmountOfItems() * packageInstances.getValue();
                })
                .sum();
    }

    /**
     * @return the total cost of this packaging group
     * */
    public Price totalCost() {
        return packages.entrySet().stream()
                .map(packageInstances -> {
                    Price price = packageInstances.getKey().getPrice();
                    int numberOfInstances = packageInstances.getValue();
                    return price.scaleByFactor(numberOfInstances);
                })
                .reduce(Price::add)
                .orElse(new Price());
    }
}
