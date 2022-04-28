package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N][M];
        int[][] arr = new int[N][M];
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 0; i < N; i++) {
            String s = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] node = q.poll();

            if (node[0] == N - 1 && node[1] == M - 1) {
                System.out.println(node[2]);
            }

            for (int[] i : d) {
                int nextX = node[0] + i[0];
                int nextY = node[1] + i[1];
                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M
                        && arr[nextX][nextY] == 1 && !visited[nextX][nextY]) {
                    q.add(new int[]{nextX, nextY, node[2] + 1});
                    visited[nextX][nextY] = true;
                }
            }
        }
    }
}