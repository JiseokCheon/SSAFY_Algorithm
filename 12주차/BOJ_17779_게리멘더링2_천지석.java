package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr, check, startPoint;
    static boolean[][] visited;
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        answer = Integer.MAX_VALUE;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        check = new int[N][N];
        visited = new boolean[N][N];
        startPoint = new int[][]{{0, 0}, {0, N - 1}, {N - 1, 0}, {N - 1, N - 1}, {0, 0}};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                findD(i, j);
            }
        }

        System.out.println(answer);
    }

    static void findD(int x, int y) {
        for (int d1 = 1; d1 < N; d1++) {
            for (int d2 = 1; d2 < N; d2++) {
                if (x + d1 + d2 < N && 0 <= y - d1 && y + d2 < N) {
                    check = new int[N][N];
                    visited = new boolean[N][N];
                    startPoint[4][0] = x+1;
                    startPoint[4][1] = y;

                    divide(x, y, d1, d2);

                    int max = 0;
                    int min = Integer.MAX_VALUE;

                    for (int i = 0; i < 5; i++) {
                        int a = population(startPoint[i][0], startPoint[i][1], i + 1);
                        max = Math.max(a, max);
                        min = Math.min(a, min);
                    }
                    answer = Math.min(max - min, answer);
                }
            }
        }
    }

    static void divide(int x, int y, int d1, int d2) {
        for (int i = 0; i <= d1; i++) {
            check[x + i][y - i] = 5;
            check[x + d2 + i][y + d2 - i] = 5;
        }
        for (int i = 0; i <= d2; i++) {
            check[x + i][y + i] = 5;
            check[x + d1 + i][y - d1 + i] = 5;
        }

        int temp = 1;
        while (x - temp >= 0) {
            check[x - temp++][y] = 1;
        }

        temp = 1;
        while (y + d2 + temp < N) {
            check[x + d2][y + d2 + temp++] = 2;
        }

        temp = 1;
        while (y - d1 - temp >= 0) {
            check[x + d1][y - d1 - temp++] = 3;
        }

        temp = 1;
        while (x + d2 + d1 + temp < N) {
            check[x + d2 + d1 + temp++][y + d2 - d1] = 4;
        }
    }

    static int population(int x, int y, int num) {
        int count = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            count += arr[node[0]][node[1]];
            if (num != 5 && check[node[0]][node[1]] == num) {
                continue;
            }
            for (int[] i : d) {
                int nextX = node[0] + i[0];
                int nextY = node[1] + i[1];
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N
                        && !visited[nextX][nextY] && (check[nextX][nextY] == num || check[nextX][nextY] == 0)) {
                    q.add(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
        }

        return count;
    }
}