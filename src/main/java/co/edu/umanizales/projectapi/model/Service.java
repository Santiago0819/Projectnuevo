package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.Duration;

/**
 * Represents a service offered by the sports center
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    public enum ServiceType {
        PERSONAL_TRAINING,
        PHYSIOTHERAPY,
        MASSAGE,
        NUTRITION_CONSULTATION,
        SWIMMING_LESSONS
    }

    private String id;
    private String name;
    private String description;
    private ServiceType type;
    private BigDecimal price;
    private Duration duration;
    private boolean isActive;
    
    /**
     * Calculates the total price for multiple sessions
     * @param sessionCount number of sessions
     * @return total price
     */
    public BigDecimal calculateTotalPrice(int sessionCount) {
        return price.multiply(BigDecimal.valueOf(sessionCount));
    }
}
