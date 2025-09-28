package com.shubhsJadhav95.OnlineReservationSystem.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationResponseDTO {
    private Long reservationId;
    private String username;        // from User
    private String trainName;       // from Train
    private Integer numberOfSeats;
    private Double totalFare;
    private LocalDateTime bookingTime;
    private String status;          // BOOKED, CANCELLED, COMPLETED
}
