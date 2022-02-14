package com.company;

import java.io.*;
import java.util.*;

public class Solution {
    static LinkedList<Integer> list;
    static int[] result;
    static int K, T, len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            System.out.print("#" + tc + " ");
            K = Integer.parseInt(br.readLine());
            len = (int) Math.pow(2, K);
            list = new LinkedList<>();
            result = new int[len];

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i < len; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            dfs(1);
            int index = 1;
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < (int) Math.pow(2, i); j++) {
                    System.out.print(result[index++] + " ");
                }
                System.out.println();
            }
        }
    }

    static void dfs(int i) {
        if (i * 2 < len)
            dfs(i * 2);

        result[i] = list.remove(0);

        if (i * 2 + 1 < len)
            dfs(i * 2 + 1);

    }
}
