package be.unamur.info.b314.compiler.emj;

import be.unamur.info.b314.compiler.emj.EMJError;
import java.util.ArrayList;

/*
Error logger for EMJ ; Will store errors when visiting a tree
@author : Alix Decrop
@version : 1.0
*/
public class EMJErrorLogger {

    private ArrayList<EMJError> errors;
    private boolean hasErrors;

    public EMJErrorLogger() {
        this.errors = new ArrayList<EMJError>();
        this.hasErrors = false;
    }

    public ArrayList<EMJError> getErrors() {
        return this.errors;
    }

    public void addError(EMJError error) {
        this.errors.add(error);
        this.hasErrors = true;
    }

    public boolean containsErrors() {
        return this.hasErrors;
    }

    public String getErrorsString() {
        String errorsString = "";

        for(EMJError error : this.errors) {
            errorsString = errorsString + error.getErrorString() + "\n";
        }

        return errorsString;
    }
}