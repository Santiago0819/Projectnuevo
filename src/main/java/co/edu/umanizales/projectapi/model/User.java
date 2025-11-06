package co.edu.umanizales.projectapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * Base abstract class for all users in the system.
 * Uses Lombok annotations to reduce boilerplate code.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private boolean active;
}
