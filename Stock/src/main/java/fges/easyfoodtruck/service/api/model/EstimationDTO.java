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
 * the list of estimation ( menu, product)
 */
@ApiModel(description = "the list of estimation ( menu, product)")

public class EstimationDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("date")
  private String date;

  @JsonProperty("productsName")
  @Valid
  private List<ProductEstimationDTO> productsName = null;

  @JsonProperty("menusName")
  @Valid
  private List<MenuEstimationDTO> menusName = null;

  public EstimationDTO id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * the id of estimation
   * @return id
  */
  @ApiModelProperty(value = "the id of estimation")


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public EstimationDTO date(String date) {
    this.date = date;
    return this;
  }

  /**
   * the date of the estimation
   * @return date
  */
  @ApiModelProperty(required = true, value = "the date of the estimation")
  @NotNull


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public EstimationDTO productsName(List<ProductEstimationDTO> productsName) {
    this.productsName = productsName;
    return this;
  }

  public EstimationDTO addProductsNameItem(ProductEstimationDTO productsNameItem) {
    if (this.productsName == null) {
      this.productsName = new ArrayList<ProductEstimationDTO>();
    }
    this.productsName.add(productsNameItem);
    return this;
  }

  /**
   * the list of 'ProductEstimationDTO'
   * @return productsName
  */
  @ApiModelProperty(value = "the list of 'ProductEstimationDTO'")

  @Valid

  public List<ProductEstimationDTO> getProductsName() {
    return productsName;
  }

  public void setProductsName(List<ProductEstimationDTO> productsName) {
    this.productsName = productsName;
  }

  public EstimationDTO menusName(List<MenuEstimationDTO> menusName) {
    this.menusName = menusName;
    return this;
  }

  public EstimationDTO addMenusNameItem(MenuEstimationDTO menusNameItem) {
    if (this.menusName == null) {
      this.menusName = new ArrayList<MenuEstimationDTO>();
    }
    this.menusName.add(menusNameItem);
    return this;
  }

  /**
   * the list of 'ProductEstimationDTO' '#/components/schemas/
   * @return menusName
  */
  @ApiModelProperty(value = "the list of 'ProductEstimationDTO' '#/components/schemas/")

  @Valid

  public List<MenuEstimationDTO> getMenusName() {
    return menusName;
  }

  public void setMenusName(List<MenuEstimationDTO> menusName) {
    this.menusName = menusName;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EstimationDTO estimationDTO = (EstimationDTO) o;
    return Objects.equals(this.id, estimationDTO.id) &&
        Objects.equals(this.date, estimationDTO.date) &&
        Objects.equals(this.productsName, estimationDTO.productsName) &&
        Objects.equals(this.menusName, estimationDTO.menusName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, productsName, menusName);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EstimationDTO {\n");

    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    productsName: ").append(toIndentedString(productsName)).append("\n");
    sb.append("    menusName: ").append(toIndentedString(menusName)).append("\n");
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

