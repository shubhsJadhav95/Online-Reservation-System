package service;

import com.shubhsJadhav95.OnlineReservationSystem.dto.ReservationRequestDTO;
import com.shubhsJadhav95.OnlineReservationSystem.dto.ReservationResponseDTO;
import dao.ReservationRepository;
import dao.TrainRepository;
import dao.UserRepository;
import model.Train;
import model.User;
import model.UserReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private TrainRepository trainRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ReservationRepository reservationRepo;


    @Transactional
    public ReservationResponseDTO bookTrain(ReservationRequestDTO request) throws Exception {
        Train train = trainRepo.findById(request.getTrainId())
                .orElseThrow(() -> new Exception("Train not found"));

        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new Exception("User not found"));

        if (train.getAvailableSeats() < request.getNumberOfSeats()) {
            throw new Exception("Not enough seats available");
        }

        // Deduct seats
        train.setAvailableSeats(train.getAvailableSeats() - request.getNumberOfSeats());
        trainRepo.save(train);

        // Create reservation entity
        UserReservation reservation = new UserReservation();
        reservation.setUserId(user.getId());
        reservation.setTrainId(train.getTrainId());
        reservation.setSeatsBooked(request.getNumberOfSeats());
        reservation.setBookingTime(LocalDateTime.now());
        reservation.setStatus("BOOKED");


        reservation = reservationRepo.save(reservation);

//        Reservation reserv = new Reservation(); // reservation model
//        reserv.getReservationId();
//        reserv.getUserId();
//        reserv.getTrainId();
//        reserv.getNumberOfSeats();
//        reserv.getBookingTime();
//        reserv.getStatus();
//        reserv.getTotalFare();
//// Save to DB
//       reserv = userReservationDetail.save(reserv);

        // Calculate fare (example: train.getFarePerSeat() * seats)
        double totalFare = train.getFare() * request.getNumberOfSeats();

        // Build response DTO
        return ReservationResponseDTO.builder()
                .reservationId(reservation.getId())
                .username(user.getUsername())
                .trainName(train.getTrainName())
                .numberOfSeats(reservation.getSeatsBooked())
                .totalFare(totalFare)
                .bookingTime(reservation.getBookingTime())
                .status(reservation.getStatus())
                .build();
    }

    public List<UserReservation> getreservation() {
        return reservationRepo.findAll();

    }

    public List<UserReservation> getreservationbtid(Long id) {
        return reservationRepo.findByUserId(id);
    }

    @Transactional
    public ReservationResponseDTO cancelReservation(Long reservationId) throws Exception {
        // Find reservation
        UserReservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new Exception("Reservation not found"));

        if (reservation.getStatus().equalsIgnoreCase("CANCELLED")) {
            throw new Exception("Reservation is already cancelled");
        }

        // Find train to update available seats
        Train train = trainRepo.findById(reservation.getTrainId())
                .orElseThrow(() -> new Exception("Train not found"));

        // Add the seats back
        train.setAvailableSeats(train.getAvailableSeats() + reservation.getSeatsBooked());
        trainRepo.save(train);

        // Update reservation status
        reservation.setStatus("CANCELLED");
        reservationRepo.save(reservation);

        // Find user for response
        User user = userRepo.findById(reservation.getUserId())
                .orElseThrow(() -> new Exception("User not found"));

        // Calculate total fare (optional, could return 0 or original fare)
        double totalFare = train.getFare() * reservation.getSeatsBooked();

        // Build response
        return ReservationResponseDTO.builder()
                .reservationId(reservation.getId())
                .username(user.getUsername())
                .trainName(train.getTrainName())
                .numberOfSeats(reservation.getSeatsBooked())
                .totalFare(totalFare)
                .bookingTime(reservation.getBookingTime())
                .status(reservation.getStatus())
                .build();
    }

}

