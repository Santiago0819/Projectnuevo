package co.edu.umanizales.projectapi.model;

import java.math.BigDecimal;

/**
 * Interface for different types of discounts
 */
public interface Discount {
    /**
     * Applies the discount to the original price
     * @param originalPrice The original price before discount
     * @return The discounted price
     */
    BigDecimal applyDiscount(BigDecimal originalPrice);
    
    /**
     * Gets the discount code
     * @return The discount code
     */
    String getCode();
    
    /**
     * Gets the discount description
     * @return Description of the discount
     */
    String getDescription();
    
    /**
     * Checks if the discount is valid
     * @return true if the discount is currently valid
     */
    boolean isValid();
}
