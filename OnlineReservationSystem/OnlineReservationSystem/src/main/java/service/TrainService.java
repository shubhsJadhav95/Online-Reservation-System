package service;



import com.shubhsJadhav95.OnlineReservationSystem.dto.TrainDTO;
import dao.TrainRepository;
import model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public String addTrain(TrainDTO trainDTO) {
        // Check if train already exists
        if (trainRepository.existsByTrainName(trainDTO.getTrainName())) {
            return "Train with name " + trainDTO.getTrainName() + " already exists!";
        }

        // Convert DTO -> Entity
        Train train = Train.builder()
                .trainName(trainDTO.getTrainName())
                .source(trainDTO.getSource())
                .destination(trainDTO.getDestination())
                .departureTime(trainDTO.getDepartureTime())
                .arrivalTime(trainDTO.getArrivalTime())
                .availableSeats(trainDTO.getAvailableSeats())
                .fare(trainDTO.getFare())
                .build();

        // Save to DB
        trainRepository.save(train);

        return "Train added successfully!";
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Train getTrainById(Long trainId) {
        return trainRepository.findById(trainId)
                .orElseThrow(() -> new RuntimeException("Train not found with id " + trainId));
    }
}
