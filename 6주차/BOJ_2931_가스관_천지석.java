package com.company;

import java.io.*;
import java.util.*;

public class Main {
    // 방향 (인덱스 0 -> 아래, 1 -> 위, 2 -> 오른쪽, 3 -> 왼쪽)
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    // 아래쪽, 위쪽, 오른쪽, 왼쪽 순서대로 가능한 블록
    static char[][] block = {{'|', '+', '2', '3'}, {'|', '+', '1', '4'}, {'-', '+', '3', '4'}, {'-', '+', '1', '2'}};
    // 숫자 블록으로 바뀌는 방향 (-1은 못가는 방향)
    // 블록 1 이면 { 위에서 아래로 못들어옴 (-1),
    // 아래에서 위로 들어오면 오른쪽으로 나감 (2),
    // 왼쪽에서 오른쪽으로 못들어옴 (-1),
    // 오른쪽에서 왼쪽으로 들어오면 아래쪽으로 나감 (0)}
    static int[][] number = {{-1, 2, -1, 0}, {2, -1, -1, 1}, {3, -1, 1, -1}, {-1, 3, 0, -1}};
    static char[][] arr;
    static int[] M;     // 모스크바 위치 (현재 위치로도 사용)
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new char[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'M') {     // 모스크바 위치
                    M = new int[]{i, j, 0};
                }
            }
        }
        // 모스크바 위치 주변에 시작하는 가스관 위치 찾음
        outer:
        for (int i = 0; i < 4; i++) {   // 상하좌우 확인
            int[] nextM = {d[i][0] + M[0], d[i][1] + M[1]};

            if (nextM[0] >= 0 && nextM[1] >= 0 && nextM[0] < R && nextM[1] < C) {
                for (int j = 0; j < 4; j++) {   // 각 방향마다 가능한 블록 중 존재하는 블록 찾음
                    if (arr[nextM[0]][nextM[1]] == block[i][j]) {
                        M[0] = nextM[0];
                        M[1] = nextM[1];
                        M[2] = i;       // 흐르는 방향
                        break outer;
                    }
                }
            }
        }

        find(); // 가스관이 끊어진 곳 찾음

        // 끊어진 곳 위치, 방향 저장
        int blockR = M[0];
        int blockC = M[1];
        int direct = M[2];

        // 방향별로 가능한 블록만 확인
        for (int i = 0; i < block[direct].length; i++) {
            M[0] = blockR;
            M[1] = blockC;
            M[2] = direct;
            arr[blockR][blockC] = block[direct][i];
            if (find()) {
                System.out.println((blockR + 1) + " " + (blockC + 1) + " " + block[direct][i]);
                break;
            }
        }
    }


    static boolean find() {
        while (M[0] >= 0 && M[0] < R && M[1] >= 0 && M[1] < C && arr[M[0]][M[1]] != '.') {
            int x = (arr[M[0]][M[1]] - '0') - 1;    // 현재 위치의 블록이 숫자일때 사용할 값

            if (arr[M[0]][M[1]] == 'Z') {   // 자그레브 도착하면 true
                return true;
            }
            // +, -, | 블록이면 방향 그대로 유지하고 다음 위치로
            // - 블록이면 2(오른쪽), 3(왼쪽) 방향일때만 가능
            // | 블록이면 0(아래쪽), 1(위쪽) 방향일때만 가능
            if (arr[M[0]][M[1]] == '+'
                    || (arr[M[0]][M[1]] == '-' && (M[2] == 2 || M[2] == 3))
                    || (arr[M[0]][M[1]] == '|' && (M[2] == 0 || M[2] == 1))) {
                M[0] += d[M[2]][0];
                M[1] += d[M[2]][1];
            }
            // 숫자블록이면 방향 바꾼 후 다음 위치로
            else if (x < 4 && x >= 0 && number[x][M[2]] >= 0) {
                M[0] += d[number[x][M[2]]][0];
                M[1] += d[number[x][M[2]]][1];
                M[2] = number[x][M[2]];
            } else {
                return false;
            }
        }
        return false;
    }
}