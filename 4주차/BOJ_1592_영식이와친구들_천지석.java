package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int count = 0;
        int current = 0;

        while (true) {
            if (++arr[current] == M) {
                break;
            }
            count++;

            if (arr[current] % 2 == 1) {
                current = (current + L) % N;
            } else {
                current = (N + current - L) % N;
            }
        }
        System.out.println(count);
    }
}