package org.achau.view;

public class InvalidOperationException extends Exception{
    public InvalidOperationException(){}
    public InvalidOperationException(String msg){
        super(msg);
    }
}
