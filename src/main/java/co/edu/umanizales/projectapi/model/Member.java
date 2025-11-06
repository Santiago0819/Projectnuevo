package co.edu.umanizales.projectapi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * Represents a member of the sports center.
 * Inherits from User and adds membership-specific fields.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class Member extends User {
    private String membershipType;
    private LocalDate joinDate;
    private int maxEquipmentAllowed;
    private int currentEquipmentCount;

    /**
     * Checks if member can rent more equipment
     * @return true if member can rent more equipment
     */
    public boolean canRentMoreEquipment() {
        return currentEquipmentCount < maxEquipmentAllowed;
    }

    /**
     * Increments the equipment count when renting equipment
     */
    public void rentEquipment() {
        if (canRentMoreEquipment()) {
            currentEquipmentCount++;
        } else {
            throw new IllegalStateException("Member has reached maximum equipment limit");
        }
    }

    /**
     * Decrements the equipment count when returning equipment
     */
    public void returnEquipment() {
        if (currentEquipmentCount > 0) {
            currentEquipmentCount--;
        }
    }
}
