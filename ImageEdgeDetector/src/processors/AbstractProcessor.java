package processors;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.StructuredTaskScope;
import interfaces.Processable;

/**
 * An abstract class that provides a template for processing lines of text from a file.
 * Implementing classes should provide their own implementation of the process and clear methods.
 */
public abstract class AbstractProcessor implements Processable {

    /**
     * Reads a file and processes each line using the process method.
     * Clears any existing data before processing the file.
     * Uses virtual threads for concurrent processing.
     * 
     * @param fileName the name of the file to be processed
     * @throws IOException 
     * @throws InterruptedException 
     */
    public void store(String fileName) throws IOException, InterruptedException {
    	clear(); // Empty data structure

        var file = new File(fileName);

        try {
        	try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
    			Files.lines(file.toPath()).forEach(line -> { // For each line
    				scope.fork(() -> { // Fork new virtual thread
    					try {
    						process(line);
    					} catch (Exception e) {
    						System.err.println(e);
    					}
    					// StructuredTaskScope uses Callable<T>
    					// Therefore, return type needed.
    					return null; 
    				});
    			});
    			scope.join();
    			scope.throwIfFailed();  
            }
		} catch (Exception e) {
			Thread.currentThread().interrupt();
			System.err.println("Processing error in " + this.getClass() + ": " + e);
		}
    }

    /**
     * Processes a line of text.
     * Implementing classes should provide their own implementation of this method.
     * 
     * @param line the line of text to be processed
     */
    public abstract void process(String line);

    /**
     * Clears any existing data.
     * Implementing classes should provide their own implementation of this method.
     */
    public abstract void clear();
}