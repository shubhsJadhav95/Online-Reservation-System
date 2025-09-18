package com.shubhs.Spring.Boo.Rest.service;

import com.shubhs.Spring.Boo.Rest.model.Train;
import com.shubhs.Spring.Boo.Rest.repo.TrainRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    private final TrainRepository trainRepository;

    public TrainService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }

    public List<Train> getAll() {
        return trainRepository.findAll();
    }

    public Optional<Train> getByNumber(String trainNumber) {
        return trainRepository.findByTrainNumber(trainNumber);
    }

    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }
}


