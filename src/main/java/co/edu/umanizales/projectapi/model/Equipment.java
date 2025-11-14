package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.time.LocalDate;

/**
 * Represents equipment that can be used in facilities or rented by members.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {
    public enum EquipmentStatus {
        AVAILABLE, IN_USE, MAINTENANCE, DAMAGED
    }

    public enum EquipmentType {
        TENNIS_RACKET, BASKETBALL, SWIM_CAP, TOWEL, LOCKER, OTHER
    }

    private String id;
    private String name;
    private String description;
    private EquipmentType type;
    private EquipmentStatus status;
    private LocalDate purchaseDate;
    private double purchasePrice;
    private Facility facility; // Current facility where the equipment is located
    private User currentUser; // If rented, ID of the user who has it
    
    /**
     * Checks if the equipment is available for rent
     * @return true if available
     */
    public boolean isAvailable() {
        Object currentUserId = null;
        return status == EquipmentStatus.AVAILABLE && currentUserId == null;
    }
    
    /**
     * Marks the equipment as rented by a user
     * @param userId ID of the user renting the equipment
     */
    public void rentToUser(String userId) {
        if (!isAvailable()) {
            throw new IllegalStateException("Equipment is not available for rent");
        }
        this.currentUserId = userId;
        this.status = EquipmentStatus.IN_USE;
    }
    
    /**
     * Returns the equipment from a user
     */
    public void returnFromUser() {
        this.currentUserId = null;
        this.status = EquipmentStatus.AVAILABLE;
    }
}
