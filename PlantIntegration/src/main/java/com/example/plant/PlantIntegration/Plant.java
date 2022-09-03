package com.example.plant.PlantIntegration;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Plant {
    private Integer sequenceNumber;
    private Integer year;
    private String plantState;
    private String plantName;
    private String generatorId;
    private GeneratorStatus status;
    private Long generatorAnnualNetGeneration;
}
