package com.kasperin.inventory_management.services;

import com.kasperin.inventory_management.api.v1.model.FruitAndVegeDTO;
import com.kasperin.inventory_management.api.v1.model.FruitAndVegeListDTO;
import com.kasperin.inventory_management.domain.FruitAndVege;

import java.util.List;
import java.util.Optional;

public interface FruitAndVegeService {

//    FruitAndVege save(FruitAndVege fruitAndVege);
//
//    Optional<FruitAndVegeDTO> findById(Long id);

    FruitAndVegeDTO findByName(String name);

   List<FruitAndVegeDTO> findAll();

//    void deleteById(Long id);

}
