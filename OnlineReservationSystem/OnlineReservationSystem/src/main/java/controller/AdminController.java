package controller;

import com.shubhsJadhav95.OnlineReservationSystem.dto.TrainDTO;
import model.UserReservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.ReservationService;
import service.TrainService;

import java.util.List;


@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/addtrains")
    public String addTrains(@RequestBody TrainDTO trainDTO) {
        trainService.addTrain(trainDTO);
        return "Train added successfully!";
    }

    @GetMapping("/reservation")
    public List<UserReservation> userReservations() {
        return reservationService.getreservation();
    }
}
