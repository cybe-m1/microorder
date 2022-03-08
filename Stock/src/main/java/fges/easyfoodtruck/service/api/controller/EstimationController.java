package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.api.model.EstimationDTO;
import fges.easyfoodtruck.service.api.model.ProductEstimationDTO;
import fges.easyfoodtruck.service.business.contrat.EstimationService;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EstimationController implements EstimationApi {

    @Autowired
    EstimationService estimationService;

    @Override
    public ResponseEntity<EstimationDTO> addMenuestimationId(Integer id,  List<MenuEstimationDTO> menuEstimationDTO) throws FunctionalException {
        EstimationDTO estimationDTO = estimationService.adddMenuestimationId(id, menuEstimationDTO);
        return new ResponseEntity(estimationDTO, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<EstimationDTO> addProductEstimationId(Integer id, List< ProductEstimationDTO > productEstimationDTO) throws FunctionalException {
       EstimationDTO estimationDTO = estimationService.addProductEstimantionId(id, productEstimationDTO);
        return new ResponseEntity(estimationDTO, HttpStatus.ACCEPTED);

    }


    @Override
    public ResponseEntity<EstimationDTO> createEstimation(EstimationDTO estimationDTO) throws FunctionalException {
        EstimationDTO estimationDTO1 = estimationService.createEstimation(estimationDTO);
        return new ResponseEntity(estimationDTO1, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<EstimationDTO>> getEstimations() {
        List<EstimationDTO> estimationDTOList = estimationService.getAllEstimation();
        return new ResponseEntity(estimationDTOList, HttpStatus.ACCEPTED);
    }

}
