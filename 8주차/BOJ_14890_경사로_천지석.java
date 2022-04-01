package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N + 1][N + 1];
        int count = 0;                                      // 불가능한 길 갯수
        boolean[][] visited = new boolean[N + 1][N + 1];    // 겹치는 경사로 확인

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 가로 방향 길 확인
        outer:
        for (int i = 1; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                if (Math.abs(arr[i][j] - arr[i][j - 1]) > 1) {  // 높이 차이가 1보다 크면 불가능
                    count++;
                    continue outer;
                } else if (arr[i][j] - arr[i][j - 1] == 1) {    // 높이 차이가 왼쪽이 1 크면

                    if (j <= L || visited[i][j - 1]) {      // 경사로 놓을수 없는경우
                        count++;
                        continue outer;
                    }

                    for (int k = 2; k <= L; k++) {      // 경사로 놓을 위치가 평평한지, 겹치지 않는지 확인
                        if (arr[i][j - 1] != arr[i][j - k] || visited[i][j - k]) {
                            count++;
                            continue outer;
                        }
                    }
                    for (int k = 1; k <= L; k++) {      // 놓은 경사로 체크
                        visited[i][j - k] = true;
                    }

                } else if (arr[i][j] - arr[i][j - 1] == -1) {   // 높이 차이가 오른쪽이 1 크면
                    if (j + L - 1 > N || visited[i][j]) {       // 경사로 놓을 수 없는 경우
                        count++;
                        continue outer;
                    }

                    for (int k = 1; k < L; k++) {           // 경사로 놓을 위치가 평평한지, 겹치지 않는지
                        if (arr[i][j] != arr[i][j + k] || visited[i][j + k]) {
                            count++;
                            continue outer;
                        }
                    }

                    for (int k = 0; k < L; k++) {           // 놓은 경사로 체크
                        visited[i][j + k] = true;
                    }
                }
            }

        }

        visited = new boolean[N + 1][N + 1];

        // 세로 방향 길 확인
        outer:
        for (int j = 1; j <= N; j++) {
            for (int i = 2; i <= N; i++) {
                if (Math.abs(arr[i][j] - arr[i - 1][j]) > 1) {
                    count++;
                    continue outer;
                } else if (arr[i][j] - arr[i - 1][j] == 1) {
                    if (i <= L || visited[i - 1][j]) {
                        count++;
                        continue outer;
                    }

                    for (int k = 2; k <= L; k++) {
                        if (arr[i - 1][j] != arr[i - k][j] || visited[i - k][j]) {
                            count++;
                            continue outer;
                        }
                    }
                    for (int k = 1; k <= L; k++) {
                        visited[i - k][j] = true;
                    }

                } else if (arr[i][j] - arr[i - 1][j] == -1) {
                    if (i + L - 1 > N || visited[i][j]) {
                        count++;
                        continue outer;
                    }

                    for (int k = 1; k < L; k++) {
                        if (arr[i][j] != arr[i + k][j] || visited[i + k][j]) {
                            count++;
                            continue outer;
                        }
                    }

                    for (int k = 0; k < L; k++) {
                        visited[i + k][j] = true;
                    }
                }
            }
        }

        System.out.println(2 * N - count);
    }
}
