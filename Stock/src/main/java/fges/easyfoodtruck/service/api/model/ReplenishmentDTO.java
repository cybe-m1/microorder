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
 * containt the date and the list of product to replenishment
 */
@ApiModel(description = "containt the date and the list of product to replenishment")

public class ReplenishmentDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("date")
  private String date;

  @JsonProperty("productList")
  @Valid
  private List<ReplenishmentProductDTO> productList = null;

  public ReplenishmentDTO date(String date) {
    this.date = date;
    return this;
  }

  /**
   * the date of the estimation
   * @return date
  */
  @ApiModelProperty(required = true, readOnly = true, value = "the date of the estimation")
  @NotNull


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public ReplenishmentDTO productList(List<ReplenishmentProductDTO> productList) {
    this.productList = productList;
    return this;
  }

  public ReplenishmentDTO addProductListItem(ReplenishmentProductDTO productListItem) {
    if (this.productList == null) {
      this.productList = new ArrayList<ReplenishmentProductDTO>();
    }
    this.productList.add(productListItem);
    return this;
  }

  /**
   * Get productList
   * @return productList
  */
  @ApiModelProperty(value = "")

  @Valid

  public List<ReplenishmentProductDTO> getProductList() {
    return productList;
  }

  public void setProductList(List<ReplenishmentProductDTO> productList) {
    this.productList = productList;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ReplenishmentDTO replenishmentDTO = (ReplenishmentDTO) o;
    return Objects.equals(this.date, replenishmentDTO.date) &&
        Objects.equals(this.productList, replenishmentDTO.productList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, productList);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ReplenishmentDTO {\n");

    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    productList: ").append(toIndentedString(productList)).append("\n");
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

