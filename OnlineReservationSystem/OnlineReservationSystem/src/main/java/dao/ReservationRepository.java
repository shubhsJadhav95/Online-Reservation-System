package dao;

import model.UserReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReservationRepository extends JpaRepository<UserReservation, Long> {
    List<UserReservation> findByUserId(Long userId);
}
