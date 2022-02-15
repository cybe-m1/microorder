package fges.easyfoodtruck.service.business.contrat;

import fges.easyfoodtruck.service.api.model.IngredientDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;

import java.util.List;

public interface IngredientService {
    public List<IngredientDTO> getAllIngredients();
    public void deleteInbredientById(Integer id) throws FunctionalException;
    public Integer createIngredient(IngredientDTO ingredientDTO) throws FunctionalException;
    public IngredientDTO updateIngredient(IngredientDTO ingredientDTO) throws FunctionalException;
}
