package be.unamur.info.b314.compiler.table_symbole;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SymbolTable {

    private List<Variable> variables;
    private Map<String, String> functionReturnTypes;
    private Map<String, List<String>> functionParams; 
    private Map<String, List<String>> functionidentif;


    public SymbolTable() {
        variables = new ArrayList<>();
        functionReturnTypes = new HashMap<>();
        functionParams = new HashMap<>();
        functionidentif = new HashMap<>();
    }

    
    public List<Variable> getVariables() {
        return variables;
    }
    
    public void registerVariable(String name, String type, String scope, String value) {
    if (!variableExists(name, scope)) {
        Variable variable = new Variable(name, type, scope,value); // Enregistrez la valeur de la variable
        variables.add(variable);
    } else {
        System.out.println("Variable " + name + " already exists in scope " + scope);
    }
}

    public boolean variableExists(String name, String scope) {
        for (Variable variable : variables) {
            if (variable.getName().equals(name) && variable.getScope().equals(scope)) {
                return true;
            }
        }
        return false;
    }

    public boolean variableExists(String name) {
        for (Variable variable : variables) {
            if (variable.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }




    public void registerFunction(String functionName, String returnType, List<String> params, List<String> identif) {
        functionReturnTypes.put(functionName, returnType);
        functionParams.put(functionName, params);
        functionidentif.put(functionName, identif);
    }


    

    public String getVariableType(String name, String scope) {
        for (Variable variable : variables) {
            if (variable.getName().equals(name)) {
                return variable.getType();
            }
        }
        return null; // Variable not found
    }


    //reecuperer la valeur d'une variable : 
    public String getVariableValue(String name) {
        for (Variable variable : variables) {
            if (variable.getName().equals(name)) {
                return variable.getValue(); // Retourne la valeur de la variable si elle est trouvée
            }
        }
        return null; // Retourne null si la variable n'est pas trouvée
    }

    public String getVariableValue(String name, String scope){
        for (Variable variable : variables) {
            if (variable.getName().equals(name) && variable.getScope().equals(scope)) {
                return variable.getValue(); // Retourne la valeur de la variable si elle est trouvée
            }
        }
        return "notFound"; // Retourne null si la variable n'est pas trouvée
    }

    public String getVariableScope(String name){
        for (Variable variable : variables) {
            if (variable.getName().equals(name)) {
                return variable.getScope(); // Retourne la valeur de la variable si elle est trouvée
            }
        }

        return null;
    }


    
    public String getFunctionReturnType(String functionName) {
        return functionReturnTypes.get(functionName); // Retourne le type de retour de la fonction ou null si la fonction n'existe pas
    }

    public List<String> getFunctionParamTypes(String functionName) {
        return functionParams.get(functionName);
    }

    public List<String> getFunctionidentif(String functionName) {
        return functionidentif.get(functionName);
    }


    public String getFuncParamType(String functionName, int i) {
        // Vérifie si la fonction existe dans functionParams
        if (functionParams.containsKey(functionName)) {
            List<String> params = functionParams.get(functionName);
            // Vérifie si l'index spécifié est valide
            if (i >= 0 && i < params.size()) {
                // Retourne le type du paramètre à l'index spécifié
                return params.get(i);
            } else {
                // Gère le cas où l'index est invalide
                return "Index de paramètre invalide";
            }
        } else {
            // Gère le cas où la fonction n'existe pas
            return "Fonction non trouvée";
        }
    }
    

    public String getFuncidentif(String functionName, int i) {
        // Vérifie si la fonction existe dans functionParams
        if (functionidentif.containsKey(functionName)) {
            List<String> identif = functionidentif.get(functionName);
            // Vérifie si l'index spécifié est valide
            if (i >= 0 && i < identif.size()) {
                // Retourne le type du paramètre à l'index spécifié
                return identif.get(i);
            } else {
                // Gère le cas où l'index est invalide
                return "Index de paramètre invalide";
            }
        } else {
            // Gère le cas où la fonction n'existe pas
            return "Fonction non trouvée";
        }
    }

    // Check if a function identifier already exists
    public boolean functionExists(String functionName) {
        return functionReturnTypes.containsKey(functionName);
    }

    

 

    
}
