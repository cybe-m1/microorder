package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Boolean existsByName(String name);
    Optional<Product> findByName(String name);
}
