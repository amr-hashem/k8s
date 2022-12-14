package com.thecollective.task.service;

import com.thecollective.task.bean.Plant;
import com.thecollective.task.dto.PlantDTO;
import com.thecollective.task.repo.PlantRepo;
import com.thecollective.task.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlantService {

    private final static int BATCH_SIZE = 100;

    @Autowired
    private PlantRepo plantRepo;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void saveInPatch(List<PlantDTO> plantDTOS) {
        for (int i = 0; i < plantDTOS.size(); i++) {
            if(i > 0 && i % BATCH_SIZE == 0) {
                entityManager.flush();
                entityManager.clear();
            }
            PlantDTO plantDTO = plantDTOS.get(i);
            Plant plant = Plant.builder()
                    .sequenceNumber(plantDTO.getSequenceNumber())
                    .year(plantDTO.getYear())
                    .plantState(plantDTO.getPlantState())
                    .plantName(plantDTO.getPlantName())
                    .generatorId(plantDTO.getGeneratorId())
                    .status(plantDTO.getStatus())
                    .generatorAnnualNetGeneration(plantDTO.getGeneratorAnnualNetGeneration())
                    .build();

            entityManager.merge(plant);
        }
    }

    public List<PlantDTO> getPlants()  {
        return CommonUtil.preloadExcelDataIntoBean();
    }

    public List<Plant> getTopPlants(Pageable pageable, String direction) {

        List<Plant> fetchedPlants =  plantRepo.getTopPlants(pageable);

        if (direction.equals("desc")) {
            return fetchedPlants.stream()
                    .sorted(Comparator.comparingInt(Plant::getSequenceNumber).reversed())
                    .collect(Collectors.toList());

        }else {
            return fetchedPlants;
        }
    }

    public List<Plant> getBottomPlants(Pageable pageable, String direction) {

        List<Plant> fetchedPlants =  plantRepo.getBottomPlants(pageable);

        if (direction.equals("desc")) {
            return fetchedPlants;
        }else {
            return fetchedPlants.stream()
                    .sorted(Comparator.comparingInt(Plant::getSequenceNumber))
                    .collect(Collectors.toList());
        }
    }

    public List<Plant> getPlantByState(String plantState, Pageable pageable) {
        return plantRepo.findAllByPlantState(plantState, pageable);
    }
    @Transactional
    @Cacheable(value = "plant", key = "#plantId", condition = "#plantId!=null")
    public Plant getPlantDetails(Integer plantId) {

        Optional<Plant> fetchedPlant = plantRepo.findBySequenceNumber(plantId);

        return fetchedPlant.isPresent() ? fetchedPlant.get() : null;

    }

    public Plant addNewPlant(Plant plant) {
        Plant savedPlant = plantRepo.save(plant);
        return savedPlant;
    }
}
