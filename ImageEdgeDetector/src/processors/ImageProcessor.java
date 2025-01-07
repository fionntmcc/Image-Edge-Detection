package processors;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The EmbeddingsProcessor class processes a file containing word embeddings and stores them in a map.
 * It provides methods to find the closest word based on cosine similarity.
 */
public class ImageProcessor extends AbstractProcessor {

    private Map<String, double[]> map = new ConcurrentHashMap<>();

    /**
     * Processes a single line from the file and stores the embedding.
     * 
     * @param line a line from the file containing a word and its embedding
     */
    @Override
    public void process(String line) {
        if (line == null) return;

        var values = line.split(", ");

        if (values.length <= 2) return;

        var word = values[0];

        double[] vec = new double[values.length - 1];
        for (int i = 1; i < values.length; i++) {
            if (isNumeric(values[i])) vec[i - 1] = Double.parseDouble(values[i]);
        }
        this.map.put(word, vec);
    }

    /**
     * Returns an unmodifiable view of the embeddings map.
     * 
     * @return a map where the key is a word and the value is its corresponding embedding
     */
    public Map<String, double[]> map() {
        return Map.copyOf(this.map);
    }

    /**
     * Checks if a string is numeric.
     * 
     * @param strNum the string to check
     * @return true if the string is numeric, false otherwise
     */
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Clears the embeddings map.
     */
    @Override
    public void clear() {
        map = new ConcurrentHashMap<>();
    }
}