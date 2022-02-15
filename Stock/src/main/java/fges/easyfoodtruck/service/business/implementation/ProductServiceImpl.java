package fges.easyfoodtruck.service.business.implementation;

import fges.easyfoodtruck.service.api.model.ProductDTO;
import fges.easyfoodtruck.service.api.model.ProductsIngredientDTO;
import fges.easyfoodtruck.service.business.contrat.ProductService;
import fges.easyfoodtruck.service.client.entity.Ingredient;
import fges.easyfoodtruck.service.client.entity.Product;
import fges.easyfoodtruck.service.client.entity.ProductsIngredient;
import fges.easyfoodtruck.service.client.entity.Type;
import fges.easyfoodtruck.service.client.repository.IngredientRepository;
import fges.easyfoodtruck.service.client.repository.ProductRepository;
import fges.easyfoodtruck.service.client.repository.ProductsIngredientRepository;
import fges.easyfoodtruck.service.client.repository.TypeRepository;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.aspectj.weaver.patterns.IVerificationRequired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;


    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    ProductsIngredientRepository productsIngredientRepository;

    @Autowired
    IngredientServiceImpl ingredientService;

    @Autowired
    TypeRepository typeRepository;

    public void handleProductDTO(ProductDTO productDTO) throws FunctionalException {
        String name = productDTO.getName();
        String description = productDTO.getDescription();

        // si le nom n'est pas vide
        if (name.isEmpty()) {
            throw new FunctionalException("name is empty");
        }
        if (name.length() > 20) {
            throw new FunctionalException("la longueur du nom doit etre inferieur à 20");
        }
        if (description.length() > 250) {
            throw new FunctionalException("la longueur de la description doit etre inferieur à 250 ");
        }
    }


    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) throws FunctionalException {
        // il faut que l'id soit present
        if (productDTO.getIdProduct() == null) {
            throw new FunctionalException("id is required");
        }
        // on verifie que l'id existe
        if (!productRepository.existsById(productDTO.getIdProduct())) {
            throw new FunctionalException("product id is not found");
        }
        this.handleProductDTO(productDTO);
        Product product =  productRepository.save(this.toEntity(productDTO));
        List<ProductsIngredient> productsIngredientList = productsIngredientRepository.findAllByIdProduct(product);
        productsIngredientRepository.deleteAll(productsIngredientList);
        List<ProductsIngredient> productsIngredientList1 =  this.toEntity(productDTO.getIngredient(), product);
        productsIngredientRepository.saveAll(productsIngredientList1);
        return this.toDTO(product);
    }

   @Override
    public Integer createProduct(ProductDTO productDTO) throws FunctionalException {
        // verification
       this.handleProductDTO(productDTO);
       // on regarde s'il n'y a pas deja un nom  de meal comme ça
       if (productRepository.existsByName(productDTO.getName())) {
           throw new FunctionalException(productDTO.getName() + " is already configured");
       }
       // on lui met un id null car c'est un ajout
       productDTO.setIdProduct(null);
       Product product = productRepository.save(this.toEntity(productDTO));
       try {
           List<ProductsIngredient> productsIngredientList = this.toEntity(productDTO.getIngredient(), product);
           productsIngredientRepository.saveAll(productsIngredientList);
       } catch (Exception e) {
           productRepository.delete(product);
           throw new FunctionalException(e.getMessage());
       }

        return product.getId();
    }

    @Override
    public ProductDTO  addIngredientInProductId(Integer id,  List<ProductsIngredientDTO> productsIngredientDTO) throws FunctionalException {
       Optional<Product> product = productRepository.findById(id);
       if (product.isEmpty()) {
           throw new FunctionalException("product id is not found");
       }
       // TODO
       //  on peut se servir de cette route pour update un product
       List<ProductsIngredient> productsIngredientList = this.toEntity(productsIngredientDTO, product.get());
       productsIngredientRepository.saveAll(productsIngredientList);
        return this.toDTO(product.get());
    }


    public List<ProductsIngredientDTO> getProductsIngredient(Product product) {
        return this.toDTO(productsIngredientRepository.findAllByIdProduct(product));
    }

    @Override
    public  List<ProductDTO> getAllProduct() {
        return productRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }




    public boolean verifyQuantity(Ingredient ingredient, int quantity) {
        String name_unit = ingredient.getIdUnit().getName();
        int division = ingredient.getDivision();
        if (quantity <= 0)  {
            return false;
        }
        if (!Objects.equals(name_unit, "unitary")) {
            return quantity % division == 0;
        }
        return true;
    }
    // TRANSLATE


    // PRODUCT
    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setIdProduct(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setType(product.getIdType().getName());
        productDTO.setIngredient(this.getProductsIngredient(product));
        return productDTO;
    }

     public Product toEntity(ProductDTO productDTO) throws FunctionalException {
        Product product = new Product();
        product.setId(productDTO.getIdProduct());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        List<Type> typeList = typeRepository.findByName(productDTO.getType().name());
        if (typeList.size() == 1) {
            product.setIdType(typeList.get(0));
        } else {
            throw new FunctionalException("error bdd, type is not configured");
        }
        return product;
     }

     // PRODUCTS_INGREDIENT
     public List<ProductsIngredientDTO> toDTO(List<ProductsIngredient> productsIngredients) {
        List<ProductsIngredientDTO> productsIngredientDTOList = new ArrayList<>();
         for (ProductsIngredient productsIngredient: productsIngredients) {
             ProductsIngredientDTO productsIngredientDTO = new ProductsIngredientDTO();
             productsIngredientDTO.setName(productsIngredient.getIdIngredient().getName());
             productsIngredientDTO.setQuantity(productsIngredient.getQuantity());
             productsIngredientDTOList.add(productsIngredientDTO);
         }
         return productsIngredientDTOList;
     }


     public List<ProductsIngredient> toEntity(List<ProductsIngredientDTO> productsIngredientDTOS, Product product) throws FunctionalException {
        List<ProductsIngredient> productsIngredientList = new ArrayList<>();
         for (ProductsIngredientDTO productsIngredientDTO:productsIngredientDTOS) {
             ProductsIngredient productsIngredient = new ProductsIngredient();
             productsIngredient.setIdProduct(product);
             List<Ingredient> ingredientList = ingredientRepository.findByName(productsIngredientDTO.getName());
             if (ingredientList.size() == 1) {
                 productsIngredient.setIdIngredient(ingredientList.get(0));
             } else {
                 throw new FunctionalException(productsIngredientDTO.getName()+" is not configured");
             }
             int quantity = productsIngredientDTO.getQuantity();
             if (this.verifyQuantity(ingredientList.get(0),  quantity)) {
                 productsIngredient.setQuantity(quantity);
             } else {
                 throw new FunctionalException(" the quantity must be positive, different zero  and proportional to division ingredient");
             }
             Optional<ProductsIngredient>  productsIngredient1 = Optional.ofNullable(productsIngredientRepository.findByIdIngredientAndIdProduct(ingredientList.get(0), product));
             if (productsIngredient1.isPresent()) {
                 productsIngredient.setId(productsIngredient1.get().getId());
             } else {
                 productsIngredient.setId(null);
             }

             productsIngredientList.add(productsIngredient);
         }
         return productsIngredientList;
     }
}

