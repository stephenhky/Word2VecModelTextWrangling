package home.textmining.word2vec;

import java.io.*;
import java.util.Arrays;

/**
 * Created by hok on 6/9/16.
 */
public class Word2VecConsole {

    public static void main(String[] args) throws IOException {
        double time0 = System.nanoTime();

        File inputFile = new File(args[0]);
//        VectorsTextToMapDB converter = new VectorsTextToMapDB();
//        Word2VecModel model = converter.convertVectorsFromTextToMapDB(new FileInputStream(inputFile),
//                new File(args[1]));
        Word2VecModel model = VectorsTextToMapDB.getMapDBWord2VecModel(new File(args[1]));

        double time1 = System.nanoTime();

        System.out.println((time1-time0)/1e+9+" sec");
        BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("word > ");
        String word = lineReader.readLine().trim();
        while (word.length()>0) {
            System.out.println(Arrays.asList(model.getWordVector(word)));
            System.out.print("word > ");
            word = lineReader.readLine().trim();
        }
    }
}
