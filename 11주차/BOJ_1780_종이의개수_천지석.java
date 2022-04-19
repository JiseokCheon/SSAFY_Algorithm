package com.company;

import java.io.*;
import java.util.*;


public class Main {

    static int[][] arr;

    static int[] answer;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        answer = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, N);
        System.out.println(answer[0]);
        System.out.println(answer[1]);
        System.out.println(answer[2]);
    }

    static void dfs(int a, int b, int x) {
        int temp = check(a, b, x);
        if (temp != -2) {
            answer[temp + 1]++;
            return;
        }
        temp = x / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                dfs(a + i * temp, b + j * temp, temp);
            }
        }
    }

    static int check(int a, int b, int x) {
        for (int i = a; i < a + x; i++) {
            for (int j = b; j < b + x; j++) {
                if (arr[a][b] != arr[i][j]) {
                    return -2;
                }
            }
        }
        return arr[a][b];
    }
}

