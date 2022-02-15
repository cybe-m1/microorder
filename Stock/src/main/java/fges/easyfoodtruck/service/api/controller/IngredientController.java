package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.api.model.IngredientDTO;
import fges.easyfoodtruck.service.business.implementation.IngredientServiceImpl;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IngredientController  implements IngredientApi{

    @Autowired
    IngredientServiceImpl ingredientService;

    @Override
    public ResponseEntity<Integer> createIngredient(IngredientDTO ingredientDTO) throws FunctionalException {
        int id  = ingredientService.createIngredient(ingredientDTO);
        return new ResponseEntity(id, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteIngredientId(Integer id) throws FunctionalException {
        ingredientService.deleteInbredientById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @Override
    public ResponseEntity<List<IngredientDTO>> getIngredients() {
        List<IngredientDTO> ingredientDTOList = ingredientService.getAllIngredients();

        return new ResponseEntity(ingredientDTOList, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<IngredientDTO> updateIngredientId(IngredientDTO ingredientDTO) throws FunctionalException {
        IngredientDTO ingredientDTO1 = ingredientService.updateIngredient(ingredientDTO);
        return new ResponseEntity(ingredientDTO1, HttpStatus.ACCEPTED);
    }

}
