package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int N, M;
    static int[][] arr;
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            arr = new int[N][M];
            list = new ArrayList<>();   // 배추 위치

            int count = 0;

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
                list.add(new int[]{x, y});
            }

            for (int i = 0; i < list.size(); i++) {
                if (arr[list.get(i)[0]][list.get(i)[1]] == 1) {
                    dfs(list.get(i)[0], list.get(i)[1]);
                    count++;
                }
            }
            System.out.println(count);
        }
    }

    // 배추 0으로 바꿔줌
    static void dfs(int x, int y) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && arr[nextX][nextY] == 1) {
                arr[nextX][nextY] = 0;
                dfs(nextX, nextY);
            }
        }
    }
}