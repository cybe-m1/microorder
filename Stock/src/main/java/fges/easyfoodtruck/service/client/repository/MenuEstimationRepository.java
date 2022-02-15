package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Estimation;
import fges.easyfoodtruck.service.client.entity.Menu;
import fges.easyfoodtruck.service.client.entity.MenuEstimation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuEstimationRepository extends JpaRepository<MenuEstimation, Integer> {
    List<MenuEstimation> findAllByIdEstimation(Estimation estimation);
    Boolean existsByIdMenuAndIdEstimation(Menu menu, Estimation estimation);
}
