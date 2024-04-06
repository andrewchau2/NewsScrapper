package org.achau.view.exceptions;

public class InvalidOperationException extends Exception{
    public InvalidOperationException(){}
    public InvalidOperationException(String msg){
        super(msg);
    }
}
