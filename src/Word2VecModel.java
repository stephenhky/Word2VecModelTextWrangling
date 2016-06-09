import java.util.HashMap;
import java.util.Map;

/**
 * Created by hok on 6/9/16.
 */
public class Word2VecModel {
    private Map<String, double[]> modelHashMap;

    public Word2VecModel() {
        modelHashMap = new HashMap<String, double[]>();
    }

    public Word2VecModel(Map<String, double[]> modelHashMap) {
        this.modelHashMap = modelHashMap;
    }

    public void setWordVector(String word, double[] vector) {
        modelHashMap.put(word, vector);
    }

    public double[] getWordVector(String word) {
        return modelHashMap.get(word);
    }

}
