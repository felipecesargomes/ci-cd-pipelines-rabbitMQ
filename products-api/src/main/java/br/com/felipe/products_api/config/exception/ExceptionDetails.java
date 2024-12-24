package br.com.felipe.products_api.config.exception;

public class ExceptionDetails {
    private int status;
    private String message;

    //Constructor empty
    public ExceptionDetails() {
    }

    //Constructor All arguments
    public ExceptionDetails(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

}