package kata.supermarket.purchase;

import kata.supermarket.product.Product;
import kata.supermarket.purchase.PurchaseType;

import java.math.BigDecimal;

/**
 * Simple model:
 *    "name": "potato",
 *    "type": "vegetable",
 *    "price": "1.30",
 *    "per": "1kg",
 *    "purchaseType": "WEIGHT"
 */
public class Item {

    public final Product product;
    public final BigDecimal quantity;

    public Item(final Product product, final BigDecimal quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal price() {
        return product.price
                .multiply(quantity)
                .setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
