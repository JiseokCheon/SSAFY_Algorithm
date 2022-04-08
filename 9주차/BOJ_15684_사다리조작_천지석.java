package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int N, H, M, answer;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        answer = -1;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        arr = new int[H + 1][N + 1];

        // 가로선 위치 1로 처리
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        dfs(1, 1, 0);
        System.out.println(answer);
    }

    // 3개 뽑는 조합으로 찾음
    static void dfs(int x, int y, int count) {
        if (check()) {
            answer = answer == -1 ? count : Math.min(count, answer);
        }

        for (int i = x; i <= H; i++) {
            if (i != x) {
                y = 1;
            }
            for (int j = y; j < N; j++) {
                if (count < 3 && arr[i][j] != 1 && arr[i][j - 1] != 1 && (answer == -1 || answer > count + 1)) {
                    arr[i][j] = 1;
                    dfs(i, j, count + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }

    // 번호에 맞게 내려오는지 확인
    static boolean check() {
        for (int i = 1; i < N + 1; i++) {
            int x = i;
            for (int j = 1; j < H + 1; j++) {
                if (arr[j][x] == 1) {
                    x++;
                } else if (arr[j][x - 1] == 1) {
                    x--;
                }
            }
            if (i != x) {
                return false;
            }
        }
        return true;
    }
}


