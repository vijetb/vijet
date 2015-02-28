package com.vijet.viewcolorpalette.exceptions;

/**
 * Created by BMH1014669 on 27/02/15.
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
