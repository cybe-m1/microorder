package fges.easyfoodtruck.service.business.contrat;

import fges.easyfoodtruck.service.api.model.ProductDTO;
import fges.easyfoodtruck.service.api.model.ProductsIngredientDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;

import java.util.List;

public interface ProductService {

    public Integer createProduct(ProductDTO productDTO) throws FunctionalException;
    public List<ProductDTO> getAllProduct();
    public ProductDTO updateProduct(ProductDTO productDTO) throws FunctionalException;
    public ProductDTO  addIngredientInProductId(Integer id,  List<ProductsIngredientDTO> productsIngredientDTO) throws FunctionalException;
}
