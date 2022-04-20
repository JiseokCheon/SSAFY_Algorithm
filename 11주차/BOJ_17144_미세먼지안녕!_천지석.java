package com.company;

import java.io.*;
import java.util.*;


public class Main {

    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // 시계방향
    static int[][] arr;

    static int R, C, T, refresh, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];
        refresh = 0;
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1 && refresh == 0) {
                    refresh = i;
                }
            }
        }

        for (int i = 0; i < T; i++) {
            dust();        // 미세먼지 확산
            air(1);     // 아래쪽
            air(-1);    // 위쪽
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (arr[i][j] > 0) {
                    answer += arr[i][j];
                }
            }
        }
        System.out.println(answer);
    }

    static void air(int a) {
        int x = a == 1 ? refresh - 1 : refresh + 2;
        int y = 0;
        int direct = a == 1 ? 0 : 2;
        while (true) {
            int nextX = x + d[direct][0];
            int nextY = y + d[direct][1];
            if (nextX == (a == 1 ? refresh : refresh + 1) && nextY == 0) {
                arr[x][y] = 0;
                break;
            }

            if (nextX > (a == 1 ? refresh : R - 1) || nextY >= C || nextX < (a == 1 ? 0 : refresh + 1) || nextY < 0) {
                direct = (direct + (a == 1 ? 1 : 3)) % 4;
                continue;
            }
            arr[x][y] = arr[nextX][nextY];
            x = nextX;
            y = nextY;
        }
    }


    static void dust() {
        int[][] temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int count = 0;

                if (arr[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        int nextR = i + d[k][0];
                        int nextC = j + d[k][1];
                        if (nextR >= 0 && nextC >= 0 && nextR < R && nextC < C && arr[nextR][nextC] != -1) {
                            count++;
                            temp[nextR][nextC] += arr[i][j] / 5;
                        }
                    }
                }
                temp[i][j] -= arr[i][j] / 5 * count;
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += temp[i][j];
            }
        }
    }
}
