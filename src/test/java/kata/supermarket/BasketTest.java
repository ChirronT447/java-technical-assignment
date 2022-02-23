package kata.supermarket;

import kata.supermarket.discount.Discount;
import kata.supermarket.discount.DiscountType;
import kata.supermarket.product.Product;
import kata.supermarket.purchase.Item;
import kata.supermarket.purchase.PurchaseType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @DisplayName("basket provides its total value when containing...")
    @MethodSource
    @ParameterizedTest(name = "{0}")
    public void basketProvidesTotalValue(String description, String expectedTotal, Iterable<Item> items) {
        final Set<Discount> discounts = discounts();
        final Basket basket = new Basket(discounts);
        items.forEach(basket::add);
        assertEquals(new BigDecimal(expectedTotal), basket.total());
    }

    static Set<Discount> discounts() {
        return Set.of(
                new Discount(DiscountType.BULK, PurchaseType.UNIT, 3, "0.25", 0, new String[]{"socks"}),
                new Discount(DiscountType.PERCENTAGE, PurchaseType.WEIGHT, 2, "0.10", 0, new String[]{"potatoes"}),
                new Discount(DiscountType.SEASONAL, PurchaseType.UNIT, 5, "0.70", 0, new String[]{"turkey"})
        );
    }

    static Stream<Arguments> basketProvidesTotalValue() {
        return Stream.of(
                noItems(),
                aSingleItemPricedPerUnit(),
                multipleItemsPricedPerUnit()//,
//                aSingleItemPricedByWeight(),
//                multipleItemsPricedByWeight()
        );
    }

    private static Arguments multipleItemsPricedPerUnit() {
        return Arguments.of("multiple items priced per unit", "2.04",
                Arrays.asList(aPackOfDigestives(), aPintOfMilk()));
    }

    private static Arguments aSingleItemPricedPerUnit() {
        return Arguments.of("a single item priced per unit", "0.49", Collections.singleton(aPintOfMilk()));
    }

    private static Arguments noItems() {
        return Arguments.of("no items", "0.00", Collections.emptyList());
    }

    private static Item aPintOfMilk() {
        return new Item(new Product("milk", "dairy", "0.49", "1L", PurchaseType.UNIT), BigDecimal.ONE);
    }

    private static Item aPackOfDigestives() {
        return new Item(new Product("digestives", "biscuit", "1.55", "1", PurchaseType.UNIT), BigDecimal.ONE);
    }

}