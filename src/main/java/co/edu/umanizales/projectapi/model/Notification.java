package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.time.LocalDateTime;

/**
 * Represents a notification sent to users
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    public enum NotificationType {
        RESERVATION_CONFIRMATION,
        PAYMENT_RECEIVED,
        UPCOMING_RESERVATION,
        SYSTEM_ALERT,
        PROMOTION
    }

    private String id;
    private String userId;  // Recipient
    private String title;
    private String message;
    private NotificationType type;
    private LocalDateTime sentAt;
    private boolean isRead;
    
    /**
     * Marks the notification as read
     */
    public void markAsRead() {
        this.isRead = true;
    }
    
    /**
     * Creates a notification for a reservation confirmation
     */
    public static Notification createReservationConfirmation(String userId, String reservationId) {
        return Notification.builder()
                .userId(userId)
                .title("Reservation Confirmed")
                .message(String.format("Your reservation #%s has been confirmed.", reservationId))
                .type(NotificationType.RESERVATION_CONFIRMATION)
                .sentAt(LocalDateTime.now())
                .isRead(false)
                .build();
    }
}
