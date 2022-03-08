package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.api.model.ReplenishmentDTO;
import fges.easyfoodtruck.service.business.contrat.ReplenishmentService;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ReplenishmentController implements ReplenishmentApi {

    @Autowired
    ReplenishmentService replenishmentService;
    @Override
    public ResponseEntity<List<ReplenishmentDTO>> getReplenishmentDTO() throws FunctionalException {
        List<ReplenishmentDTO> replenishmentDTOList = replenishmentService.getReplenishmentDTO();
        return new ResponseEntity(replenishmentDTOList, HttpStatus.ACCEPTED);
    }
}
