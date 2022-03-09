package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        answer = 0;
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(arr, 0);
        System.out.println(answer);
    }

    // 5번 이동 시켜서 최대값 찾는 메서드
    static void dfs(int[][] arr, int count) {
        // 5번 이동시킬때가 가장 크기 때문에 5일때만 최댓값 찾음
        if (count == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(arr[i][j], answer);
                }
            }
            return;
        }

        // 이동시킬 블록
        int[][] temp = new int[N][N];

        // 4방향으로 반복
        for (int j = 0; j < 4; j++) {
            // 이동시킬 블록 초기화
            for (int i = 0; i < N; i++) {
                temp[i] = arr[i].clone();
            }
            // 4방향으로 움직임
            dfs(move(temp, j), count + 1);
        }
    }

    // 움직이는 함수
    static int[][] move(int[][] arr, int x) {
        for (int i = 0; i < N; i++) {
            int index = 0;  // 블록이 이동할 인덱스
            int temp = 1;   // 이전블록값
            if (x == 0) {   // 왼쪽으로 합쳐질때
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] != 0) {
                        if (temp == arr[i][j]) {    // 이전 블록과 같으면
                            arr[i][index - 1] = arr[i][j] * 2;  // 이전 인덱스의 블록을 2배 증가
                            temp = 1;   // 현재 블록은 합쳐져서 사용할 수 없으므로 이전블록을 사용할 수 없도록 1로 변경
                        } else {    // 이전 블록과 같지 않으면
                            arr[i][index++] = arr[i][j];    // 현재 인덱스 값에 넣음
                            temp = arr[i][j];   // 이전 블록 값에 넣어줌
                        }
                    }
                    // 0 이면 그냥 넘어가서 index 도 증가되지 않음
                }
                // 남은 부분 모두 0으로
                while (index < N) {
                    arr[i][index++] = 0;
                }
            } else if (x == 1) {    // 위쪽으로 합쳐질때
                for (int j = 0; j < N; j++) {
                    if (arr[j][i] != 0) {
                        if (temp == arr[j][i]) {
                            arr[index - 1][i] = arr[j][i] * 2;
                            temp = 1;
                        } else {
                            arr[index++][i] = arr[j][i];
                            temp = arr[j][i];
                        }
                    }
                }
                while (index < N) {
                    arr[index++][i] = 0;
                }
            } else if (x == 2) {    // 오른쪽으로 합쳐질때
                index = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (arr[i][j] != 0) {
                        if (temp == arr[i][j]) {
                            arr[i][index + 1] = arr[i][j] * 2;
                            temp = 1;
                        } else {
                            arr[i][index--] = arr[i][j];
                            temp = arr[i][j];
                        }
                    }
                }
                while (index >= 0) {
                    arr[i][index--] = 0;
                }
            } else {    // 아래쪽으로 합쳐질때
                index = N - 1;
                for (int j = N - 1; j >= 0; j--) {
                    if (arr[j][i] != 0) {
                        if (temp == arr[j][i]) {
                            arr[index + 1][i] = arr[j][i] * 2;
                            temp = 1;
                        } else {
                            arr[index--][i] = arr[j][i];
                            temp = arr[j][i];
                        }
                    }
                }
                while (index >= 0) {
                    arr[index--][i] = 0;
                }
            }
        }

        return arr;
    }
}
