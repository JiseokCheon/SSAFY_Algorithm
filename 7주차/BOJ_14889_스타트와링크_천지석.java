package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int[] team;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        team = new int[N];      // start 팀에 들어갔는지 확인
        arr = new int[N][N];    // 능력치
        answer = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0, 0);
        System.out.println(answer);
    }

    // 조합
    static void dfs(int count, int index) {
        if (count == N / 2) {   // 팀이 구성되면
            int start = 0;
            int link = 0;

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (team[i] == 0 && team[j] == 0) {
                        start += arr[i][j] + arr[j][i];
                    } else if (team[i] == 1 && team[j] == 1) {
                        link += arr[i][j] + arr[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(start - link));
            return;
        }

        for (int i = index; i < N; i++) {
            if (team[i] == 0) {
                team[i] = 1;
                dfs(count + 1, i + 1);
                team[i] = 0;
            }
        }
    }
}

