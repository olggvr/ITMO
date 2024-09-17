public class Main {
    public static void main(String[] args) {

        // 1
        // init
        int[] c;
        int n = (25 - 7 + 1)/2 + (25 - 7 + 1) % 2;
        c = new int[n];
        int count = 0;

        int r;
        r = 10;

        // find array of numbers 7:25
        for (int i = 7; i <= 25; i++) {
            if (i % 2 != 0) {
                c[count] = i;
                count++;
            }
        }

        // 2
        // init
        float[] x;
        x = new float[17];
        float min = -7.0f;
        float max = 12.0f;

        // find array of random numbers
        for (int j = 0; j < x.length; j++){
            x[j] = min + Math.round((float)Math.random()*((max - min)));
        }

        // 3
        // init
        double[][] ar;
        ar = new double[10][17];

        // calculate
        for (int i = 0; i < ar.length; i++) {
            for (int j = 0; j < ar[i].length; j++) {
                double num = (x[j] + 2.5) / 19;
                if (c[i] == 15) {
                    ar[i][j] = Math.log(Math.acos(num));
                } else if (c[i] == 7 || c[i] == 11 || c[i] == 17 || c[i] == 19 || c[i] == 23) {
                    ar[i][j] = Math.pow(((double)1/4 + Math.atan(num) / 2), Math.atan(num * num));
                } else {
                    ar[i][j] = Math.sin(Math.cos(Math.pow(Math.E, Math.log(Math.abs(x[j])))));
                }
                System.out.printf("%7.4f\t", ar[i][j]);
            }
            System.out.println();
        }
    }
}