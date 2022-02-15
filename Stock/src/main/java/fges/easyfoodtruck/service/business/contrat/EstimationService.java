package fges.easyfoodtruck.service.business.contrat;

import fges.easyfoodtruck.service.api.model.EstimationDTO;
import fges.easyfoodtruck.service.api.model.MenuEstimationDTO;
import fges.easyfoodtruck.service.api.model.ProductEstimationDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;

import java.util.List;

public interface EstimationService {
    public EstimationDTO createEstimation(EstimationDTO estimationDTO) throws FunctionalException;
    public List<EstimationDTO> getAllEstimation();
    public EstimationDTO addProductEstimantionId(Integer id, List<ProductEstimationDTO> productEstimationDTOS) throws FunctionalException;
    public EstimationDTO adddMenuestimationId(Integer id,  List<MenuEstimationDTO> menuEstimationDTO) throws FunctionalException;
}
