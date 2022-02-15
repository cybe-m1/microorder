package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * menu containt the list of product (meal, drink, dessert)
 */
@ApiModel(description = "menu containt the list of product (meal, drink, dessert)")

public class MenutDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("products")
  @Valid
  private List<String> products = new ArrayList<String>();

  public MenutDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * the id of menu
   * @return id
  */
  @ApiModelProperty(value = "the id of menu")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public MenutDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the name of the menu
   * @return name
  */
  @ApiModelProperty(required = true, value = "the name of the menu")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public MenutDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * the description of the menu
   * @return description
  */
  @ApiModelProperty(required = true, value = "the description of the menu")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public MenutDTO products(List<String> products) {
    this.products = products;
    return this;
  }

  public MenutDTO addProductsItem(String productsItem) {
    this.products.add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
  */
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<String> getProducts() {
    return products;
  }

  public void setProducts(List<String> products) {
    this.products = products;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MenutDTO menutDTO = (MenutDTO) o;
    return Objects.equals(this.id, menutDTO.id) &&
        Objects.equals(this.name, menutDTO.name) &&
        Objects.equals(this.description, menutDTO.description) &&
        Objects.equals(this.products, menutDTO.products);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, products);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MenutDTO {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

