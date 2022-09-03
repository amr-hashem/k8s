package com.example.plant.PlantIntegration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v2/integrationService")
public class IntegrationController {

    @Autowired
    private TheCollectiveProxy proxy;

    @GetMapping("/getPlantDetails/{plantId}")
    public Plant getPlants(
            @PathVariable(value = "plantId") final Integer plantId
    ) {
        return proxy.getPlantDetails(plantId);
    }
}
