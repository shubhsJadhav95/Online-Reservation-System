package model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor  // JPA requires a no-args constructor
@AllArgsConstructor // constructor with all fields
@Builder            // optional: makes object creation easier
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // primary key (auto-incremented)

    @Column(nullable = false, unique = true)
    private String username;  // unique username

    @Column(nullable = false)
    private String password;  // hashed password

    @Column(nullable = false, unique = true)
    private String email;// unique email

    @Enumerated(EnumType.STRING)
    private Role role; // ADMIN or CUSTOMER


public enum Role {
    ADMIN,
    CUSTOMER
}

}
