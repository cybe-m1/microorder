package fges.easyfoodtruck.service.business.implementation;

import fges.easyfoodtruck.service.api.model.IngredientDTO;
import fges.easyfoodtruck.service.business.implementation.IngredientServiceImpl;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IngredientServiceImplTest {

    @Autowired
    IngredientServiceImpl ingredientService;


    @Test
    void handleIngredientTestSuccess() {
        //given
        Boolean thrown = false;
        String actualException=null;
        String exception=null;
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setStock(10);
        ingredientDTO.setDivision(10);
        ingredientDTO.setName("test");
        ingredientDTO.setDescription("boite d oeuf");

        //when

        try {
            this.ingredientService.handleIngredientDTO(ingredientDTO);
        } catch (FunctionalException e) {
            thrown = true;
            actualException=e.getMessage();
        }
        //then
        Assertions.assertFalse(thrown);
        Assertions.assertEquals(exception,actualException);

    }

    @Test
    void handleIngredientTestStockIsNegative() {
        //given
        Boolean thrown = false;
        String actualException=null;
        String exception= "quantity is not positive ";
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setStock(-10);
        ingredientDTO.setDivision(10);
        ingredientDTO.setName("oeuf");
        ingredientDTO.setDescription("boite d oeuf");

        //when

        try {
            this.ingredientService.handleIngredientDTO(ingredientDTO);
        } catch (FunctionalException e) {
            thrown = true;
            actualException=e.getMessage();
        }
        //then
        Assertions.assertEquals(exception,actualException);

    }

    @Test
    void handleIngredientTestDivisionIsNegative() {
        //given
        Boolean thrown = false;
        String actualException=null;
        String exception= "attribute stock and divison must positive";
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setStock(10);
        ingredientDTO.setDivision(-10);
        ingredientDTO.setName("oeuf");
        ingredientDTO.setDescription("boite d oeuf");


        //when

        try {
            this.ingredientService.handleIngredientDTO(ingredientDTO);
        } catch (FunctionalException e) {
            thrown = true;
            actualException=e.getMessage();
        }
        //then
        Assertions.assertEquals(exception,actualException);
    }

    @Test
    void handleIngredientTestNameIsEmpty() {
        //given
        Boolean thrown = false;
        String actualException=null;
        String exception= "name must be not empty";
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setStock(10);
        ingredientDTO.setDivision(10);
        ingredientDTO.setName("");

        //when

        try {
            this.ingredientService.handleIngredientDTO(ingredientDTO);
        } catch (FunctionalException e) {
            thrown = true;
            actualException=e.getMessage();
        }
        //then
        Assertions.assertEquals(exception,actualException);

    }


    @Test
    void deleteInbredientByIdSuccess() {
        //given
        Integer id = 2;
        //when
        //then
    }

    @Test
    void deleteInbredientByIdWhereIdIsNotFound() {
        //given
        Integer id = 2;
        //when
        //then
    }
}
