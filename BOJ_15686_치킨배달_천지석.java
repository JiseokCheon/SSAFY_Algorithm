package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, sum;
    static ArrayList<int[]> chicken, home;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sum = Integer.MAX_VALUE;
        chicken = new ArrayList<>();
        home = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 1) {
                    home.add(new int[]{i, j});
                } else if (x == 2) {
                    chicken.add(new int[]{i, j});
                }
            }
        }
        visited = new boolean[chicken.size()];
        dfs(0, 0);
        System.out.println(sum);

    }

    static void dfs(int index, int count) {
        int[] dis = new int[home.size()];
        for (int i = 0; i < chicken.size(); i++) {
            if (visited[i]) {
                for (int j  = 0; j< home.size(); j++) {
                    int x = Math.abs(chicken.get(i)[0] - home.get(j)[0]) + Math.abs(chicken.get(i)[1] - home.get(j)[1]);
                    dis[j] = dis[j] == 0 ? x : Math.min(dis[j], x);
                }
            }
        }
        if (count == M)
            sum = Math.min(sum, Arrays.stream(dis).sum());

        for (int i = index; i < chicken.size(); i++) {
            if (count + 1 <= M) {
                visited[i] = true;
                dfs(i + 1, count + 1);
                visited[i] = false;
            }
        }
    }
}

