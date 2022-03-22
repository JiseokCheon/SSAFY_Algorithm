package com.company;

import java.io.*;
import java.util.*;


public class Main {
    // 방향 위,오르쪽, 아래, 왼쪽
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int time = 0;   
        int direct = 1; 

        int[][] board = new int[N + 1][N + 1];  // 보드판
        char[] rotate = new char[10001];        // 시간마다 방향 변환 정보
        Deque<int[]> snake = new LinkedList<>();    // 뱀

        // 보드판에 사과 정보 입력
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int L = Integer.parseInt(br.readLine());

        // 시간별로 방향 정보 입력
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            rotate[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        }

        // 시작 1,1
        snake.addFirst(new int[]{1, 1});

        outer:
        while (true) {
            time++;     // 1초 증가
            int[] head = snake.peekFirst(); // 현재 머리 정보

            // 다음 머리 위치
            int nextX = head[0] + d[direct][0];
            int nextY = head[1] + d[direct][1];

            // 다음 머리 위치가 벽에 닿으면
            if (nextX <= 0 || nextX > N || nextY <= 0 || nextY > N) {
                System.out.println(time);
                break;
            }

            // 머리가 현재 뱀의 몸에 닿으면
            for (int[] body : snake) {
                if (body[0] == nextX && body[1] == nextY) {
                    System.out.println(time);
                    break outer;
                }
            }
            // 머리 이동 완료
            snake.addFirst(new int[]{nextX, nextY});

            // 사과 없으면 꼬리 당김
            if (board[nextX][nextY] != 1) {
                snake.removeLast();
            } else {
                board[nextX][nextY] = 0;    // 사과 먹음
            }

            // 회전 처리
            if (rotate[time] == 'L') {
                direct = (direct + 3) % 4;
            } else if (rotate[time] == 'D') {
                direct = (direct + 1) % 4;
            }
        }
    }
}