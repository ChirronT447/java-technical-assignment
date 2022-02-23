package kata.supermarket.discount;

/**
 * There are many possible types of discount
 */
public enum DiscountType {

    /**
     * No discount
     */
    NONE,

    /**
     * Buy one, get one free; buy three for the price of two.
     */
    BULK,

    /**
     * Cross-selling to encourage customers to buy complementary items eg. dinner + dessert
     */
    BUNDLE,

    /**
     * At certain times of the year we will do seasonal discounts eg. Post Xmas
     */
    SEASONAL,

    /**
     * General % discounts may be offered to loyalty program members or voucher holders.
     */
    PERCENTAGE,

    /**
     * Free shipping when > fixed amount eg. orders over £50
     */
    FREE_SHIPPING;

}
