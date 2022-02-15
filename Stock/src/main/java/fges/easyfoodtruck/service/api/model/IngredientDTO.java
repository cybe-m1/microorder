package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * an ingredient
 */
@ApiModel(description = "an ingredient")

public class IngredientDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id_ingredient")
  private Integer idIngredient;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  @JsonProperty("stock")
  private Integer stock;

  /**
   * unit of the ingredient
   */
  public enum UnitEnum {
    G("g"),

    L("l"),

    UNITARY("unitary");

    private String value;

    UnitEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static UnitEnum fromValue(String value) {
      for (UnitEnum b : UnitEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("unit")
  private UnitEnum unit;

  @JsonProperty("division")
  private Integer division;

  public IngredientDTO idIngredient(Integer idIngredient) {
    this.idIngredient = idIngredient;
    return this;
  }

  /**
   * id of the ingredient
   * @return idIngredient
  */
  @ApiModelProperty(value = "id of the ingredient")


  public Integer getIdIngredient() {
    return idIngredient;
  }

  public void setIdIngredient(Integer idIngredient) {
    this.idIngredient = idIngredient;
  }

  public IngredientDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * name of the ingredient
   * @return name
  */
  @ApiModelProperty(required = true, value = "name of the ingredient")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public IngredientDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * name of the description
   * @return description
  */
  @ApiModelProperty(required = true, value = "name of the description")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public IngredientDTO stock(Integer stock) {
    this.stock = stock;
    return this;
  }

  /**
   * integer of the ingredient
   * @return stock
  */
  @ApiModelProperty(required = true, value = "integer of the ingredient")
  @NotNull


  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }

  public IngredientDTO unit(UnitEnum unit) {
    this.unit = unit;
    return this;
  }

  /**
   * unit of the ingredient
   * @return unit
  */
  @ApiModelProperty(required = true, value = "unit of the ingredient")
  @NotNull


  public UnitEnum getUnit() {
    return unit;
  }

  public void setUnit(UnitEnum unit) {
    this.unit = unit;
  }

    public void setUnit(String unit) {
        this.unit = UnitEnum.fromValue(unit);
    }

  public IngredientDTO division(Integer division) {
    this.division = division;
    return this;
  }

  /**
   * the number of the ingredient in package
   * @return division
  */
  @ApiModelProperty(required = true, value = "the number of the ingredient in package")
  @NotNull


  public Integer getDivision() {
    return division;
  }

  public void setDivision(Integer division) {
    this.division = division;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IngredientDTO ingredientDTO = (IngredientDTO) o;
    return Objects.equals(this.idIngredient, ingredientDTO.idIngredient) &&
        Objects.equals(this.name, ingredientDTO.name) &&
        Objects.equals(this.description, ingredientDTO.description) &&
        Objects.equals(this.stock, ingredientDTO.stock) &&
        Objects.equals(this.unit, ingredientDTO.unit) &&
        Objects.equals(this.division, ingredientDTO.division);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idIngredient, name, description, stock, unit, division);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IngredientDTO {\n");

    sb.append("    idIngredient: ").append(toIndentedString(idIngredient)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    stock: ").append(toIndentedString(stock)).append("\n");
    sb.append("    unit: ").append(toIndentedString(unit)).append("\n");
    sb.append("    division: ").append(toIndentedString(division)).append("\n");
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

