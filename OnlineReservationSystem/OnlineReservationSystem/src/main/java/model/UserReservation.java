package model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_reservations")
@Data
public class UserReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // reservationId in DTO

    @Column(nullable = false)
    private Long userId;   // FK → User

    @Column(nullable = false)
    private Long trainId;  // FK → Train

    @Column(nullable = false)
    private Integer seatsBooked;

    @Column(nullable = false)
    private LocalDateTime bookingTime;

    @Column(nullable = false)
    private String status;   // BOOKED, CANCELLED, COMPLETED
}
