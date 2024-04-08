package org.achau.view.exceptions;

/**
 * Exception that occurs when user inputs a invalid operation for a given service
 * @author Andrew Chau
 * @version 1.0
 */
public class InvalidOperationException extends Exception{
    public InvalidOperationException(){}
    public InvalidOperationException(String msg){
        super(msg);
    }
}
