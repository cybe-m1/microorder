package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    Boolean existsByName(String name);
    Optional<Menu> findByName(String name);
}
