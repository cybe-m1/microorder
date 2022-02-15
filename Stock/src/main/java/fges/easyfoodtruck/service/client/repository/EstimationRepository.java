package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Estimation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface EstimationRepository extends JpaRepository<Estimation, Integer> {
    Boolean existsEstimationByDate(LocalDate localDate);
    Optional<Estimation> findById(Integer integer);
}
