package be.unamur.info.b314.compiler.emj;

import be.unamur.info.b314.compiler.EMJLexer;
import be.unamur.info.b314.compiler.EMJParser;
import be.unamur.info.b314.compiler.EMJParserBaseVisitor;
import be.unamur.info.b314.compiler.EMJParser.FuncparamsnotemptyContext;
import be.unamur.info.b314.compiler.emj.EMJError;
import be.unamur.info.b314.compiler.emj.EMJErrorLogger;
import be.unamur.info.b314.compiler.table_symbole.Variable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EMJPrinter {

    private PrintWriter writer;
    private Map<String, String> emojiVarMap;
    private Map<String, String> emojiFuncMap;
    private int varCounter;
    private int funcCounter;
    private int numberOfFunctions;
    private int indentLevel;
    private boolean isSoundOn = false; // Variable pour suivre l'état du son
    private boolean areLightsOn = false; // Variable pour suivre l'état des lumières

    public EMJPrinter(String fileName) throws FileNotFoundException {
        writer = new PrintWriter(fileName);
        emojiVarMap = new HashMap<>();
        emojiFuncMap = new HashMap<>();
        varCounter = 1;
        funcCounter = 1;
        indentLevel = 0;
    }

    

    public void setNumberOfFunctions(int numberOfFunctions) {
        this.numberOfFunctions = numberOfFunctions;
    }

    public int getNumberOffunction() {
        return this.numberOfFunctions;
    }

    public void increaseIndent() {
        indentLevel++;
    }

    public void decreaseIndent() {
        if (indentLevel > 0) {
            indentLevel--;
        }
    }

    public void changeIndent(int i){
        this.indentLevel = i;
    }

    private String repeat(String str, int times) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < times; i++) {
            result.append(str);
        }
        return result.toString();
    }
    
    private String getIndent() {
        return repeat("    ", indentLevel);  // 4 espaces par niveau d'indentation
    }

    /* -------------------------------------------------------------- */
    public void printVarDecl(String varId, EMJParser.RightexpContext rightExp) {

        String varName = getOrCreateVarName(varId);

        if(rightExp == null) {
            writer.printf("%s%s = %s", getIndent(), varName, "None").println();
        } else if (rightExp.callfunc() != null){
            String funcName = getOrCreateFuncName(rightExp.callfunc().identif().getText());
            writer.printf("%s%s = %s", getIndent(), varName, replaceEmojiWithNames(rightExp.getText())).println();
        } else {
            writer.printf("%s%s = %s", getIndent(), varName, replaceEmojiWithNames(rightExp.getText())).println();
        }


        writer.flush();
    }
    
    public void printIfExp(String boolExp) {
        writer.printf("%sif (%s):", getIndent(), replaceEmojiWithNames(boolExp)).println();
        
    }

    public void printIfElse() {
        writer.printf("%selse:%n", getIndent());
        

    }

    public void printWhileExp(String boolExp) {
        writer.println(); 
        writer.printf("%swhile (%s):%n", getIndent(), replaceEmojiWithNames(boolExp));
        
    }

    public void printForExp(String l) {
        writer.println();
        writer.printf("%sfor i in range(%s):%n", getIndent(), replaceEmojiWithNames(l));
        
    }

    public void printAffectInstr(String varId, String value) {
        String varName = getOrCreateVarName(varId);
        writer.printf("%s%s = %s%n", getIndent(), varName, replaceEmojiWithNames(value));
        writer.flush();
    }

    public void printImportation(String idCart) {
        writer.printf("import %s", idCart).println();
        writer.println();
        writer.flush();
    }

    public void printreturnExp(String rightexp) {
        writer.printf("%sreturn %s%n", getIndent(), replaceEmojiWithNames(rightexp)).println();
        writer.flush();
        

        if(getNumberOffunction() == 0) {
            writer.println();
            writer.printf("main()").println();
            writer.flush();
            writer.close();
        }
        
    }

    public void printFuncDecl(String funcName, List<String> parameters) {
        String translatedFuncName = getOrCreateFuncName(funcName);

        System.out.println(" ++ " + funcName + " ++ " + translatedFuncName);
        writer.println();
        writer.printf("%sdef %s(", getIndent(), translatedFuncName);
        for (int i = 0; i < parameters.size(); i++) {
            if (i == parameters.size() - 1) {
                writer.printf("%s", getOrCreateVarName(parameters.get(i)));
            } else {
                writer.printf("%s, ", getOrCreateVarName(parameters.get(i)));
            }
        }
        writer.printf("):%n");
        writer.flush();
        increaseIndent();
        numberOfFunctions--;
    }

    public void printCallFunc(String funcName, String args, boolean isInstruction) {
        if (isInstruction){
            String translatedFuncName = getOrCreateFuncName(funcName);
            writer.printf("%s%s(%s)", getIndent(), translatedFuncName, replaceEmojiWithNames(args)).println();
            writer.flush();
        }
    }

    public void printcallmain(){
        writer.printf("def main():").println();
        increaseIndent();
    }

    public void printEndMain() {
        
        if(getNumberOffunction() == 0) {
            writer.println();
            writer.printf("main()").println();
            writer.flush();
            writer.close();
        }
        changeIndent(0);
    }


    /* -------------------- PREFUNCS ------------------------ */
    public void printUpFunc(String nbCase){
        writer.printf("%s## UP #############", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.FORWARD, 50, %s)", getIndent(), nbCase).println();
        
        writer.flush();

    }

    public void printDownFunc(String nbCase){
        writer.printf("%s## DOWN ############", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.BACKWARD, 50, %s)", getIndent(), nbCase).println();
        
        writer.flush();

    }

    public void printLeftFunc(String nbCase){
        writer.printf("%s## LEFT ############", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.LEFT, 50, 0.2)", getIndent()).println();
        writer.printf("%sbasic.pause(200)", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.FORWARD, 50, %s)", getIndent(), nbCase).println();
        
        writer.flush();
    }

    public void printRightFunc(String nbCase){
        writer.printf("%s## RIGHT ############", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.RIGHT, 50, 0.2)", getIndent()).println();
        writer.printf("%sbasic.pause(200)", getIndent()).println();
        writer.printf("%scuteBot.move_time(cuteBot.Direction.FORWARD, 50, %s)", getIndent(), nbCase).println();
        
        writer.flush();
    }
    
    public void printLightsFunc() {
        if (!areLightsOn) {
            writer.printf("%s## LIGHTS ON ############", getIndent()).println();
            writer.printf("%scuteBot.color_light(cuteBot.RGBLights.RGB_R, 0xff0000)", getIndent()).println();
            writer.printf("%scuteBot.color_light(cuteBot.RGBLights.RGB_L, 0x0000ff)", getIndent()).println();
            areLightsOn = true;
        } else {
            writer.printf("%s## LIGHTS OFF ############", getIndent()).println();
            writer.printf("%scuteBot.closeheadlights()", getIndent()).println();
            areLightsOn = false;
        }
    }

    public void printSoundFunc() {
        if (!isSoundOn) {
            writer.printf("%s## SOUND ON ############%n", getIndent());
            writer.printf("%smusic._play_default_background(music.built_in_playable_melody(Melodies.JUMP_UP), music.PlaybackMode.LOOPING_IN_BACKGROUND)", getIndent()).println();
            isSoundOn = true;
        } else {
            writer.printf("%s## SOUND OFF ############%n", getIndent());
            writer.printf("%smusic.stop_all_sounds()", getIndent()).println();
            isSoundOn = false;
        }
    }
    

    /* ------------------FONCTIONS UTILES--------------------- */
    private String getOrCreateVarName(String emojiVar) {
        if (!emojiVarMap.containsKey(emojiVar)) {
            String varName = "var_" + varCounter++;
            emojiVarMap.put(emojiVar, varName);
        }
        return emojiVarMap.get(emojiVar);
    }

    private String getOrCreateFuncName(String emojiFunc) {
        if (!emojiFuncMap.containsKey(emojiFunc)) {
            String funcName = "func_" + funcCounter++;
            emojiFuncMap.put(emojiFunc, funcName);
        }
        return emojiFuncMap.get(emojiFunc);
    }

    private String replaceEmojiWithNames(String str) {
        if (str == null) {
            throw new IllegalArgumentException("L'argument ne doit pas être null");
        }
        // Replace logical operators and literals first
        str = str.replace("✅", "True")
                 .replace("❌", "False")
                 .replace("&&", " and ")
                 .replace("||", " or ")
                 .replace("1️","[1]")
                 .replace("0️" , "[0]");

        // Remplacer "!" uniquement s'il n'est pas suivi par "="
        str = str.replaceAll("!(?!=)", "not ");

        // Replace variable and function names
        for (Map.Entry<String, String> entry : emojiVarMap.entrySet()) {
            str = str.replace(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, String> entry : emojiFuncMap.entrySet()) {
            str = str.replace(entry.getKey(), entry.getValue());
        }
        return str;
    }


}






