package fges.easyfoodtruck.service.client.repository;

import fges.easyfoodtruck.service.client.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface UnitRepository extends JpaRepository<Unit, Integer> {
    List<Unit> findByName(String name);
}
