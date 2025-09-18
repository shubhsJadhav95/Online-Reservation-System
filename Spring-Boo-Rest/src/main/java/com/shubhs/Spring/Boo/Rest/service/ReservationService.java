package com.shubhs.Spring.Boo.Rest.service;

import com.shubhs.Spring.Boo.Rest.model.*;
import com.shubhs.Spring.Boo.Rest.repo.ReservationRepository;
import com.shubhs.Spring.Boo.Rest.repo.TrainRepository;
import com.shubhs.Spring.Boo.Rest.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final TrainRepository trainRepository;
    private final UserRepository userRepository;
    private final SecureRandom random = new SecureRandom();

    public ReservationService(ReservationRepository reservationRepository, TrainRepository trainRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.trainRepository = trainRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(Long userId, String trainNumber, LocalDate journeyDate,
                                         String fromStation, String toStation, TrainClass trainClass) {
        User user = userRepository.findById(userId).orElseThrow();
        Train train = trainRepository.findByTrainNumber(trainNumber).orElseThrow();

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setTrain(train);
        reservation.setJourneyDate(journeyDate);
        reservation.setFromStation(fromStation);
        reservation.setToStation(toStation);
        reservation.setTrainClass(trainClass);
        reservation.setPnr(generatePnr());
        reservation.setStatus(ReservationStatus.CONFIRMED);
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getByPnr(String pnr) {
        return reservationRepository.findByPnr(pnr);
    }

    public Optional<Reservation> cancelByPnr(String pnr) {
        return reservationRepository.findByPnr(pnr).map(r -> {
            r.setStatus(ReservationStatus.CANCELLED);
            return reservationRepository.save(r);
        });
    }

    private String generatePnr() {
        StringBuilder sb = new StringBuilder(12);
        for (int i = 0; i < 12; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}


