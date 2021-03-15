package mricorserviceandcoudtut.Tut_with_Spring_cloud.AppExceptions;

import mricorserviceandcoudtut.Tut_with_Spring_cloud.Response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandller extends ResponseEntityExceptionHandler {


    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request){
        String errorDescription = ex.getLocalizedMessage();
        if(errorDescription == null) errorDescription = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(),
                errorDescription);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value={NullPointerException.class, UserServiceException.class})
    public ResponseEntity<Object> handleSpecificException(Exception ex, WebRequest request){
        String errorDescription = ex.getLocalizedMessage();
        if(errorDescription == null) errorDescription = ex.toString();
        ErrorMessage errorMessage = new ErrorMessage(new Date(),
                errorDescription);
        return new ResponseEntity<>(
                errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
