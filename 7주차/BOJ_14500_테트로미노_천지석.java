package com.company;

import java.io.*;
import java.util.*;

public class Main {
    // 블록 ㄴ, ㄹ 모양 블록만 뒤집어서 추가
    static int[][][] block = {{{0, 0}, {0, 1}, {0, 2}, {0, 3}}, {{0, 0}, {1, 1}, {0, 1}, {1, 0}},
            {{0, 0}, {1, 0}, {2, 0}, {2, 1}}, {{0, 0}, {1, 0}, {1, 1}, {2, 1}}, {{0, 0}, {0, 1}, {0, 2}, {1, 1}},
            {{0, 0}, {1, 0}, {2, 0}, {2, -1}}, {{0, 0}, {0, 1}, {1, 1}, {1, 2}}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int paper = Math.max(N, M);                 // 종이 크기
        int[][][] arr = new int[4][paper][paper];   // 종이 4방향으로 돌림

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < paper; j++) {
                Arrays.fill(arr[i][j], 2000);   // 종이에서 불가능한 곳
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[0][i][j] = num;
                arr[1][j][N - i - 1] = num;
                arr[2][N - i - 1][M - j - 1] = num;
                arr[3][M - j - 1][i] = num;
            }
        }

        for (int k = 0; k < 4; k++) {
            for (int i = 0; i < paper; i++) {
                for (int j = 0; j < paper; j++) {
                    // 블록을 하나씩 넣어봄
                    for (int b = 0; b < block.length; b++) {
                        int sum = 0;
                        for (int v = 0; v < block[b].length; v++) {
                            int x = i + block[b][v][0];
                            int y = j + block[b][v][1];
                            if (x >= 0 && x < paper && y >= 0 && y < paper) {
                                if (arr[k][x][y] == 2000) {
                                    break;
                                }
                                sum += arr[k][x][y];
                            }
                        }
                        max = Math.max(sum, max);
                    }
                }
            }
        }

        System.out.println(max);

    }
}

