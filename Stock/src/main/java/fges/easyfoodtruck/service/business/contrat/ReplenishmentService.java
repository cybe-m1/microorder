package fges.easyfoodtruck.service.business.contrat;

import fges.easyfoodtruck.service.api.model.ReplenishmentDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReplenishmentService {
    public List<ReplenishmentDTO> getReplenishmentDTO() throws FunctionalException;
}
