package co.edu.umanizales.projectapi.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Record for report data transfer objects
 * @param <T> The type of data in the report
 */
public record Report<T>(
    String reportId,
    String title,
    String description,
    LocalDate startDate,
    LocalDate endDate,
    LocalDateTime generatedAt,
    T data
) {
    /**
     * Creates a new report with the current timestamp
     */
    public Report {
        if (generatedAt == null) {
            generatedAt = LocalDateTime.now();
        }
    }
    
    /**
     * Creates a new report with the current timestamp
     */
    public Report(
        String reportId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        T data
    ) {
        this(reportId, title, description, startDate, endDate, LocalDateTime.now(), data);
    }
}
