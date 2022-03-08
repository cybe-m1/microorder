package fges.easyfoodtruck.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/**
 * Standard error
 */
@ApiModel(description = "Standard error")

public class Error  implements Serializable {
  private static final long serialVersionUID = 1L;

  @JsonProperty("errorCode")
  private String errorCode;

  @JsonProperty("errorReason")
  private String errorReason;

  @JsonProperty("errorUrl")
  private String errorUrl;

  public Error errorCode(String errorCode) {
    this.errorCode = errorCode;
    return this;
  }

  /**
   * Get errorCode
   * @return errorCode
  */
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull


  public String getErrorCode() {
    return errorCode;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public Error errorReason(String errorReason) {
    this.errorReason = errorReason;
    return this;
  }

  /**
   * Get errorReason
   * @return errorReason
  */
  @ApiModelProperty(required = true, readOnly = true, value = "")
  @NotNull


  public String getErrorReason() {
    return errorReason;
  }

  public void setErrorReason(String errorReason) {
    this.errorReason = errorReason;
  }

  public Error errorUrl(String errorUrl) {
    this.errorUrl = errorUrl;
    return this;
  }

  /**
   * Get errorUrl
   * @return errorUrl
  */
  @ApiModelProperty(readOnly = true, value = "")


  public String getErrorUrl() {
    return errorUrl;
  }

  public void setErrorUrl(String errorUrl) {
    this.errorUrl = errorUrl;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.errorCode, error.errorCode) &&
        Objects.equals(this.errorReason, error.errorReason) &&
        Objects.equals(this.errorUrl, error.errorUrl);
  }

  @Override
  public int hashCode() {
    return Objects.hash(errorCode, errorReason, errorUrl);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");

    sb.append("    errorCode: ").append(toIndentedString(errorCode)).append("\n");
    sb.append("    errorReason: ").append(toIndentedString(errorReason)).append("\n");
    sb.append("    errorUrl: ").append(toIndentedString(errorUrl)).append("\n");
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

