package be.unamur.info.b314.compiler.exception;

/*
EMJErrorException extending an Exception, to warn when error(s) occured during the tree visit
@author : Alix Decrop
@version : 1.0
*/
public class EMJErrorException extends Exception {

    public EMJErrorException(String errors) {
        super("Error(s) were caught during EMJ tree visit. Stacktrace:\n" + errors);
    }
}