package ua.exception;
public class DataSerializationException extends RuntimeException{
    public DataSerializationException(String message){
        super(message);
    }
    public DataSerializationException(String message, Throwable cause){
        super(message, cause);
    }
}
