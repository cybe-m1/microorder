package fges.easyfoodtruck.service.business.implementation;

import fges.easyfoodtruck.service.api.model.*;
import fges.easyfoodtruck.service.business.contrat.EstimationService;
import fges.easyfoodtruck.service.business.contrat.ReplenishmentService;
import fges.easyfoodtruck.service.client.entity.*;
import fges.easyfoodtruck.service.client.repository.MenuProductRepository;
import fges.easyfoodtruck.service.client.repository.MenuRepository;
import fges.easyfoodtruck.service.client.repository.ProductRepository;
import fges.easyfoodtruck.service.client.repository.ProductsIngredientRepository;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReplenishmentServiceImpl implements ReplenishmentService {


    @Autowired
    EstimationService estimationService;

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    MenuProductRepository menuProductRepository;

    @Autowired
    ProductsIngredientRepository productsIngredientRepository;

    @Autowired
    ProductRepository productRepository;




    public List<ProductEstimationDTO> menuToListProduct(List<MenuEstimationDTO> menuEstimationList) throws FunctionalException {
        List<ProductEstimationDTO> productEstimationDTOList = new ArrayList<>();
        for (MenuEstimationDTO menuEstimation: menuEstimationList) {
            String nameMenu = menuEstimation.getName();
            Optional<Menu> menuOptional =  menuRepository.findByName(nameMenu);
            if (menuOptional.isEmpty()) {
                throw new FunctionalException(nameMenu +" is not configured or deleted");
            }
            Menu menu = menuOptional.get();
            for (MenuProduct menuProduct: menuProductRepository.findAllByIdMenu(menu)) {
                ProductEstimationDTO productEstimationDTO = new ProductEstimationDTO();
                productEstimationDTO.setName(menuProduct.getIdProduct().getName());
                productEstimationDTO.setQuantity(menuEstimation.getQuantity());
                productEstimationDTOList.add(productEstimationDTO);
            }
        }
        return productEstimationDTOList;
    }


    public List<ReplenishmentProductDTO> getAllProduct(List<ProductEstimationDTO> productEstimationDTOS) throws FunctionalException {
        List<ReplenishmentProductDTO> replenishmentProductDTOS = new ArrayList<>();
        for (ProductEstimationDTO productEstimationDTO:productEstimationDTOS ) {
            int quantityEstimation = productEstimationDTO.getQuantity();
            Optional<Product> productOptional = productRepository.findByName(productEstimationDTO.getName());
            if (productOptional.isEmpty()) {
                throw new FunctionalException(productEstimationDTO.getName() +" is not configured or deleted");
            }
            Product product = productOptional.get();
            List<ProductsIngredient> productsIngredientList = productsIngredientRepository.findAllByIdProduct(product);
            for (ProductsIngredient productsIngredient: productsIngredientList ) {
                ReplenishmentProductDTO replenishmentProductDTO = new ReplenishmentProductDTO();
                Ingredient ingredient = productsIngredient.getIdIngredient();
                List<ReplenishmentProductDTO> replenishmentDTOList = replenishmentProductDTOS.stream().filter(elt -> elt.getName().equals(ingredient.getName())).collect(Collectors.toList());
                if (replenishmentDTOList.size() == 0) {
                    replenishmentProductDTO.setName(ingredient.getName());
                    replenishmentProductDTO.setDivision(ingredient.getDivision());
                    replenishmentProductDTO.setUnitary(ingredient.getIdUnit().getName());
                    quantityEstimation *= productsIngredient.getQuantity();
                    replenishmentProductDTO.setQuantity(quantityEstimation);
                    replenishmentProductDTO.setStock(ingredient.getStock());
                    replenishmentProductDTOS.add(replenishmentProductDTO);
                } else {
                   for (ReplenishmentProductDTO replenishmentProductDTO1 :replenishmentProductDTOS ) {
                       if (replenishmentProductDTO1.getName().equals(ingredient.getName())) {

                           int actualQuantity = replenishmentProductDTO1.getQuantity();
                           replenishmentProductDTO1.setQuantity(actualQuantity + productsIngredient.getQuantity() * quantityEstimation );
                       }
                   }
                }

            }
        }
        return replenishmentProductDTOS;
    }


    public List<ReplenishmentProductDTO> calculNewStock(List<ReplenishmentProductDTO> replenishmentProductDTOList) {
        for (ReplenishmentProductDTO replenishmentProductDTO: replenishmentProductDTOList) {
            int stockunit = replenishmentProductDTO.getDivision() * replenishmentProductDTO.getStock();
            if (stockunit >= replenishmentProductDTO.getQuantity()) {
               replenishmentProductDTO.setQuantity(0);
            }
            else {
                float numerator = (replenishmentProductDTO.getQuantity() - stockunit);
                float newQuantity = numerator / replenishmentProductDTO.getDivision();
                int newQuantityInt = (int)newQuantity;
                if (newQuantity != newQuantityInt) {
                    newQuantityInt+= 1;
                }
                replenishmentProductDTO.setQuantity(newQuantityInt);
            }
        }
        return replenishmentProductDTOList.stream().filter(elt -> elt.getQuantity() != 0).collect(Collectors.toList());
    }

    @Override
    public List<ReplenishmentDTO> getReplenishmentDTO() throws FunctionalException {
        List<ReplenishmentDTO> replenishmentDTOList = new ArrayList<>();
        LocalDate today = LocalDate.now();
        List<EstimationDTO> estimationDTOList = estimationService.getAllEstimation().stream().filter(elt -> LocalDate.parse(elt.getDate()).isAfter(today)).collect(Collectors.toList());
        for(EstimationDTO estimationDTO: estimationDTOList) {

            ReplenishmentDTO replenishmentDTO = new ReplenishmentDTO();
            replenishmentDTO.setDate(estimationDTO.getDate());

            List<ProductEstimationDTO> productEstimationDTOListMenu = this.menuToListProduct(estimationDTO.getMenusName());
            List<ProductEstimationDTO> productEstimationDTOListProduct =  estimationDTO.getProductsName();
            productEstimationDTOListMenu.addAll(productEstimationDTOListProduct);

            replenishmentDTO.setProductList(this.calculNewStock(this.getAllProduct(productEstimationDTOListMenu)));

            replenishmentDTOList.add(replenishmentDTO);
        }
        return replenishmentDTOList;
    }

}
