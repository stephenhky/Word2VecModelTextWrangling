package home.textmining.word2vec;

import java.util.HashMap;
import java.util.Iterator;
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

    public Map<String, double[]> getModelHashMap() { return modelHashMap; }

    public Iterator<String> getSOIterator() {
        return modelHashMap.keySet().iterator();
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Number of words = "); sb.append(this.modelHashMap.size());
        Iterator<String> stringIterator = this.modelHashMap.keySet().iterator();
        String word = stringIterator.next();
        sb.append("Dimension = ");  sb.append(this.modelHashMap.get(word).length);
        return sb.toString();
    }
}
