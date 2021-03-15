package mricorserviceandcoudtut.Tut_with_Spring_cloud.AppExceptions;

public class UserServiceException extends RuntimeException{
    private final long serialVersion = 11432556689898744L;
    public UserServiceException(String message) {
        super(message);
    }
}
