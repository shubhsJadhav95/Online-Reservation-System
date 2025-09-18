package com.shubhs.Spring.Boo.Rest.controller;

import com.shubhs.Spring.Boo.Rest.model.Reservation;
import com.shubhs.Spring.Boo.Rest.model.TrainClass;
import com.shubhs.Spring.Boo.Rest.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody Map<String, String> payload) {
        Long userId = Long.parseLong(payload.get("userId"));
        String trainNumber = payload.get("trainNumber");
        LocalDate date = LocalDate.parse(payload.get("journeyDate"));
        String fromStation = payload.get("fromStation");
        String toStation = payload.get("toStation");
        TrainClass trainClass = TrainClass.valueOf(payload.get("trainClass"));
        return ResponseEntity.ok(
                reservationService.createReservation(userId, trainNumber, date, fromStation, toStation, trainClass)
        );
    }

    @GetMapping("/{pnr}")
    public ResponseEntity<?> getByPnr(@PathVariable String pnr) {
        return reservationService.getByPnr(pnr)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{pnr}/cancel")
    public ResponseEntity<?> cancel(@PathVariable String pnr) {
        return reservationService.cancelByPnr(pnr)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}


