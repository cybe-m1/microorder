package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.api.model.ProductDTO;
import fges.easyfoodtruck.service.api.model.ProductsIngredientDTO;
import fges.easyfoodtruck.service.business.contrat.ProductService;
import fges.easyfoodtruck.service.exception.FunctionalException;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProductController implements ProductApi{

    @Autowired
    ProductService productService;

    @Override
    public ResponseEntity<Integer> createProduct(ProductDTO productDTO) throws FunctionalException {
       int id =  productService.createProduct(productDTO);
        return new ResponseEntity( id, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> productDTOList = productService.getAllProduct();
        return new ResponseEntity(productDTOList, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ProductDTO> updateProductId(ProductDTO productDTO) throws FunctionalException {
        ProductDTO productDTO1 = productService.updateProduct(productDTO);
        return new ResponseEntity(productDTO1, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ProductDTO> addIngredientInProductId(Integer id,  List<ProductsIngredientDTO> productsIngredientDTO) throws FunctionalException {
        ProductDTO id_priduct = productService.addIngredientInProductId(id, productsIngredientDTO);
        return new ResponseEntity(id_priduct, HttpStatus.ACCEPTED);
    }

}
