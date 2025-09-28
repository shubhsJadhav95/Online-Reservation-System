package controller;

import com.shubhsJadhav95.OnlineReservationSystem.dto.ReservationRequestDTO;
import com.shubhsJadhav95.OnlineReservationSystem.dto.ReservationResponseDTO;
import com.shubhsJadhav95.OnlineReservationSystem.dto.UserDTO;
import model.Train;
import model.User;
import model.UserReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ReservationService;
import service.TrainService;
import service.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @Autowired
    private ReservationService reservationService;

    // Register with RequestBody
    @PostMapping("/register")
    public String userRegister(@RequestBody UserDTO userDTO) {
        userService.userRegister(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        return "User Registered Successfully";
    }

    // Login with RequestBody
    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        boolean success = userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
        if (success) {
            return "Login successful!";
        } else {
            return "Invalid username or password.";
        }
    }

    // Search all trains
    @GetMapping("/search")
    public List<Train> getTrains() {
        return trainService.getAllTrains();
    }

    // Search train by ID
    @GetMapping("/search/{trainId}")
    public Train getById(@PathVariable Long trainId) {
        return trainService.getTrainById(trainId);
    }


    @PostMapping("/booktrain")
    public ReservationResponseDTO bookTrain(@RequestBody ReservationRequestDTO request) throws Exception {
        return reservationService.bookTrain(request);
    }

    @GetMapping("/{id}")
    public List<UserReservation> getUserReservations(@PathVariable Long id) {
        return reservationService.getreservationbtid(id);
    }


    @PutMapping("/{userId}/reservations/cancel/{reservationId}")
    public ReservationResponseDTO cancelReservation(
            @PathVariable Long userId,
            @PathVariable Long reservationId) throws Exception {

        // Optional: validate that the reservation belongs to this user
        UserReservation reservation = reservationService.getreservationbtid(userId).stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElseThrow(() -> new Exception("Reservation not found for this user"));

        return reservationService.cancelReservation(reservationId);
    }

}



