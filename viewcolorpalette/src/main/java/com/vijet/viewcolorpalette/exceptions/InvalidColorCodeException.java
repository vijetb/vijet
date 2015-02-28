/**
 * @author Vijet Badigannavar(bvijet@gmail.com)
 * @modified 28-Feb-2015
 */
package com.vijet.viewcolorpalette.exceptions;

/**
 * Custom Exception that will be thrown if the value of the color is negative or above the range
 */
public class InvalidColorCodeException extends RuntimeException {

    public InvalidColorCodeException(){}

    public InvalidColorCodeException(String msg){
        super(msg);
    }

    public InvalidColorCodeException(Throwable throwable){
        super(throwable);
    }

    public InvalidColorCodeException(String msg, Throwable throwable){
        super(msg,throwable);
    }

}
