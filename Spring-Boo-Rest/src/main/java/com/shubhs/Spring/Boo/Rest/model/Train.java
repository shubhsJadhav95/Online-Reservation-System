package com.shubhs.Spring.Boo.Rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "trains")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true, length = 10)
    private String trainNumber;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String trainName;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String fromStation;

    @NotBlank
    @Column(nullable = false, length = 50)
    private String toStation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TrainClass defaultClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getFromStation() {
        return fromStation;
    }

    public void setFromStation(String fromStation) {
        this.fromStation = fromStation;
    }

    public String getToStation() {
        return toStation;
    }

    public void setToStation(String toStation) {
        this.toStation = toStation;
    }

    public TrainClass getDefaultClass() {
        return defaultClass;
    }

    public void setDefaultClass(TrainClass defaultClass) {
        this.defaultClass = defaultClass;
    }
}


