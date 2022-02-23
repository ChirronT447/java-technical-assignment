package kata.supermarket.product;

import kata.supermarket.purchase.PurchaseType;

import java.math.BigDecimal;

/**
 *
 */
public class Product {

    public final String name;
    public final String type;               // Another enum candidate
    public final BigDecimal price;
    public final String size;                // Metric system enums
    public final PurchaseType purchaseType;  // Another enum candidate

    public Product(String name, String type, String price, String size, PurchaseType purchaseType) {
        this.name = name;
        this.type = type;
        this.price = new BigDecimal(price);
        this.size = size;
        this.purchaseType = purchaseType;
    }

}
