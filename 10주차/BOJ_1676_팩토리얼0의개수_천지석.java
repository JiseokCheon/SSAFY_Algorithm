package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Integer.parseInt(br.readLine());
        int count1 = 0; // 2 개수
        int count2 = 0; // 5 개수
        for (int i = 1; i <= N; i++) {
            int x = i;
            while (x % 2 == 0) {
                count1++;
                x /= 2;
            }
            x = i;
            while (x % 5 == 0) {
                count2++;
                x /= 5;
            }
        }

        System.out.println(Math.min(count1, count2));
    }
}
