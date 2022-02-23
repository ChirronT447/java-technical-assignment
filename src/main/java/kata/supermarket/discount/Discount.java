package kata.supermarket.discount;

import kata.supermarket.purchase.PurchaseType;

/**
 * @Discount(
 *      discountType = DiscountType.PERCENT,
 *      purchaseType = PurchaseType.UNIT,
 *      target = 3,
 *      factor = 0.33,
 *      fixed = 0,
 *      tags = ["raspberries", "blackberries", "blueberries", "strawberries"]
 * )
 * Discounts apply in order; names take precedence and then type.
 */
//@Documented
//@Target(ElementType.METHOD)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface Discount {
public class Discount {

    /**
     * There are a wide variety of types of discount
     * @return the DiscountType
     */
    public final DiscountType discountType; //() default DiscountType.NONE;

    /**
     *
     * @return PurchaseType
     */
    public final PurchaseType purchaseType; //() default PurchaseType.UNIT;

    /**
     * Target to hit before discount applied; based on purchaseType
     * Buy one kilo of vegetables for half price -> amount = 1kg
     * Buy one get one free -> amount = 2;
     * @return The amount
     */
    public final int target; //() default 0;

    /**
     * This represents the discount factor. ie. Some percentage off the price
     * Eg. Buy one, get one free == 50% off                     - 0.5
     *     Buy three for the price of 2 == 33.33% off           - 0.3333
     *     Buy two items for £1 == 100% off + fixed £1          - 1.0
     *     Buy one kilo of vegetables for half price == 50% off - 0.5
     * @return The discount factor
     */
    public final String factor; //() default "0";

    /**
     * Represents a fixed discount
     * eg. Buy two items for £1 == 100% off + fixed discount of 1 -1
     * @return fixed discount
     */
    public final int fixed; //() default 0;

    /**
     * This is used to determine how we group and apply discounts.
     * Tags == the groups that this discount applies to, if any.
     * eg. names like "raspberries", "mushrooms" and types such as "vegetable", "dairy", "fruit", "mealdeal"
     * @return List of groups
     */
    public final String[] tags; //() default "";


    // Additional elements:
    // - expiry: 2030-01-01,
    // - maximum: 18
    public Discount(DiscountType discountType, PurchaseType purchaseType, int target, String factor, int fixed, String[] tags) {
        this.discountType = discountType;
        this.purchaseType = purchaseType;
        this.target = target;
        this.factor = factor;
        this.fixed = fixed;
        this.tags = tags;
    }

}
