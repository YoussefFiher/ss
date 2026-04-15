package be.unamur.info.b314.compiler.main;

// Grammar
import be.unamur.info.b314.compiler.EMJLexer;
import be.unamur.info.b314.compiler.EMJParser;
// Visitor
import be.unamur.info.b314.compiler.emj.EMJVisitor;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.*;

import be.unamur.info.b314.compiler.exception.EMJErrorException;
import be.unamur.info.b314.compiler.emj.EMJErrorLogger;
import be.unamur.info.b314.compiler.emj.EMJMapPrinter;
import be.unamur.info.b314.compiler.emj.EMJPrinter;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RecognitionException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("deprecation")

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);
    private static final String NAME = "EMJ-compiler";
    private static final String HELP = "h";
    private static final String INPUT = "i";
    private static final String OUTPUT = "o";

    /**
     * Main method launched when starting compiler jar file.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        Main main = new Main();
        CommandLineParser parser = new DefaultParser();
        CommandLine line = null;

        try {
            line = parser.parse(main.options, args);
        }
        catch (ParseException ex) {
            LOG.error("Error while parsing command line!", ex);
            main.printHelpMessage();
        }

        // If help is requested, print help message and exit.
        if (line != null) {
            if (line.hasOption(HELP)) {
                main.printHelpMessage();
            }
            else {
                // Else start compilation
                try {
                    main.initialise(line);
                    main.compile(); // Call compile method (to be completed)
                    System.err.println("OK"); // Print OK on stderr
                }
                catch (Exception e) {
                    LOG.error("Exception occurred during compilation!", e);
                    System.err.println("KO"); // Print KO on stderr if a problem occurred
                }
            }
        }
    }

    /**
     * The command line options.
     */
    private final Options options;

    /**
     * The input B314 file.
     */
    private File inputFile;

    /**
     * The output PCode file.
     */
    private File outputFile;

    private EMJParser parser;

    private Main() {
        // Create command line options
        options = new Options();

        options.addOption(Option.builder(HELP)
                .desc("Prints this help message.")
                .build());

        options.addOption(Option.builder(INPUT)
                .desc("The B314 input file.")
                .hasArg(true)
                .build());

        options.addOption(Option.builder(OUTPUT)
                .desc("The PCOde output file.")
                .hasArg(true)
                .build());
    }

    /**
     * Prints help message with these options.
     */
    private void printHelpMessage() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(128);
        formatter.printHelp(String.format("java -jar %s.jar -%s | %s %s",
                NAME, HELP, INPUT, OUTPUT), options);
    }

    /**
     * Initialise the input compiler using the given input line.
     *
     * @throws Exception If one of the three required arguments is not provided.
     */
    private void initialise(CommandLine line) throws Exception {
        LOG.debug("Initialisation");

        // Check that the arguments are there
        if (!line.hasOption(INPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", INPUT));
        }

        else if (!line.hasOption(OUTPUT)) {
            throw new ParseException(String.format("Option %s is mandatory!", OUTPUT));
        }

        // Get given files and check they exist
        inputFile = new File(line.getOptionValue(INPUT));
        checkArgument(inputFile.exists() && inputFile.isFile(), "File %s not found!", inputFile.getName());
        LOG.debug("Input file set to {}", inputFile.getPath());

        outputFile = new File(line.getOptionValue(OUTPUT));

        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        checkArgument(outputFile.exists() && outputFile.isFile(), "File %s not created!", outputFile.getName());

        LOG.debug("Output file set to {}", outputFile.getPath());
        LOG.debug("Initialisation: done");
    }


    /**
     * Compiler Methods, this is where the MAGIC happens !!! \o/
     */
    private void compile() throws EMJErrorException, IOException {
        try {
            // Get abstract syntax tree
            LOG.debug("Parsing input");

            // Create ANTLR lexer and parser, based on .g4 grammar
            EMJLexer lexer = new EMJLexer(CharStreams.fromPath(inputFile.getAbsoluteFile().toPath()));
            EMJParser.RootContext tree;
            try {
                EMJParser parser = new EMJParser(new CommonTokenStream(lexer));
                // Set error listener to implementation
                parser.removeErrorListeners();
                MyConsoleErrorListener errorListener = new MyConsoleErrorListener();
                parser.addErrorListener(errorListener);
                try {
                    tree = parser.root();
                }
                catch (RecognitionException e) {
                    throw new IllegalArgumentException("Error while retrieving parsing tree!", e);
                }
                if (errorListener.errorHasBeenReported()) {
                    throw new IllegalArgumentException("Error while parsing input!");
                }
            }
            catch(Exception e) {
                LOG.error("error while parsing");
                throw new IllegalArgumentException("Error while parsing", e);
            }

            LOG.debug("Parsing input: done");
            LOG.debug("AST is {}", tree.toStringTree(parser));

            // LEAVE LINE COMMENTED UNTIL SEMANTIC TESTS ARE AVAILABLE
            // YOU CAN UNCOMMENT TO TEST WITH YOUR OWN SEMANTIC TESTS
            this.visitTree(tree);

        }
        catch(Exception e) {
            throw e;
        }
    }

    private void visitTree(EMJParser.RootContext tree) throws EMJErrorException, FileNotFoundException {
        // Visit tree

        EMJPrinter printer = new EMJPrinter("Output.py");
        EMJMapPrinter mapPrinter = new EMJMapPrinter("map.py");
        EMJVisitor visitor = new EMJVisitor(printer, mapPrinter);
        LOG.debug("Visiting");
        visitor.visit(tree);
        LOG.debug("Visiting: done");

        // If a error occured during the tree visit, throw it
        EMJErrorLogger errorLogger = visitor.getErrorLogger();
        if(errorLogger.containsErrors()) {
            System.out.println(errorLogger.getErrorsString());
            throw new EMJErrorException(errorLogger.getErrorsString());
        }
        
    }
    

}