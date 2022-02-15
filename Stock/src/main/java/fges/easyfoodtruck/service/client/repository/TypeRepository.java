package fges.easyfoodtruck.service.client.repository;


import fges.easyfoodtruck.service.client.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TypeRepository  extends JpaRepository<Type, Integer> {
    List<Type> findByName(String name);
}
