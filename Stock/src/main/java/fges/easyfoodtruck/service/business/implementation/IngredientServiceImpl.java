package fges.easyfoodtruck.service.business.implementation;


import fges.easyfoodtruck.service.api.model.IngredientDTO;
import fges.easyfoodtruck.service.business.contrat.IngredientService;
import fges.easyfoodtruck.service.client.entity.Ingredient;
import fges.easyfoodtruck.service.client.entity.Unit;
import fges.easyfoodtruck.service.client.repository.IngredientRepository;
import fges.easyfoodtruck.service.client.repository.UnitRepository;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngredientServiceImpl implements IngredientService {

    public static final String ID_IS_NOT_FIND = "Id is not find";
    public static final String ATTRIBUTE_IS_REQUIRED = "Id is required";
    public static final String NAME_IS_EMPTY = "name must be not empty";
    public static final String QUANTITY_IS_NEGATIVE = "quantity is not positive ";
    public static final String NAME_IS_ALREADY_IN_BDD = "quantity is not positive ";
    public static final String INGREDIENT_ALREADY_PRESENT = "this name of ingredient is already present";


    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    UnitRepository unitRepository;


    @Override
    public List<IngredientDTO> getAllIngredients() {
        return ingredientRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteInbredientById(Integer id) throws FunctionalException {
        if (!ingredientRepository.existsById(id)) {
            throw new FunctionalException(ID_IS_NOT_FIND);
        }
        ingredientRepository.deleteById(id);
    }

    @Override
    public Integer createIngredient(IngredientDTO ingredientDTO) throws FunctionalException {
        // verification
        this.handleIngredientDTO(ingredientDTO);
        Boolean existInBdd = ingredientRepository.existsByName(ingredientDTO.getName());
        if (existInBdd) {
            throw new FunctionalException(INGREDIENT_ALREADY_PRESENT);
        }
        // on met a null l'id pour eviter l'update
        ingredientDTO.setIdIngredient(null);
        Ingredient ingredient = ingredientRepository.save(toEntity(ingredientDTO));
        return ingredient.getId();
    }

    @Override
    public IngredientDTO updateIngredient(IngredientDTO ingredientDTO) throws FunctionalException {
        // verification
        this.handleIngredientDTO(ingredientDTO);
        if (ingredientDTO.getIdIngredient() == null) {
            throw new FunctionalException(ATTRIBUTE_IS_REQUIRED);
        }
        if (!ingredientRepository.existsById(ingredientDTO.getIdIngredient())) {
            throw new FunctionalException(ID_IS_NOT_FIND);
        }
        return toDTO(ingredientRepository.save(toEntity(ingredientDTO)));
    }



    public void handleIngredientDTO(IngredientDTO ingredientDTO) throws FunctionalException {
        String name = ingredientDTO.getName();
        String description = ingredientDTO.getDescription();
        int stock = ingredientDTO.getStock();
        int division = ingredientDTO.getDivision();
        // le nom n'est pas vide
        if (name.isEmpty()) {
            throw new FunctionalException(NAME_IS_EMPTY);
        }
        // si la longuur du nom depasse 20 caracteres
        if (name.length() > 20) {
            throw new FunctionalException("la longeur du nom ne peut exeder 20 caracteres");
        }
        // si la longuur de la description depasse 250 caracteres
        if (description.length() > 250) {
            throw new FunctionalException("la longeur de la description ne peut exeder 250 caracteres");
        }
        // si le stock est inferieur à 0
        if (stock < 0) {
            throw new FunctionalException(QUANTITY_IS_NEGATIVE);
        }
        // si la division est inferieur ou égal à 0
        if (division <= 0) {
            throw new FunctionalException("attribute stock and divison must positive");
        }
        // si un ingredient n'existe pas encore avec ce nom

    }

    public IngredientDTO toDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setIdIngredient(ingredient.getId());
        ingredientDTO.setName(ingredient.getName());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setStock(ingredient.getStock());
        ingredientDTO.setUnit(ingredient.getIdUnit().getName());
        ingredientDTO.setDivision(ingredient.getDivision());
        return ingredientDTO;
    }


    public  Ingredient toEntity(IngredientDTO ingredientDTO) throws FunctionalException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientDTO.getIdIngredient());
        ingredient.setName(ingredientDTO.getName());
        ingredient.setDescription(ingredientDTO.getDescription());
        ingredient.setStock(ingredientDTO.getStock());
        ingredient.setStock(ingredientDTO.getStock());
        ingredient.setDivision(ingredientDTO.getDivision());
        List<Unit> unitList = unitRepository.findByName(ingredientDTO.getUnit().name());
        if (unitList.size() != 1) {
            throw new FunctionalException("this unit is not configured");
        } else {
            ingredient.setIdUnit(unitList.get(0));
        }
        return ingredient;
    }

}

