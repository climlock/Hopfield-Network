
public class Dispatcher {

    static private double[][] x1 = {{1,-1,-1,
                                     1,-1,-1,
                                     1, 1, 1},
                                    {1, 1, 1,
                                     1,-1, 1,
                                     1, 1, 1},
                                    {1,-1, 1,
                                     1, 1, 1,
                                     1,-1, 1}
    };

    //static double[] x2;
    static private double[][] W;
    public static void main (String[] args){
        double[] x2 = {1,-1,-1,
                1,-1,-1,
                1, 1, -1};

        train(x2);


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
        for (int i = 0; i < y.length; i++){
            y[i] = y[i] >= 0 ? 1 : -1;
        }
        for (int i = 0; i < getX1().length; i++){
           for (int j = 0; j < y.length; j++){
               if (y[j] == getX1()[i][j]){
                   coincidences++;
               }
           }
           if ( (double) coincidences == getW()[i].length){
               r = true;
               index = i;
           }
           coincidences = 0;
        }
        return index;

    }
    static public void train(double[] x2){
        int answer = -1;

        training();

        float n = (float) 1/(getX1()[0].length);
        for (int i = 0; i < getX1().length; i++){
            for (int j = 0; j < getX1()[i].length; j++){
                for (int k = 0; k < getX1()[i].length; k++){
                    getW()[j][k] += ((getX1()[i][j]) * (getX1()[i][k]));
                }
            }
        }
        for (int l = 0; l < getW().length; l++){
            for (int j = 0; j < getW()[0].length; j++){
                getW()[l][j] *= n;
            }
        }

        diagonal();
        answer = analyze(x2);
        if (answer != -1){
            System.out.println(answer + " - Index");
        } else System.out.println("Nothing");
    }
    public static void training(){
        W = new double[getX1()[0].length][];
        for (int i = 0; i < getX1()[0].length; i++){
            W[i] = new double[getX1()[0].length];
        }
    }
}

