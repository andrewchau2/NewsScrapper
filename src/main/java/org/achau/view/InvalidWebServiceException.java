package org.achau.view;

public class InvalidWebServiceException extends Exception{
    public InvalidWebServiceException(){}

    public InvalidWebServiceException(String msg){
        super(msg);
    }
}
