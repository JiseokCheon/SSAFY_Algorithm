package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        ArrayList<Integer> coins = new ArrayList<>();

        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        for (int i = N - 1; i >= 0; i--) {
            if (K >= coins.get(i)) {
                cnt += K / coins.get(i);
                K = K % coins.get(i);
            }
        }
        System.out.println(cnt);
    }
}

