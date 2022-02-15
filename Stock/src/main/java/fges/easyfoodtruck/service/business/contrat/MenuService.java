package fges.easyfoodtruck.service.business.contrat;

import fges.easyfoodtruck.service.api.model.MenutDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;

import java.util.List;

public interface MenuService {

    public List<MenutDTO> getAllMenus();
    public MenutDTO createMenu(MenutDTO menutDTO) throws FunctionalException;
    public void deleteProduct(int id, List<String> productList) throws FunctionalException;
    public MenutDTO addProductInMenuId( Integer id, List<String> requestBody) throws FunctionalException;
}
