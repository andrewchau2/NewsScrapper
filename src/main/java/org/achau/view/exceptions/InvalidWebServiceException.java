package org.achau.view.exceptions;

/**
 * Exception that occurs when a user input an invalid service to web-scrap
 * @author Andrew Chau
 * @version 1.0
 */
public class InvalidWebServiceException extends Exception{
    public InvalidWebServiceException(){}

    public InvalidWebServiceException(String msg){
        super(msg);
    }
}
