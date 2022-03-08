package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * product content the list of the ingredient
 */
@ApiModel(description = "product content the list of the ingredient")

public class ProductDTO  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("id_product")
  private Integer idProduct;

  @JsonProperty("name")
  private String name;

  @JsonProperty("description")
  private String description;

  /**
   * type enum, type of the product
   */
  public enum TypeEnum {
    MEAL("meal"),

    DRINK("drink"),

    DESSERT("dessert");

    private String value;

    TypeEnum(String value) {
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
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  @JsonProperty("type")
  private TypeEnum type;

  @JsonProperty("ingredient")
  @Valid
  private List<ProductsIngredientDTO> ingredient = null;

  public ProductDTO idProduct(Integer idProduct) {
    this.idProduct = idProduct;
    return this;
  }

  /**
   * id of the product
   * @return idProduct
  */
  @ApiModelProperty(value = "id of the product")


  public Integer getIdProduct() {
    return idProduct;
  }

  public void setIdProduct(Integer idProduct) {
    this.idProduct = idProduct;
  }

  public ProductDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * the name of the product
   * @return name
  */
  @ApiModelProperty(required = true, value = "the name of the product")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDTO description(String description) {
    this.description = description;
    return this;
  }

  /**
   * description of the product
   * @return description
  */
  @ApiModelProperty(required = true, value = "description of the product")
  @NotNull


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ProductDTO type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * type enum, type of the product
   * @return type
  */
  @ApiModelProperty(required = true, value = "type enum, type of the product")
  @NotNull


  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }
  public void setType(String type) {
        this.type = TypeEnum.fromValue(type);
    }

  public ProductDTO ingredient(List<ProductsIngredientDTO> ingredient) {
    this.ingredient = ingredient;
    return this;
  }

  public ProductDTO addIngredientItem(ProductsIngredientDTO ingredientItem) {
    if (this.ingredient == null) {
      this.ingredient = new ArrayList<ProductsIngredientDTO>();
    }
    this.ingredient.add(ingredientItem);
    return this;
  }

  /**
   * the list of the ingredient
   * @return ingredient
  */
  @ApiModelProperty(value = "the list of the ingredient")

  @Valid

  public List<ProductsIngredientDTO> getIngredient() {
    return ingredient;
  }

  public void setIngredient(List<ProductsIngredientDTO> ingredient) {
    this.ingredient = ingredient;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDTO productDTO = (ProductDTO) o;
    return Objects.equals(this.idProduct, productDTO.idProduct) &&
        Objects.equals(this.name, productDTO.name) &&
        Objects.equals(this.description, productDTO.description) &&
        Objects.equals(this.type, productDTO.type) &&
        Objects.equals(this.ingredient, productDTO.ingredient);
  }

  @Override
  public int hashCode() {
    return Objects.hash(idProduct, name, description, type, ingredient);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDTO {\n");

    sb.append("    idProduct: ").append(toIndentedString(idProduct)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    ingredient: ").append(toIndentedString(ingredient)).append("\n");
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

