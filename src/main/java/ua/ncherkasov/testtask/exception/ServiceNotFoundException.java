package ua.ncherkasov.testtask.exception;

public class ServiceNotFoundException extends Exception{
    public ServiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
