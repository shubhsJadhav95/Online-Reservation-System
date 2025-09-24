package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "trains")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainId;   // primary key (auto-incremented)

    @Column(nullable = false, unique = true)
    private String trainName;  // train name (unique)

    @Column(nullable = false)
    private String source;     // starting station

    @Column(nullable = false)
    private String destination; // ending station

    @Column(nullable = false)
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Integer availableSeats;

    @Column(nullable = false)
    private Double fare;
}
