package fges.easyfoodtruck.service.api.controller;


import fges.easyfoodtruck.service.api.model.MenutDTO;
import fges.easyfoodtruck.service.business.contrat.MenuService;
import fges.easyfoodtruck.service.exception.FunctionalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MenuController implements  MenuApi{

    @Autowired
    MenuService menuService;

    @Override
    public ResponseEntity<MenutDTO> addProductInMenuId( Integer id, List<String> requestBody) throws FunctionalException {
        MenutDTO menu = menuService.addProductInMenuId(id, requestBody);
        return new ResponseEntity(menu, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<MenutDTO> createMenu(MenutDTO menutDTO) throws FunctionalException {
        MenutDTO menutDTO1 = menuService.createMenu(menutDTO);
        return new ResponseEntity(menutDTO1, HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> deleteProductIdInMenu(Integer id, List<String> productList) throws FunctionalException {
        menuService.deleteProduct(id, productList);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<MenutDTO>> getMenus() {
        List<MenutDTO> menutDTOS = menuService.getAllMenus();
        return new ResponseEntity(menutDTOS, HttpStatus.ACCEPTED);
    }
}
