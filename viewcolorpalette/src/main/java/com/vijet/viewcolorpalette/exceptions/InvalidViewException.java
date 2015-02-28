package com.vijet.viewcolorpalette.exceptions;

/**
 * Created by BMH1014669 on 27/02/15.
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
