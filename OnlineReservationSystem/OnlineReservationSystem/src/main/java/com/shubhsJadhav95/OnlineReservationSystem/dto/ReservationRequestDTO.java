package com.shubhsJadhav95.OnlineReservationSystem.dto;


import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class ReservationRequestDTO {

                @NotNull
                private Long userId;

                @NotNull
                private Long trainId;

                private Integer numberOfSeats;
        }




