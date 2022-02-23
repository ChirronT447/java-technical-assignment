package kata.supermarket.discount;

import kata.supermarket.Basket;
import kata.supermarket.product.Product;
import kata.supermarket.purchase.Item;
import kata.supermarket.purchase.PurchaseType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DiscountCalculatorTest {

    @Test
    public void testDiscount() {
        final Set<Discount> discounts = Set.of(
                new Discount(DiscountType.BULK, PurchaseType.UNIT, 1, "0.25", 0, new String[]{"dairy"})
        );
        final Basket basket = new Basket(discounts);
        final List<Item> items = Arrays.asList(aPackOfDigestives(), aPintOfMilk());
        items.forEach(basket::add);

        // "1.55" + "0.49" = 2.04 (before discount)
        // "1.55" + "0.3675" = 1.9175 (after discount) - 75% of 0.49 = 0.3675
        assertEquals(new BigDecimal("1.9175"), basket.total());
    }

    private static Item aPintOfMilk() {
        return new Item(new Product("milk", "dairy", "0.49", "1L", PurchaseType.UNIT), BigDecimal.ONE);
    }

    private static Item aPackOfDigestives() {
        return new Item(new Product("digestives", "biscuit", "1.55", "1", PurchaseType.UNIT), BigDecimal.ONE);
    }

}