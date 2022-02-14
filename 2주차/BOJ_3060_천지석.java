package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[6];
            int[] temp = new int[6];
            int time = 1;
            int sum = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 6; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                sum += arr[i];
            }

            while (N - sum >= 0) {
                time++;
                sum = 0;
                for (int i = 0; i < 6; i++) {
                    temp[i] = arr[i] + arr[(i + 1) % 6] + arr[(i + 7) % 6] + arr[(i + 3) % 6];
                    sum += temp[i];
                }

                for (int i = 0; i < 6; i++) {
                    arr[i] = temp[i];
                }
            }
            System.out.println(time);
        }
    }
}

