package com.company;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());
        int[][] arr = new int[6][2];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 6; i++) {
            if (arr[i][0] == arr[(i + 2) % 6][0] && arr[(i + 5) % 6][0] == arr[(i + 1) % 6][0]) {
                System.out.println(K * (arr[(i + 4) % 6][1] * arr[(i + 3) % 6][1] - arr[i][1] * arr[(i + 1) % 6][1]));
                break;
            }
        }
    }
}