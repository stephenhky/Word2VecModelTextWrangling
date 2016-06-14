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

        int j = 0;
        for (String line; (line=reader.readLine())!=null; ) {
            String[] tokens = line.split(" ");
            String word = tokens[0];
            System.out.println((j++)+" : "+word);
            double[] vector = new double[tokens.length-1];
            for (int i=0; i<vector.length; i++) {
                vector[i] = Double.parseDouble(tokens[i+1]);
            }
            vectorMap.put(word, vector);
        }
        db.commit();
        return new Word2VecModel(vectorMap);
    }

    public static Word2VecModel getMapDBWord2VecModel(File hashMapFile) {
        DB db = DBMaker.newFileDB(hashMapFile).make();
        ConcurrentMap<String, double[]> vectorMap = db.getHashMap("map");

        return new Word2VecModel(vectorMap);
    }

    public static void main(String[] args) throws IOException {
        VectorsTextToMapDB converter = new VectorsTextToMapDB();

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        System.out.println("Starting creating mapDB for model....");

        double time0 = System.nanoTime();
        converter.convertVectorsFromTextToMapDB(new FileInputStream(new File(inputFilePath)), new File(outputFilePath));
        double time1 = System.nanoTime();

        System.out.println("Finished. "+(time1-time0)/1e+9+" sec");
    }
}
