package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a reservation made by a member for a facility.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    public enum ReservationStatus {
        PENDING, CONFIRMED, CANCELLED, COMPLETED, NO_SHOW
    }

    private String id;
    private String memberId;
    private String facilityId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ReservationStatus status;
    private String notes;
    private double totalCost;
    
    @Builder.Default
    private List<String> equipmentIds = new ArrayList<>();
    
    /**
     * Adds equipment to this reservation
     * @param equipmentId ID of the equipment to add
     */
    public void addEquipment(String equipmentId) {
        if (!equipmentIds.contains(equipmentId)) {
            equipmentIds.add(equipmentId);
        }
    }
    
    /**
     * Removes equipment from this reservation
     * @param equipmentId ID of the equipment to remove
     */
    public void removeEquipment(String equipmentId) {
        equipmentIds.remove(equipmentId);
    }
    
    /**
     * Checks if this reservation overlaps with another reservation
     * @param other The other reservation to check against
     * @return true if the reservations overlap
     */
    public boolean overlapsWith(Reservation other) {
        if (!this.facilityId.equals(other.facilityId)) {
            return false;
        }
        return !this.endTime.isBefore(other.startTime) && 
               !this.startTime.isAfter(other.endTime);
    }
    
    /**
     * Cancels this reservation
     */
    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
    }
    
    /**
     * Checks if the reservation is active
     * @return true if the reservation is active
     */
    public boolean isActive() {
        return status == ReservationStatus.PENDING || status == ReservationStatus.CONFIRMED;
    }
}
