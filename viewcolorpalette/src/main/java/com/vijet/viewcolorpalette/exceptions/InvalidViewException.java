/**
 * @author Vijet Badigannavar(bvijet@gmail.com)
 * @modified 28-Feb-2015
 */
package com.vijet.viewcolorpalette.exceptions;

/**
 * Custom Exception that will be thrown if View is null.
 */
public class InvalidViewException extends RuntimeException {
    public InvalidViewException(){}

    public InvalidViewException(String msg){
        super(msg);
    }

    public InvalidViewException(Throwable throwable){
        super(throwable);
    }

    public InvalidViewException(String msg, Throwable throwable){
        super(msg,throwable);
    }
}
