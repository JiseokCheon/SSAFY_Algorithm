package com.company;

import java.io.*;
import java.util.*;


public class Main {

    static int[][] d = {{0}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dice = new int[4][4];   // 주사위
        int[][] map = new int[N][M];    // 지도

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int command = Integer.parseInt(st.nextToken()); // 명령어
            int nextX = x + d[command][0];
            int nextY = y + d[command][1];

            // 다음 위치 지도 벗어나면 다음으로
            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            roll(dice, command);    // 방향으로 굴림

            // 지도에서 현재 좌표 0이면 주사위 값 복사
            if (map[nextX][nextY] == 0) {
                map[nextX][nextY] = dice[1][1];
            }
            // 0 아니면 주사위로 복사 후 지도값 0으로
            else {
                dice[1][1] = map[nextX][nextY];
                map[nextX][nextY] = 0;
            }

            x = nextX;
            y = nextY;
            System.out.println(dice[1][3]);
        }
    }

    static void roll(int[][] dice, int direct) {
        int i = 0;      // 값 바꿀 위치
        int cnt = 0;    // 값 바꾼 횟수

        // 동, 서 방향
        if (direct < 3) {
            int temp = dice[1][0];          // 값 바꿀때 사용할 변수
            int x = direct == 1 ? 1 : 3;    // 동 -> 1 (증가), 서 -> 3 (감소)
            while (cnt < 3) {
                int nextIndex = (i + x) % 4;
                dice[1][i] = dice[1][nextIndex];
                i = (i + x) % 4;
                cnt++;
            }
            dice[1][i] = temp;
            dice[3][1] = dice[1][3];    // 주사위 위쪽값 맞춰줌
        }
        // 북, 남 방향
        else {
            int temp = dice[0][1];          // 값 바꿀때 사용할 변수
            int x = direct == 3 ? 3 : 1;    // 북 -> 1 (증가), 남 -> 3 (감소)
            while (cnt < 3) {
                int nextIndex = (i + x) % 4;
                dice[i][1] = dice[nextIndex][1];
                i = (i + x) % 4;
                cnt++;
            }
            dice[i][1] = temp;
            dice[1][3] = dice[3][1];    // 주사위 위쪽값 맞춰줌

        }
    }
}