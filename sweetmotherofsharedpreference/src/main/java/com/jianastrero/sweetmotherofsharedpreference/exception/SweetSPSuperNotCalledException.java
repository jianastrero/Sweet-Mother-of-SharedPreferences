package com.jianastrero.sweetmotherofsharedpreference.exception;

/**
 * Created by Jian Astrero on 1/28/2017.
 */
public class SweetSPSuperNotCalledException extends RuntimeException {
    @Override
    public String getMessage() {
        return "SweetSP subclasses should call super(identifier)! please call super() on this subclass";
    }
}
