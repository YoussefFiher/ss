package be.unamur.info.b314.compiler.table_symbole;

public class Variable {
    private String name;
    private String type;
    private String scope;
    private String value;

    public Variable(String name, String type, String scope,String value) {
        this.name = name;
        this.type = type;
        this.scope = scope;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getScope() {
        return scope;
    }

    public String getValue(){
        return value;

    }

    public void setValue(String value){
        this.value = value;
    }


    @Override
    public int hashCode() {
        return 31 * name.hashCode() + scope.hashCode();
    }
}
