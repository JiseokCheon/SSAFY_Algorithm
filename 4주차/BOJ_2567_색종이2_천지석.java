package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int[][] arr = new int[102][102];
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int N = Integer.parseInt(br.readLine());
        int count = 0;

        // 도화지에 색종이가 위치한 부분은 1로
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    arr[x + j + 1][y + k + 1] = 1;
                }
            }
        }

        // 1인 부분의 4방 탐색해서 0이면 길이 1씩 증가
        for (int i = 1; i <= 101; i++) {
            for (int j = 1; j <= 101; j++) {
                if (arr[i][j] == 1) {
                    for (int k = 0; k < 4; k++) {
                        if (arr[i + d[k][0]][j + d[k][1]] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        System.out.println(count);
    }
}