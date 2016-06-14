package home.textmining.word2vec.console;

import home.textmining.word2vec.VectorCalculator;
import home.textmining.word2vec.VectorsTextToMapDB;
import home.textmining.word2vec.Word2VecModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by hok on 6/14/16.
 */
public class SimilarityConsole {
    public static void main(String[] args) throws IOException {
        // read the model
        double time0 = System.nanoTime();
        Word2VecModel model = VectorsTextToMapDB.getMapDBWord2VecModel(new File(args[0]));
        double time1 = System.nanoTime();
        System.out.println((time1 - time0) / 1e+9 + " sec");

        BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("word1 > ");
        String word1 = lineReader.readLine().trim();
        System.out.print("word2 > ");
        String word2 = lineReader.readLine().trim();
        while (word1.length()>0 || word2.length()>0) {
            double[] vector1 = model.getWordVector(word1);
            double[] vector2 = model.getWordVector(word2);
            if ((vector1!=null) && (vector2!=null)) {
                double similarity = VectorCalculator.cosine(vector1, vector2);
                System.out.println("Similarity = "+similarity);
            }
            System.out.print("word1 > ");
            word1 = lineReader.readLine().trim();
            System.out.print("word2 > ");
            word2 = lineReader.readLine().trim();
        }

    }
}
