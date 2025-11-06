package co.edu.umanizales.projectapi.service;

import co.edu.umanizales.projectapi.model.Notification;

/**
 * Interface for notification services
 */
public interface NotificationService {
    /**
     * Sends a notification
     * @param notification The notification to send
     * @return true if the notification was sent successfully
     */
    boolean sendNotification(Notification notification);
    
    /**
     * Gets the type of notification service
     * @return The notification service type
     */
    String getNotificationType();
    
    /**
     * Checks if the notification service is available
     * @return true if the service is available
     */
    boolean isAvailable();
}
