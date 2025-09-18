package com.shubhs.Spring.Boo.Rest.controller;

import com.shubhs.Spring.Boo.Rest.model.Train;
import com.shubhs.Spring.Boo.Rest.service.TrainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"})
public class TrainController {

    private final TrainService trainService;

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAll() {
        return ResponseEntity.ok(trainService.getAll());
    }

    @PostMapping
    public ResponseEntity<Train> add(@RequestBody Train train) {
        return ResponseEntity.ok(trainService.addTrain(train));
    }
}


