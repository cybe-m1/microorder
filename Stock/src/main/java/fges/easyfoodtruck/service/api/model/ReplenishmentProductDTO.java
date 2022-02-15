package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * the product
 */
@ApiModel(description = "the product")

public class ReplenishmentProductDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("name")
  private String name;

  @JsonProperty("quantity")
  private Integer quantity;

  @JsonProperty("division")
  private Integer division;

  @JsonProperty("unitary")
  private String unitary;

  @JsonProperty("stock")
  private Integer stock;

  public ReplenishmentProductDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the name of the ingredient
   * @return name
  */
  @ApiModelProperty(readOnly = true, value = "the name of the ingredient")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ReplenishmentProductDTO quantity(Integer quantity) {
    this.quantity = quantity;
    return this;
  }

  /**
   * the quantity of the ingredient
   * @return quantity
  */
  @ApiModelProperty(readOnly = true, value = "the quantity of the ingredient")


  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public ReplenishmentProductDTO division(Integer division) {
    this.division = division;
    return this;
  }

  /**
   * the distribution of the quantity for the ingredient
   * @return division
  */
  @ApiModelProperty(readOnly = true, value = "the distribution of the quantity for the ingredient")


  public Integer getDivision() {
    return division;
  }

  public void setDivision(Integer division) {
    this.division = division;
  }

  public ReplenishmentProductDTO unitary(String unitary) {
    this.unitary = unitary;
    return this;
  }

  /**
   * the unitary of the ingredient
   * @return unitary
  */
  @ApiModelProperty(readOnly = true, value = "the unitary of the ingredient")


  public String getUnitary() {
    return unitary;
  }

  public void setUnitary(String unitary) {
    this.unitary = unitary;
  }

  public ReplenishmentProductDTO stock(Integer stock) {
    this.stock = stock;
    return this;
  }

  /**
   * stock of this ingredient
   * @return stock
  */
  @ApiModelProperty(value = "stock of this ingredient")


  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplenishmentProductDTO replenishmentProductDTO = (ReplenishmentProductDTO) o;
    return Objects.equals(this.name, replenishmentProductDTO.name) &&
        Objects.equals(this.quantity, replenishmentProductDTO.quantity) &&
        Objects.equals(this.division, replenishmentProductDTO.division) &&
        Objects.equals(this.unitary, replenishmentProductDTO.unitary) &&
        Objects.equals(this.stock, replenishmentProductDTO.stock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, quantity, division, unitary, stock);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplenishmentProductDTO {\n");

    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    division: ").append(toIndentedString(division)).append("\n");
    sb.append("    unitary: ").append(toIndentedString(unitary)).append("\n");
    sb.append("    stock: ").append(toIndentedString(stock)).append("\n");
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

