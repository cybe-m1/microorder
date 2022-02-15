package fges.easyfoodtruck.service.business.implementation;

import fges.easyfoodtruck.service.api.model.EstimationDTO;
import fges.easyfoodtruck.service.api.model.MenuEstimationDTO;
import fges.easyfoodtruck.service.api.model.ProductEstimationDTO;
import fges.easyfoodtruck.service.business.contrat.EstimationService;
import fges.easyfoodtruck.service.client.entity.*;
import fges.easyfoodtruck.service.client.repository.*;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstimationServiceImpl  implements EstimationService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    EstimationRepository estimationRepository;

    @Autowired
    MenuEstimationRepository menuEstimationRepository;

    @Autowired
    ProductEstimationRepository productEstimationRepository;


    // POST
    public EstimationDTO createEstimation(EstimationDTO estimationDTO) throws FunctionalException {
        Estimation estimation = this.toEntity(estimationDTO);
        Estimation estimationResult = estimationRepository.save(estimation);
        try {
            if (!estimationDTO.getMenusName().isEmpty()) {
                List<MenuEstimation> menuEstimationList = this.toEntityMenu(estimationDTO.getMenusName(), estimation);
                List<MenuEstimation> menuEstimationsResult = menuEstimationRepository.saveAll(menuEstimationList);
            }
            if (!estimationDTO.getProductsName().isEmpty()) {
                List<ProductEstimation> productEstimationList = this.toEntityEstimation(estimationDTO.getProductsName(), estimation);
                List<ProductEstimation> productEstimationsResult = productEstimationRepository.saveAll(productEstimationList);
            }

        } catch (Exception e) {
            estimationRepository.delete(estimationResult);
            throw new FunctionalException(e.getMessage());
        }
        return this.toDTO(estimationResult);
    }

    public EstimationDTO adddMenuestimationId(Integer id,  List<MenuEstimationDTO> menuEstimationDTO) throws FunctionalException {
        Optional<Estimation> estimationOptional = estimationRepository.findById(id);
        if (estimationOptional.isEmpty()) {
            throw new FunctionalException("id is not configured");
        }
        List<MenuEstimation> menuEstimationList = this.toEntityMenu(menuEstimationDTO, estimationOptional.get());
        List<MenuEstimation> result = menuEstimationRepository.saveAll(menuEstimationList);
        return this.toDTO(estimationOptional.get());
    }

    public EstimationDTO addProductEstimantionId(Integer id, List<ProductEstimationDTO> productEstimationDTOS) throws FunctionalException {
        Optional<Estimation> estimationOptional = estimationRepository.findById(id);
        if (estimationOptional.isEmpty()) {
            throw new FunctionalException("id is not configured");
        }
        List<ProductEstimation> productEstimationList = this.toEntityEstimation(productEstimationDTOS, estimationOptional.get());
        List<ProductEstimation> result  = productEstimationRepository.saveAll(productEstimationList);
        return this.toDTO(estimationOptional.get());
    }


    // get
    public List<MenuEstimationDTO> getAllMenuEstimation(Estimation estimation) {
        List<MenuEstimation> menuEstimationList = menuEstimationRepository.findAllByIdEstimation(estimation);
        return this.toDTOMenu(menuEstimationList);
    }

    public List<ProductEstimationDTO> getAllProductEstimation(Estimation estimation) {
        List<ProductEstimation> productEstimationList = productEstimationRepository.findAllByIdEstimation(estimation);
        return this.toDTOProduct(productEstimationList);
    }

    public List<EstimationDTO> getAllEstimation() {
        return estimationRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }



    // date
    public LocalDate stringToLocalDate(String date) throws FunctionalException {
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(date);
        } catch (Exception e) {
            throw new FunctionalException("format date is not correct AAAA-MM-JJ");
        }
        LocalDate todaysDate = LocalDate.now();
        if (todaysDate.isAfter(localDate)) {
            throw new FunctionalException("the date must be superior at today");
        }
        if (estimationRepository.existsEstimationByDate(localDate)) {
            throw new FunctionalException("the date is already configured, use /estimation/add_product or /estimation/add_menu to add an estimation");
        }
        return localDate;
    }


    // translate

    public EstimationDTO toDTO(Estimation estimation) {
        EstimationDTO estimationDTO = new EstimationDTO();
        estimationDTO.setId(estimation.getId());
        estimationDTO.setDate(estimation.getDate().toString());
        estimationDTO.setMenusName(this.getAllMenuEstimation(estimation));
        estimationDTO.setProductsName(this.getAllProductEstimation(estimation));
        return estimationDTO;
    }

    public Estimation toEntity(EstimationDTO estimationDTO) throws FunctionalException {
        Estimation estimation = new Estimation();
        estimation.setDate(this.stringToLocalDate(estimationDTO.getDate()));
        return estimation;
    }

    public List<MenuEstimationDTO> toDTOMenu(List<MenuEstimation> menuEstimationList) {
        List<MenuEstimationDTO> menuEstimationDTOList = new ArrayList<>();
        for (MenuEstimation  estimationMenu: menuEstimationList) {
            MenuEstimationDTO menuEstimationDTO = new MenuEstimationDTO();
            menuEstimationDTO.setName(estimationMenu.getIdMenu().getName());
            menuEstimationDTO.setQuantity(estimationMenu.getQuantity());
            menuEstimationDTOList.add(menuEstimationDTO);
        }
        return menuEstimationDTOList;
    }

    public List<ProductEstimationDTO> toDTOProduct(List<ProductEstimation> productEstimationList) {
        List<ProductEstimationDTO> productEstimationDTOList = new ArrayList<>();
        for (ProductEstimation  productEstimation: productEstimationList) {
            ProductEstimationDTO productEstimationDTO = new ProductEstimationDTO();
            productEstimationDTO.setName(productEstimation.getIdProduct().getName());
            productEstimationDTO.setQuantity(productEstimation.getQuantity());
            productEstimationDTOList.add(productEstimationDTO);
        }
        return productEstimationDTOList;
    }

    public List<MenuEstimation> toEntityMenu(List<MenuEstimationDTO> menuEstimationDTOList, Estimation estimation) throws FunctionalException {
        List<MenuEstimation> menuEstimationList = new ArrayList<>();
        for (MenuEstimationDTO menuEstimationDTO:menuEstimationDTOList) {
            MenuEstimation menuEstimation = new MenuEstimation();
            menuEstimation.setId(null);
            menuEstimation.setIdEstimation(estimation);
            Optional<Menu> menuOptional = menuRepository.findByName(menuEstimationDTO.getName());
            if (menuOptional.isEmpty()) {
                throw new FunctionalException(menuEstimationDTO.getName() + " is not configured");
            }
            if (menuEstimationRepository.existsByIdMenuAndIdEstimation(menuOptional.get(), estimation)) {
                throw new FunctionalException(menuEstimationDTO.getName() + " is already add in estimation");
            }
            menuEstimation.setIdMenu(menuOptional.get());
            if (menuEstimationDTO.getQuantity() <= 0) {
                throw new FunctionalException(menuEstimationDTO.getName() + ": the quantity must be superior to zero");
            }
            menuEstimation.setQuantity(menuEstimationDTO.getQuantity());
            menuEstimationList.add(menuEstimation);
        }
        return menuEstimationList;
    }

    public List<ProductEstimation> toEntityEstimation(List<ProductEstimationDTO> productEstimationDTOList, Estimation estimation) throws FunctionalException {
        List<ProductEstimation> productEstimationList = new ArrayList<>();
        for (ProductEstimationDTO productEstimationDTO: productEstimationDTOList) {
            ProductEstimation productEstimation = new ProductEstimation();
            productEstimation.setId(null);
            productEstimation.setIdEstimation(estimation);
            if (productEstimationDTO.getQuantity() <= 0) {
                throw new FunctionalException(productEstimationDTO.getName() + ": the quantity must be superior to zero");
            }
            productEstimation.setQuantity(productEstimationDTO.getQuantity());
            Optional<Product> productOptional = productRepository.findByName(productEstimationDTO.getName());
            if (productOptional.isEmpty()) {
                throw new FunctionalException(productEstimationDTO.getName() + " is not configured");
            }
            if (productEstimationRepository.existsByIdProductAndIdEstimation(productOptional.get(), estimation)) {
                throw new FunctionalException(productEstimationDTO.getName() + " is already add in estimation");
            }
            productEstimation.setIdProduct(productOptional.get());
            productEstimationList.add(productEstimation);

        }
        return productEstimationList;
    }
}
