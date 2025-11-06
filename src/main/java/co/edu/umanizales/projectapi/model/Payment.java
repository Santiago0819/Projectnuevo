package co.edu.umanizales.projectapi.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a payment made for a reservation or other services.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    public enum PaymentMethod {
        CASH, CREDIT_CARD, DEBIT_CARD, BANK_TRANSFER, MOBILE_PAYMENT
    }
    
    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED, REFUNDED, PARTIALLY_REFUNDED
    }
    
    private String id;
    private String reservationId; // Optional, can be null for other types of payments
    private String memberId;     // Who made the payment
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    private String transactionId; // External payment gateway transaction ID
    private String notes;
    
    /**
     * Processes the payment
     * @return true if payment was successful
     */
    public boolean process() {
        if (status == PaymentStatus.PENDING) {
            // In a real application, this would integrate with a payment processor
            this.status = PaymentStatus.COMPLETED;
            this.paymentDate = LocalDateTime.now();
            return true;
        }
        return false;
    }
    
    /**
     * Issues a refund for this payment
     * @return true if refund was successful
     */
    public boolean refund() {
        if (status == PaymentStatus.COMPLETED) {
            this.status = PaymentStatus.REFUNDED;
            return true;
        }
        return false;
    }
}
