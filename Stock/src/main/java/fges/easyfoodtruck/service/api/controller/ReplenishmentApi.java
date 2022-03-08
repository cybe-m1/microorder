/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (4.3.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.api.model.Error;
import fges.easyfoodtruck.service.api.model.ReplenishmentDTO;
import fges.easyfoodtruck.service.exception.FunctionalException;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Api(value = "Replenishment", description = "the Replenishment API")
public interface ReplenishmentApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /v1/replenishment/all : get all ReplenishmentDTO
     * get all ReplenishmentDTO
     *
     * @return Successful response - returns an array of &#x60;ReplenishmentDTO&#x60; entities. (status code 200)
     *         or Bad request (status code 400)
     */
    @ApiOperation(value = "get all ReplenishmentDTO", nickname = "getReplenishmentDTO", notes = "get all ReplenishmentDTO", response = ReplenishmentDTO.class, responseContainer = "List", tags={ "Replenishment", })
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successful response - returns an array of `ReplenishmentDTO` entities.", response = ReplenishmentDTO.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = Error.class) })
    @RequestMapping(value = "/v1/replenishment/all",
        produces = { "application/json" },
        method = RequestMethod.GET)
    default ResponseEntity<List<ReplenishmentDTO>> getReplenishmentDTO() throws FunctionalException {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"division\" : 6.027456183070403, \"quantity\" : 0.8008281904610115, \"name\" : \"name\", \"unitary\" : 1.4658129805029452 }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
