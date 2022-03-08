package fges.easyfoodtruck.service.api.controller;

import fges.easyfoodtruck.service.exception.FunctionalException;
import fges.easyfoodtruck.service.exception.TechnicalException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    public final static org.slf4j.Logger LOG = LoggerFactory.getLogger(CustomRestExceptionHandler.class);
    Map<String, String> errorMessages = new HashMap<>();
    /*
        MethodArgumentNotValidException : This exception is thrown when argument annotated with @Valid failed validation
     */
    @PostConstruct
    private void initErrorMessages(){
        errorMessages.put("ne peut pas être nul", "can not be null");
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String englishVersion = null;
            for(String key : errorMessages.keySet()){
                if(error.getDefaultMessage().contains(key));
                englishVersion = error.getDefaultMessage().replace(key, errorMessages.get(key));
            }
            errors.add(error.getField() + ": " + (englishVersion != null ? englishVersion : error.getDefaultMessage()));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason("Method Argument Not Valid");
        error.setErrorUrl(null);
        return handleExceptionInternal(
                ex, error, headers, HttpStatus.BAD_REQUEST, request);
    }
    /*
        MissingServletRequestParameterException : This exception is thrown when request missing parameter
    */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String errorString = ex.getParameterName() + " parameter is missing";
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason("Missing Servlet Request Parameter");
        error.setErrorUrl(null);

        return new ResponseEntity<Object>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /*
        ConstrainViolationException: This exception reports the result of constraint violations
    */
    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason("Constraint violation");
        error.setErrorUrl(null);

        return new ResponseEntity<Object>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /*
        MethodArgumentTypeMismatchException: This exception is thrown when method argument is not the expected type
     */
    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String errorString =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason(errorString);
        error.setErrorUrl(null);
        return new ResponseEntity<Object>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /*
     */
    @ExceptionHandler({ HttpMessageConversionException.class })
    public ResponseEntity<Object> handleHttpMessageConversionException(
            HttpMessageConversionException ex, WebRequest request) {
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason("Type definition error");
        error.setErrorUrl(null);
        return new ResponseEntity<Object>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    /*
         HttpRequestMethodNotSupportedException – which occurs when you send a requested with an unsupported HTTP method
    */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason(builder.toString());
        error.setErrorUrl(null);
        return new ResponseEntity<Object>(
                error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ TechnicalException.class })
    public ResponseEntity<Object> handleTechnicalException(
            TechnicalException e, WebRequest request) {
        LOG.error("Technical Exception", e);
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        error.setErrorReason( e.getMessage());
        error.setErrorUrl(null);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({FunctionalException.class })
    public ResponseEntity<Object> handleFunctionalException(
            FunctionalException e, WebRequest request) {
        LOG.error("Functional Exception", e);
        fges.easyfoodtruck.service.api.model.Error error =
                new fges.easyfoodtruck.service.api.model.Error();
        error.setErrorCode(HttpStatus.BAD_REQUEST.name());
        error.setErrorReason( e.getMessage());
        error.setErrorUrl(null);
        return new ResponseEntity(error, e.getStatus());
    }
}
