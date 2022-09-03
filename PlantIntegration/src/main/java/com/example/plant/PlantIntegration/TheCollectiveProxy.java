package com.example.plant.PlantIntegration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "theCollective", url = "${THECOLLECTIVE_SERVICE_HOST:http://localhost}:8080")
public interface TheCollectiveProxy {

    @GetMapping("/api/v1/plants/{plantId}")
    Plant getPlantDetails(
            @PathVariable(value = "plantId") final Integer plantId

    );
}