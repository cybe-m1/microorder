package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Estimation;
import fges.easyfoodtruck.service.client.entity.Product;
import fges.easyfoodtruck.service.client.entity.ProductEstimation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEstimationRepository  extends JpaRepository<ProductEstimation, Integer> {
    List<ProductEstimation> findAllByIdEstimation(Estimation estimation);
    Boolean existsByIdProductAndIdEstimation(Product product, Estimation estimation);
}
