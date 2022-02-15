package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[5];
        int temp = 0;
        boolean check = false;

        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        while (!check) {
            check = true;
            for (int i = 0; i < 4; i++) {
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    for (int j : arr) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                }
                if (arr[i] != i + 1) {
                    check = false;
                }
            }
        }
    }
}

