package com.shubhsJadhav95.OnlineReservationSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainDTO {

    private Long trainId;           // Train identifier
    private String trainName;       // Name of the train
    private String source;          // Starting station
    private String destination;     // Destination station
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer availableSeats; // Seats left
    private Double fare;            // Ticket price
}
