package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        boolean[][] visited = new boolean[N][M];

        // 왼쪽 방향 좌표
        int leftR = 0;
        int leftC = 0;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());       // 현재 좌표
        int C = Integer.parseInt(st.nextToken());
        int direct = Integer.parseInt(st.nextToken());  // 현재 방향

        int count = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        outer:
        while (true) {
            if (!visited[R][C]) {
                count++;
                visited[R][C] = true;
            }

            for (int i = 0; i < 5; i++) {
                if (i < 4) {
                    direct = (direct + 3) % 4;
                    leftR = R + d[direct][0];
                    leftC = C + d[direct][1];
                } else {    // 4방향 확인후 갈수 있는 곳이 없으면 후진
                    leftR = R - d[direct][0];
                    leftC = C - d[direct][1];
                }
                // 이동
                if (arr[leftR][leftC] != 1 && (!visited[leftR][leftC] || i == 4)) {
                    R = leftR;
                    C = leftC;
                    continue outer;
                }
            }
            // 이동 가능하지 않으면 작동 종료
            break;
        }
        System.out.println(count);
    }
}