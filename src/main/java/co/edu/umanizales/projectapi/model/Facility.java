package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a facility in the sports center (e.g., court, pool, gym).
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Facility {
    public enum FacilityType {
        TENNIS_COURT, BASKETBALL_COURT, SWIMMING_POOL, GYM, SAUNA, MEETING_ROOM
    }

    private String id;
    private String name;
    private String description;
    private FacilityType type;
    private int capacity;
    private double hourlyRate;
    private boolean isActive;
    
    @Builder.Default
    private List<String> equipmentIds = new ArrayList<>();
    
    /**
     * Adds equipment to this facility
     * @param equipmentId ID of the equipment to add
     */
    public void addEquipment(String equipmentId) {
        if (!equipmentIds.contains(equipmentId)) {
            equipmentIds.add(equipmentId);
        }
    }
    
    /**
     * Removes equipment from this facility
     * @param equipmentId ID of the equipment to remove
     */
    public void removeEquipment(String equipmentId) {
        equipmentIds.remove(equipmentId);
    }
}
