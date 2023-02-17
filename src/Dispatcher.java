import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class Dispatcher {
    /*
    static private double[][] x1 = {{1,-1,-1,
                                     1,-1,-1,
                                     1, 1, 1},
                                    {1, 1, 1,
                                     1,-1, 1,
                                     1, 1, 1},
                                    {1,-1, 1,
                                     1, 1, 1,
                                     1,-1, 1}};

     */
    static private double[][] x1 = {{1, 1,1,
                                     1,-1,1,
                                     1,-1,1},
                                    {1, 1, 1,
                                     1,-1,-1,
                                     1,-1,-1},
                                    {1,-1,-1,
                                     1,-1,-1,
                                     1, 1, 1}};
    static double[] x2 = {1,1,1,
                          1,-1,1,
                          1,1,1};

     /* static private double[] x2 = {1, 1, 1,
                               1,-1, 1,
                               1, 1, 1};
    static private double[] x3 = {1,-1, 1,
                               1, 1, 1,
                               1,-1, 1};
    */
    static private double[][] W;
    public static void main (String[] args){

        training();

        //int index = 0;
        for (int i = 0; i < getX1().length; i++){
            for (int j = 0; j < getX1()[i].length; j++){
                for (int k = 0; k < getX1()[i].length; k++){
                    getW()[j][k] += (getX1()[i][j]*getX1()[i][k]);
                    //index++;
                }
               // index = 0;
            }
        }
        for (int i = 0; i < getW().length; i++){
            System.out.println(Arrays.toString(getW()[i]));
        }

        float n = (float) 1/(getX1()[0].length);
        for (int i = 0; i < getW().length; i++){
            for (int j = 0; j < getW()[0].length; j++){
                getW()[i][j] *= n;
            }
        }
        diagonal();
        System.out.println("///////////////////////////////////////////////////////");
        for (int i = 0; i < getW().length; i++){
            System.out.println(Arrays.toString(getW()[i]));
        }
        System.out.println(analyze(x2));


    }

    static public double[][] getX1() {
        return x1;
    }
/*
    static public double[] getX2() {
        return x2;
    }

    static public double[] getX3() {
        return x3;
    }

    public static ArrayList getW() {
        return W;
    }
*/
    public static double[][] getW() {
        return W;
    }
    public static void diagonal(){
        for (int i = 0; i < getW().length; i++){
            getW()[i][i] = 0;
        }
    }
    public static int analyze (double[] x2){
        int coincidences = 0;
        double[] y = new double[x2.length];
        for (int i = 0; i < getW().length; i++){
            for (int j = 0; j < x2.length; j++){
                y[i] += x2[j]*getW()[i][j];
            }
        }
        for (int i = 0; i < y.length; i++){
            y[i] = y[i] > 0 ? 1 : -1;
            /*
            if (y[i]>0) y[i] = 1;
            else y[i] = -1;
             */
        }
        for (int i = 0; i < getW().length; i++){
           for (int j = 0; j < y.length; j++){
               if (y[j] == getW()[i][j]){
                   coincidences++;
               }
           }
           if (coincidences == getW()[i].length){
               return i;
           } else coincidences = 0;
        }
        System.out.println("YYYYYY  "+Arrays.toString(y));
        return -1;
    }
    public static void setW(double[] w) {}
    public static void training(){
        W = new double[getX1()[0].length][];
        for (int i = 0; i < getX1()[0].length; i++){
            W[i] = new double[getX1()[0].length];
        }
    }
}

