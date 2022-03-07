package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;   // 시간

        while (true) {
            time++;
            int count = 0;

            // 빙산 한번 녹이고 다 녹았는지 확인
            if (!melt()) {
                System.out.println(0);
                break;
            }

            // visited 초기화 하면서 빙산 갯수 확인
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    visited[i][j] = false;
                    if (arr[i][j] != 0)
                        count++;
                }
            }

            // dfs로 연결되있는 갯수랑 모든 빙산 갯수랑 같은지 확인
            if (count != dfs(x, y)) {
                System.out.println(time);
                break;
            }
        }
    }

    // 빙산 녹는 메서드
    static boolean melt() {
        boolean check = false;  // 모두 녹았는지 확인용
        int[][] temp = new int[N][M];   // 녹일때 사용할 배열

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 현재 빙산의 4방향 확인
                if (arr[i][j] != 0) {
                    for (int k = 0; k < 4; k++) {
                        if (arr[i + d[k][0]][j + d[k][1]] == 0) {
                            temp[i][j]--;
                        }
                    }
                }
            }
        }

        // 모두다 녹인 후 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (temp[i][j] > 0) {
                    // dfs 탐색하기 위한 빙산 위치 저장
                    x = i;
                    y = j;
                    check = true;   // 빙산 존재
                    arr[i][j] = temp[i][j];
                } else
                    arr[i][j] = 0;
            }
        }

        return check;
    }

    // 연결된 빙산 갯수 확인
    static int dfs(int x, int y) {
        visited[x][y] = true;
        int a = 1;

        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];
            if (arr[nextX][nextY] > 0 && !visited[nextX][nextY])
                a += dfs(x + d[i][0], y + d[i][1]);
        }
        return a;
    }
}
