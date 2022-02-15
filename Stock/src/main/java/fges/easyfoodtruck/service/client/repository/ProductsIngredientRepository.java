package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Ingredient;
import fges.easyfoodtruck.service.client.entity.Product;
import fges.easyfoodtruck.service.client.entity.ProductsIngredient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsIngredientRepository extends JpaRepository<ProductsIngredient, Integer> {
    ProductsIngredient findByIdIngredientAndIdProduct(Ingredient ingredient, Product product);
    List<ProductsIngredient> findAllByIdProduct(Product product);
    Boolean existsByIdProductAndIdIngredient(Product product, Ingredient ingredient);
    void deleteAllByIdProduct(Product idProduct);
}
