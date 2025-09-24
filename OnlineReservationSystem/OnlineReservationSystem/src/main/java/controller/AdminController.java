package controller;

import com.shubhsJadhav95.OnlineReservationSystem.dto.TrainDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.TrainService;


@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/addtrains")
    public String addTrains(@RequestBody TrainDTO trainDTO) {
        trainService.addTrain(trainDTO);
        return "Train added successfully!";
    }
}
