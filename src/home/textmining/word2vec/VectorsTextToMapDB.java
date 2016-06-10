package home.textmining.word2vec;

import org.mapdb.DB;
import org.mapdb.DBMaker;

import java.io.*;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by hok on 6/10/16.
 */
public class VectorsTextToMapDB {
    private boolean ignoreFirstLine;

    public VectorsTextToMapDB(boolean ignoreFirstLine) {
        this.ignoreFirstLine = ignoreFirstLine;
    }

    public VectorsTextToMapDB() {
        this(true);
    }

    public Word2VecModel convertVectorsFromTextToMapDB(InputStream iStream, File hashMapFile) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
        DB db = DBMaker.newFileDB(hashMapFile).make();
        ConcurrentMap<String, double[]> vectorMap = db.createHashMap("map").make();

        if (ignoreFirstLine) {
            reader.readLine();
        }

        for (String line; (line=reader.readLine())!=null; ) {
            String[] tokens = line.split(" ");
            String word = tokens[0];
            System.out.println(word);
            double[] vector = new double[tokens.length-1];
            for (int i=0; i<vector.length; i++) {
                vector[i] = Double.parseDouble(tokens[i+1]);
            }
            vectorMap.put(word, vector);
        }

        db.close();
        return new Word2VecModel(vectorMap);
    }

}