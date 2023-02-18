import java.util.Arrays;

public class Dispatcher {

    static private double[][] x1 = {{1,-1,-1,
                                     1,-1,-1,
                                     1, 1, 1},
                                    {1, 1, 1,
                                     1,-1, 1,
                                     1, 1, 1},
                                    {1,-1, 1,
                                     1, 1, 1,
                                     1,-1, 1}};

    /*
    static private double[][] x1 = {{1, 1,1,

                                     1,-1,1,
                                     1, -1,1},
                                    {1, 1, 1,
                                     1,-1,-1,
                                     1,-1,-1},
                                    {1,-1,-1,
                                     1,-1,-1,
                                     1, 1, 1}};
        */
    static double[] x2 = {1, -1,1,
                          1,1,1,
                          1, -1,1};

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
/*
            double result;
            for ( int i = 0; i < getX1().length; i++) {
                for (int j = 0; j < getX1()[0].length; j++) {
                    System.out.println("");
                    System.out.println("getX1()[0][j] : " + getX1()[i][j]);
                    for (int k = 0; k < getX1()[0].length; k++) {
                        System.out.println("");
                        System.out.println("I: " + i);
                        System.out.print("XK " + k + " : " + getX1()[i][k] + ", ");
                        result = ((getX1()[i][j]) * (getX1()[i][k]));
                        System.out.println("");
                        System.out.print("R: " + result + ", ");
                        getW()[j][k] += (result);
                        System.out.println("");
                        System.out.println("WK: " + k + " : " + getW()[j][k]);


                        //getW()[j][k] = (getX1()[i][j] * getX1()[i][k]);
                    }
                }
                System.out.println("");
                for (int g = 0; g < getW().length; g++){
                    System.out.println(Arrays.toString(getW()[g]));
                }
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            }

*/


        for (int i = 0; i < getX1().length; i++){
            for (int j = 0; j < getX1()[i].length; j++){
                for (int k = 0; k < getX1()[i].length; k++){
                    getW()[j][k] += ((getX1()[i][j]) * (getX1()[i][k]));
                }
            }
        }

        float n = (float) 1/(getX1()[0].length);
        for (int i = 0; i < getW().length; i++){
            for (int j = 0; j < getW()[0].length; j++){
                getW()[i][j] *= n;
            }
        }
        diagonal();
        /*
        System.out.println("///////////////////////////////////////////////////////");
        for (int i = 0; i < getW().length; i++){
            System.out.println(Arrays.toString(getW()[i]));
        }
         */

        System.out.println(analyze(x2));
    }

    static public double[][] getX1() {
        return x1;
    }
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
        boolean r = false;
        int index = -1;
        double[] y = new double[x2.length];
        for (int i = 0; i < x2.length   ; i++){
            for (int j = 0; j < x2.length; j++){
                y[i] += x2[j]*getW()[i][j];
            }
        }
        System.out.println(Arrays.toString(y));
        for (int i = 0; i < y.length; i++){
            y[i] = y[i] >= 0 ? 1 : -1;
        }
        for (int i = 0; i < getX1().length; i++){
           for (int j = 0; j < y.length; j++){
               if (y[j] == getX1()[i][j]){
                   coincidences++;
                   //System.out.println("I: " + i);
               }
           }
           if ( (double) coincidences == getW()[i].length){
               r = true;
               index = i;
           }
           coincidences = 0;
        }
        System.out.println("YYYYYY  "+Arrays.toString(y));
        if (r) {
            System.out.println("WORK");
            return index;
        } else return index;

    }
    public static void training(){
        W = new double[getX1()[0].length][];
        for (int i = 0; i < getX1()[0].length; i++){
            W[i] = new double[getX1()[0].length];
        }
    }
}

