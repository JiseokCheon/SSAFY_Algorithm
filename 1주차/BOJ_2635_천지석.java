package com.company;

import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        StringBuilder temp = new StringBuilder();
        String answer = null;

        int N = sc.nextInt();
        int a, b, c, count;
        int max = 0;

        for (int i = N; i > 0; i--) {
            temp.setLength(0);
            temp.append(N).append(" ").append(i).append(" ");
            a = N;
            b = i;
            count = 2;
            while ((c = a - b) >= 0) {
                count++;
                a = b;
                b = c;
                temp.append(c + " ");
            }

            if (max < count) {
                max = count;
                answer = temp.toString();
            }
        }
        System.out.println(max);
        System.out.println(answer);
    }
}
