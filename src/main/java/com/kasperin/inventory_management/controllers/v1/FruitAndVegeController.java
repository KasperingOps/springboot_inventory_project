package com.kasperin.inventory_management.controllers.v1;

import com.kasperin.inventory_management.services.FruitAndVegeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(FruitAndVegeController.BASE_URL)
public class FruitAndVegeController {

    public static final String BASE_URL = "/api/v1/customers";

    private final FruitAndVegeService fruitAndVegeService;

    public FruitAndVegeController(FruitAndVegeService fruitAndVegeService) {
        this.fruitAndVegeService = fruitAndVegeService;
    }

    private String getCustomerUrl(Long id) {
        return FruitAndVegeController.BASE_URL + "/" + id;
    }



    @GetMapping("{name}")
    public ResponseEntity<FruitAndVegeDTO> findByName( @PathVariable String name){
        return new ResponseEntity<FruitAndVegeDTO>(
                fruitAndVegeService.findByName(name), HttpStatus.OK
        );
    }




















//    @GetMapping({"/{id}"})
//    @ResponseStatus(HttpStatus.OK)
//    public FruitAndVegeListDTO getFruitAndVegeById(@PathVariable Long id){
//        return FruitAndVegeService.findById(id);
//    }
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public FruitAndVegeListDTO getListOfFruitAndVeges(){
//        return new FruitAndVegeListDTO(fruitAndVegeService.findAll());
//    }

//    @GetMapping
//    public ResponseEntity<FruitAndVegeListDTO> findAll(){
//        return new ResponseEntity<FruitAndVegeListDTO>
//                (new FruitAndVegeListDTO(fruitAndVegeService.findAll()), HttpStatus.OK);
//    }
}
