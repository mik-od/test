package ua.ncherkasov.testtask.exception;

public class OperationNotFoundException extends Exception{
    public OperationNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
