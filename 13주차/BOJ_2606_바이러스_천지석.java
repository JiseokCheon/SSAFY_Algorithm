package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] computer;
    static int N, count;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        count = 0;
        computer = new int[N][N];
        visited = new boolean[N];

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            computer[a][b] = 1;
            computer[b][a] = 1;
        }
        visited[0] = true;
        dfs(0);
        System.out.println(count);
    }

    static void dfs(int num) {
        for (int i = 0; i < N; i++) {
            if (num == i) {
                continue;
            }
            if (computer[num][i] == 1 && !visited[i]) {
                visited[i] = true;
                count++;
                dfs(i);
            }
        }
    }
}