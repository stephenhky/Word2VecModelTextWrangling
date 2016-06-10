package home.textmining.word2vec;

/**
 * Created by hok on 6/9/16.
 */
public class VectorCalculator {
    public static double norm(double[] vector) {
        double sqsum = 0.0;
        for (int i=0; i<vector.length; i++) {
            sqsum += vector[i]*vector[i];
        }
        return Math.sqrt(sqsum);
    }

    public static double dotprod(double[] vector1, double[] vector2) {
        double sumdotprod = 0.0;
        int dim = Math.min(vector1.length, vector2.length);

        for (int i=0; i<dim; i++) {
            sumdotprod += vector1[i]*vector2[i];
        }

        return sumdotprod;
    }

    public static double cosine(double[] vector1, double[] vector2) {
        return dotprod(vector1, vector2) / (norm(vector1)*norm(vector2));
    }

    public static double[] add(double[] vector1, double[] vector2) {
        int dim = Math.min(vector1.length, vector2.length);
        double[] sum = new double[dim];

        for (int i=0; i<dim; i++) {
            sum[i] = vector1[i] + vector2[i];
        }

        return sum;
    }

    public static double[] subtract(double[] vector1, double[] vector2) {
        double[] neg_vec2 = new double[vector2.length];
        for (int i=0; i<vector2.length; i++) {
            neg_vec2[i] = - vector2[i];
        }
        return add(vector1, neg_vec2);
    }
}
