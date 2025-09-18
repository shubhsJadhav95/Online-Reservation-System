package com.shubhs.Spring.Boo.Rest.repo;

import com.shubhs.Spring.Boo.Rest.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainRepository extends JpaRepository<Train, Long> {
    Optional<Train> findByTrainNumber(String trainNumber);
}


