package interfaces;

/**
 * The Processable interface defines a contract for processing lines of text.
 * Implementing classes should provide their own implementation of the process method.
 */
public abstract interface Processable {

    /**
     * Processes a line of text.
     * 
     * @param line the line of text to be processed
     */
    public abstract void process(String line);
}