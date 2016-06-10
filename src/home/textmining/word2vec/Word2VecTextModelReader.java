package home.textmining.word2vec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hok on 6/9/16.
 */
public class Word2VecTextModelReader {
    private boolean ignoreFirstLine;
    private InputStream iStream;

    public Word2VecTextModelReader(InputStream iStream, boolean ignoreFirstLine) {
        this.iStream = iStream;
        this.ignoreFirstLine = ignoreFirstLine;
    }

    public Word2VecTextModelReader(InputStream iStream) {
        this(iStream, true);
    }

    public Word2VecModel readModel() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        Word2VecModel model = new Word2VecModel();

        if (ignoreFirstLine) {
            reader.readLine();
        }

        for (String line; (line=reader.readLine())!=null; ) {
            String[] tokens = line.split(" ");
            String word = tokens[0];
            double[] vector = new double[tokens.length-1];
            for (int i=0; i<vector.length; i++) {
                vector[i] = Double.parseDouble(tokens[i+1]);
            }
            model.setWordVector(word, vector);
        }

        return model;
    }

}
