package controller;


import com.shubhsJadhav95.OnlineReservationSystem.dto.TrainDTO;
import com.shubhsJadhav95.OnlineReservationSystem.dto.UserDTO;
import model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/search")
    public List<Train> getrains()
    {
        return trainService.getAllTrains();
    }

    @GetMapping("/search/{trainId}")
    public Train getById(@PathVariable Long trainId) {
        return trainService.getTrainById(trainId);
    }

}
