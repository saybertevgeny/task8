package ru.lanit.exception;

public class MappingException extends Exception {

    private String message;

    public MappingException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString(){
        return message;
    }
}
