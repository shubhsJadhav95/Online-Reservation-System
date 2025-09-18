package com.shubhs.Spring.Boo.Rest.repo;

import com.shubhs.Spring.Boo.Rest.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByPnr(String pnr);
}


