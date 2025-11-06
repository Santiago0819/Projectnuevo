package co.edu.umanizales.projectapi.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Represents an employee of the sports center.
 * Inherits from User and adds employee-specific fields.
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
public class Employee extends User {
    public enum Position {
        MANAGER, TRAINER, RECEPTIONIST, MAINTENANCE, CLEANING
    }

    private Position position;
    private BigDecimal salary;
    private LocalDate hireDate;
    private String department;
    
    /**
     * Calculates years of service
     * @return number of years the employee has worked
     */
    public int calculateYearsOfService() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }
}
