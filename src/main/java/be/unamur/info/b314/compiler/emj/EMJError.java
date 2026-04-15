package be.unamur.info.b314.compiler.emj;

/*
EMJError class to define an EMJ error
@author : Alix Decrop
@version : 1.0
*/
public class EMJError {

    private final String name;
    private final String location;
    private final int line;

    public EMJError(String name, String location, int line) {
        this.name = name;
        this.location = location;
        this.line = line;
    }

    public String getErrorString() {
        return "[EMJ ERROR] " + this.name + " for " + this.location + " at line " + this.line;
    }
}