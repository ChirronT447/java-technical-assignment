package kata.supermarket;

import kata.supermarket.product.Product;
import kata.supermarket.purchase.PurchaseType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void singleItemHasExpectedUnitPriceFromProduct() {
        final BigDecimal price = new BigDecimal("2.49");
        assertEquals(price, new Product("milk", "dairy", "2.49", "1L", PurchaseType.UNIT).price);
    }
}