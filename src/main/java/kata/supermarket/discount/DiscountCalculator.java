package kata.supermarket.discount;

import kata.supermarket.purchase.Item;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static kata.supermarket.discount.DiscountType.*;

/**
 * Taking an approach of calculating the discount, but it might be better to turn this around and "apply" to the basket
 * rather than summing up based on contents
 * i.e Order discounts and apply the best
 */
public class DiscountCalculator {

    // Mapping tag value : Discount
    private final Map<String, Discount> liveDiscounts = new HashMap<>();

    public DiscountCalculator(Set<Discount> discounts) {
        for(Discount discount : discounts) {
            for(String tag : discount.tags) {
                liveDiscounts.put(tag, discount);
            }
        }
    }

    public BigDecimal calculate(List<Item> items) {
        // If there are no discounts
        if(liveDiscounts.isEmpty()) {
            return BigDecimal.ZERO;
        }

        final Map<Discount, List<Item>> applicableDiscounts = findApplicableDiscounts(items);

        BigDecimal result = BigDecimal.ZERO;
        final List<Item> alreadyDiscounted = new ArrayList<>();
        for(var entry : applicableDiscounts.entrySet()) {
            final Discount discount = entry.getKey();
            final List<Item> subItems = entry.getValue();

            result = result.add(switch (discount.discountType) {
                case NONE           -> BigDecimal.ZERO;
                case BULK           -> calcBulkDiscount(discount, subItems, alreadyDiscounted);
                case BUNDLE         -> calcBundleDiscount(discount, subItems, alreadyDiscounted);
                case SEASONAL       -> calcSeasonalDiscount(discount, subItems, alreadyDiscounted);
                case PERCENTAGE     -> calcPercentageDiscount(discount, subItems, alreadyDiscounted);
                default -> throw new IllegalStateException("Unexpected value: " + discount.discountType);
            });
        }
        return result;
    }

    private BigDecimal calcPercentageDiscount(Discount discount, List<Item> subItems, List<Item> alreadyDiscounted) {
        return BigDecimal.ZERO;
    }

    private BigDecimal calcSeasonalDiscount(Discount discount, List<Item> subItems, List<Item> alreadyDiscounted) {
        return BigDecimal.ZERO;
    }

    private BigDecimal calcBundleDiscount(Discount discount, List<Item> subItems, List<Item> alreadyDiscounted) {
        return BigDecimal.ZERO;
    }

    private BigDecimal calcBulkDiscount(Discount discount, List<Item> subItems, List<Item> alreadyDiscounted) {
        BigDecimal subListSum = subItems.stream().map(item -> item.product.price).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new BigDecimal(discount.factor).multiply(subListSum);
    }

    // A single item may have multiple discounts that apply to it.
    // eg. A generic 5% loyalty bonus + a "two for one" offer; we can group these and then exclude if required.
    // The other way of phrasing this is that a single discount may have multiple items falling under it:
    private Map<Discount, List<Item>> findApplicableDiscounts(List<Item> items) {
        final Map<Discount, List<Item>> groupItemsByDiscount = liveDiscounts.values().stream().collect(Collectors.toMap(
                discount -> discount,
                discount -> items.stream().filter(item -> { // Match tags
                    String name = item.product.name;
                    String type = item.product.type;
                    return Arrays.stream(discount.tags).anyMatch(
                            tag -> tag.equalsIgnoreCase(name) || tag.equalsIgnoreCase(type)
                    ); // and match minimum quantity
                }).filter(item -> item.quantity.intValueExact() >= discount.target).collect(Collectors.toList())
        )); // Maybe better to loop through items rather than discounts... and we might care about discount value (OrderedMap)
        return groupItemsByDiscount;
    }

//    private BigDecimal applyFixedDiscount(){
//        // Apply discount given applicable items
//        if(discount.fixed != 0) {
//            // int count = entry.getValue().size();
//            sum.add();
//            // alreadyDiscounted.addAll(entry.getValue()); // Would want to think about applying multiple discounts; would probably need a flag to know when to avoid.
//            continue;
//        }
//    }

    // Might need to think about clearing / adding / expiring
    public void endDiscounts() {
        liveDiscounts.clear();
    }

}
