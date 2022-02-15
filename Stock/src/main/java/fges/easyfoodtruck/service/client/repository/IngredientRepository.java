package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
    List<Ingredient> findByName(String name);
    Boolean existsByName(String name);
}
