package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a reservation for a service (e.g., personal training)
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceReservation {
    public enum ServiceStatus {
        PENDING, CONFIRMED, COMPLETED, CANCELLED, NO_SHOW
    }
    
    private String id;
    private String memberId;
    private String serviceId;
    private String employeeId; // The staff member providing the service
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private ServiceStatus status;
    private String notes;
    private BigDecimal price;
    
    /**
     * Checks if this service reservation overlaps with another
     */
    public boolean overlapsWith(ServiceReservation other) {
        if (!this.employeeId.equals(other.employeeId)) {
            return false;
        }
        return !this.endTime.isBefore(other.startTime) && 
               !this.startTime.isAfter(other.endTime);
    }
    
    /**
     * Cancels this service reservation
     */
    public void cancel() {
        if (this.status == ServiceStatus.PENDING || this.status == ServiceStatus.CONFIRMED) {
            this.status = ServiceStatus.CANCELLED;
        }
    }
}
