package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.controllers.v1.FruitAndVegeController;
import com.kasperin.inventory_management.domain.FoodType;
import com.kasperin.inventory_management.domain.ProcessedFood;
import com.kasperin.inventory_management.repository.ProcessedFoodRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProcessedFoodServiceImpl implements ProcessedFoodService {

    private final ProcessedFoodRepo processedFoodRepo;


    public ProcessedFoodServiceImpl(ProcessedFoodRepo processedFoodRepo) {
        this.processedFoodRepo = processedFoodRepo;
    }

    private String getProcessedFoodUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/id/" + id;
    }

    @Override
    public ProcessedFood save(ProcessedFood processedFood) {
        return processedFoodRepo.save(processedFood);
    }

    @Override
    public Optional<ProcessedFood> updateById(Long id, ProcessedFood proFood) {
        return processedFoodRepo.findById(id).map(processedFood -> {

            if(proFood.getInStockQuantity() != 0){
                processedFood.setInStockQuantity(proFood.getInStockQuantity());
            }
            if(proFood.getName() != null){
                processedFood.setName(proFood.getName());
            }
            if(proFood.getBarcode() != null){
                processedFood.setBarcode(proFood.getBarcode());
            }
            if(proFood.getPrice() != null){
                processedFood.setPrice(proFood.getPrice());
            }

            return processedFoodRepo.save(processedFood);
        });
    }

    @Override
    public Optional<ProcessedFood> findById(Long id) {
       return processedFoodRepo.findById(id);
    }

    @Override
    public ProcessedFood  findByName(String name) {
        ProcessedFood pf = processedFoodRepo.findByName(name);
        pf.setProcessedFoodUrl(getProcessedFoodUrl(pf.getId()));
        return pf;
    }

    @Override
    public List<ProcessedFood> findAll() {
       List<ProcessedFood> pf = processedFoodRepo.findAll();
       for(ProcessedFood p : pf){
           p.setProcessedFoodUrl(getProcessedFoodUrl(p.getId()));
       }
       return pf;
    }

    @Override
    public List<ProcessedFood> findByType(FoodType foodType) {
        return processedFoodRepo.findAllByFoodType(foodType);
    }

    @Override
    public void deleteById(Long id) {
        processedFoodRepo.deleteById(id);
    }
}
