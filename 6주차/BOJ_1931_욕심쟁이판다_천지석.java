package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int[][] arr, dp;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];    // 대나무 숲
        dp = new int[N][N];     // 각 칸에서 출발할때 이동 가능한 최대 횟수
        answer = 0; // 이동할 수 있는 칸의 최댓값

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 칸에서 출발할 때
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dfs(i, j);
            }
        }

        System.out.println(answer + 1);
    }


    static int dfs(int x, int y) {
        // 최대 방문 가능한 횟수가 구해져 있으면 그대로 return
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        // 현재 위치에서 이동 가능한 최대 값
        int value = 0;

        for (int i = 0; i < 4; i++) {
            int nextX = d[i][0] + x;
            int nextY = d[i][1] + y;
            int count = 1;
            // 다음 값이 더 큰 칸만
            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N && arr[x][y] < arr[nextX][nextY]) {
                // 이동 가능한 최댓값을 찾음
                // count + dfs(nextX, nextY) -> 다음 칸으로 이동 횟수 + 다음 칸의 이동 가능한 최댓값
                value = Math.max(value, count + dfs(nextX, nextY));
            }
        }

        dp[x][y] = value;       // 현재 위치의 최댓값 설정
        answer = Math.max(value, answer);   // 정답 찾아줌
        return value;   // 최댓값 return
    }
}