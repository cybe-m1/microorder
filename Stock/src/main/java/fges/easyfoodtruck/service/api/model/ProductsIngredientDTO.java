package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * link between ingredient and product
 */
@ApiModel(description = "link between ingredient and product")

public class ProductsIngredientDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name;

  @JsonProperty("quantity")
  private Integer quantity;

  public ProductsIngredientDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of ingredient
   * @return name
  */
  @ApiModelProperty(required = true, value = "name of ingredient")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductsIngredientDTO quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * quantity of ingredient to product
   * @return quantity
  */
  @ApiModelProperty(required = true, value = "quantity of ingredient to product")
  @NotNull


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductsIngredientDTO productsIngredientDTO = (ProductsIngredientDTO) o;
    return Objects.equals(this.name, productsIngredientDTO.name) &&
        Objects.equals(this.quantity, productsIngredientDTO.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductsIngredientDTO {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
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

