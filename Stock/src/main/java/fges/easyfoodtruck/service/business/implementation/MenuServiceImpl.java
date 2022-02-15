package fges.easyfoodtruck.service.business.implementation;

import fges.easyfoodtruck.service.api.model.MenutDTO;
import fges.easyfoodtruck.service.business.contrat.MenuService;
import fges.easyfoodtruck.service.client.entity.Menu;
import fges.easyfoodtruck.service.client.entity.MenuProduct;
import fges.easyfoodtruck.service.client.entity.Product;
import fges.easyfoodtruck.service.client.repository.MenuProductRepository;
import fges.easyfoodtruck.service.client.repository.MenuRepository;
import fges.easyfoodtruck.service.client.repository.ProductRepository;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuRepository menuRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MenuProductRepository menuProductRepository;


    @Override
    public List<MenutDTO> getAllMenus() {
        return menuRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public void handleMenuDTO(MenutDTO menutDTO) throws FunctionalException {
        String name = menutDTO.getName();
        String description = menutDTO.getDescription();
        if (name.isEmpty()) {
            throw new FunctionalException(" name is empty");
        }
        // si la longuur du nom depasse 20 caracteres
        if (name.length() > 20) {
            throw new FunctionalException("la longeur du nom ne peut exeder 20 caracteres");
        }
        // si la longuur de la description depasse 250 caracteres
        if (description.length() > 250) {
            throw new FunctionalException("la longeur de la description ne peut exeder 250 caracteres");
        }
        // si le nom du menu n'existe pas encore
        if (menuRepository.existsByName(name)) {
            throw new FunctionalException("le nom du menu exisite déja");
        }

    }

    public MenutDTO createMenu(MenutDTO menutDTO) throws FunctionalException {
        // verification
        this.handleMenuDTO(menutDTO);
        Menu menu = menuRepository.save(this.toEntity(menutDTO));
        if (!menutDTO.getProducts().isEmpty()) {
            try {
                List<MenuProduct> menuProductList = this.toEntity(menutDTO.getProducts(), menu);
                menuProductRepository.saveAll(menuProductList);

            } catch (Exception e) {
                menuRepository.delete(menu);
                throw new FunctionalException(e.getMessage());
            }
        }
        return this.toDTO(menu);
    }

    public MenutDTO addProductInMenuId( Integer id, List<String> requestBody) throws FunctionalException {
       Optional<Menu>   menuOptional = menuRepository.findById(id);
       if (menuOptional.isEmpty()) {
           throw new FunctionalException("menu id is not configured");
       }
        List<MenuProduct> menuProductList = this.toEntity(requestBody, menuOptional.get());
        menuProductRepository.saveAll(menuProductList);
        return this.toDTO(menuOptional.get());
    }

    public void deleteProduct(int id, List<String> menuList) throws FunctionalException {
        Optional<Menu> menuOptional = menuRepository.findById(id);
        if (menuOptional.isEmpty()) {
            throw new FunctionalException("menu id is not configured");
        }
        List<MenuProduct> menuProductList = new ArrayList<>();
        for (String nameProduct: menuList) {
            Optional<Product> productOptional = productRepository.findByName(nameProduct);
            if (productOptional.isEmpty()) {
                throw new FunctionalException(nameProduct + " is not configured");
            }
           Optional<MenuProduct> menuProductOptional = menuProductRepository.findAByIdMenuAndIdProduct(menuOptional.get(), productOptional.get());
            if (menuProductOptional.isEmpty()) {
                throw new FunctionalException(nameProduct +  "is not add in menu");
            }
            menuProductList.add(menuProductOptional.get());
        }
        menuProductRepository.deleteAll(menuProductList);
    }

    public List<String> getMenuProductByProduct(Menu menu) {
        return this.toDTO(menuProductRepository.findAllByIdMenu(menu));
    }


    // Translate
    public MenutDTO toDTO(Menu menu) {
        MenutDTO menutDTO = new MenutDTO();
        menutDTO.setId(menu.getId());
        menutDTO.setName(menu.getName());
        menutDTO.setDescription(menu.getDescription());
        menutDTO.setProducts(this.getMenuProductByProduct(menu));
        menutDTO.setId(menu.getId());
        return menutDTO;
    }

    public Menu  toEntity(MenutDTO menutDTO) {
        Menu menu = new Menu();
        menu.setId(null);
        menu.setDescription(menutDTO.getDescription());
        menu.setName(menutDTO.getName());
        return menu;
    }

    public List<MenuProduct> toEntity(List<String> stringList, Menu menu) throws FunctionalException {
        List<MenuProduct> menuProductList = new ArrayList<>();
        for (String menuService: stringList) {
            MenuProduct menuProduct = new MenuProduct();
            menuProduct.setId(null);
            Optional<Product> product = productRepository.findByName(menuService);
            if (product.isEmpty()) {
                throw new FunctionalException(" name product is not configured");
            } else {
                menuProduct.setIdProduct(product.get());
            }
          if (menuProductRepository.existsByIdMenuAndIdProduct(menu, product.get())) {
              throw new FunctionalException(product.get().getName()+" is already add");
          }
            menuProduct.setIdMenu(menu);
            menuProductList.add(menuProduct);
        }
        return menuProductList;
    }

    public List<String> toDTO (List<MenuProduct> menuProductList) {
        List<String>  stringList = new ArrayList<>();
        for (MenuProduct menuProduct: menuProductList) {
            stringList.add(menuProduct.getIdProduct().getName());
        }
        return stringList;
    }

}
