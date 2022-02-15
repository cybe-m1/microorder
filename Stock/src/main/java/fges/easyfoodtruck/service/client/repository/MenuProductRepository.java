package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Menu;
import fges.easyfoodtruck.service.client.entity.MenuProduct;
import fges.easyfoodtruck.service.client.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuProductRepository extends JpaRepository<MenuProduct, Integer> {

    List<MenuProduct> findAllByIdMenu(Menu menu);
    Optional<MenuProduct> findAByIdMenuAndIdProduct(Menu menu, Product product);
    Boolean existsByIdMenuAndIdProduct(Menu menu, Product product);
}
