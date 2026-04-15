package be.unamur.info.b314.compiler.exception;

/**
 *
 * @author James Ortiz - james.ortizvega@unamur.be
 */
public class SymbolNotFoundException extends RuntimeException {

    public SymbolNotFoundException(String message) {
        super(message);
    }

    public SymbolNotFoundException(String message, Exception cause) {
        super(message, cause);
    }
    
}
