import java.util.Scanner;

public class Main {

    // convert function
    public static String conv(int n){

        // init fib array
        int[] arFib;
        arFib = new int[20];

        arFib[0] = 1;
        arFib[1] = 2;
        String fibStr = "";

        // fill fib array
        for(int i = 2; i < arFib.length; i++)
            arFib[i] = arFib[i - 1] + arFib[i - 2];


        // go down on array of fib numbers
        for(int i = arFib.length - 1; i > 0; i--){

            // if n between elements, then decrement form number lower of them
            if(n < arFib[i] && n >= arFib[i - 1]){
                n -= arFib[i - 1];

                // concatenate final string
                fibStr += "1";
            }
            else if (!fibStr.isEmpty()) fibStr += "0";
        }

        return fibStr;
    };

    public static void main(String[] args) {

        // convert from decimal to fib

        // input decimal number
        Scanner in = new Scanner(System.in);
        int num = in.nextInt();

        // execute convert function and write result
        System.out.println(conv(num));
    }
}