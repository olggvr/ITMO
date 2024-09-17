
// This code is example of Hamming code: it gets input message and find if there an error consisting, if it is, then it writes right message

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // input start message
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        // find s
        int s1 = ((int)s.charAt(0) + (int)s.charAt(2) + (int)s.charAt(4) + (int)s.charAt(6))%2;
        int s2 = ((int)s.charAt(1) + (int)s.charAt(2) + (int)s.charAt(5) + (int)s.charAt(6))%2;
        int s3 = ((int)s.charAt(3) + (int)s.charAt(4) + (int)s.charAt(5) + (int)s.charAt(6))%2;

        // find index of error bit
        int ei = s1 + s2 * 2 + s3 * 4;

        // out right message
        for (int i = 0; i < s.length(); i++){
            if (i == 2 || i == 4 || i == 5 || i == 6) {
                if (i == ei - 1) {
                    if (s.charAt(i) == '0')
                        System.out.print("1");
                    else
                        System.out.print("0");
                    continue;
                }
                System.out.print(s.charAt(i));
            }
        }
    }
}