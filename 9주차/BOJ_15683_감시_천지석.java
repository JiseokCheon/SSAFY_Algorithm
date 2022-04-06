package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int N, M, max, size;
    static int[][] direct = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static ArrayList<int[]> cctv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        cctv = new ArrayList<>();   // cctv 좌표
        max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0 && arr[i][j] != 6) {
                    cctv.add(new int[]{i, j});
                }
            }
        }

        size = cctv.size();

        dfs(-1);
        System.out.println(M * N - max);
    }


    static void dfs(int index) {
        // cctv 사각지대 갯수 확인
        if (index == size - 1) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (arr[i][j] != 0)
                        count++;
                }
            }
            max = Math.max(count, max);
            return;
        }

        for (int i = index + 1; i < size; i++) {
            int x = cctv.get(i)[0];
            int y = cctv.get(i)[1];

            for (int j = 0; j < 4; j++) {   // 4방향 확인

                cctvViewCount(arr[x][y], j, x, y, -1);
                dfs(i);
                cctvViewCount(arr[x][y], j, x, y, 1);

                if (arr[x][y] == 2 && j == 1 || arr[x][y] == 5) {   // 2번은 2방향, 5번은 1방향만 해도 동일
                    break;
                }
            }
        }
    }

    static void cctvViewCount(int num, int startD, int x, int y, int check) {
        for (int i = 0; i < 4; i++) {
            int nextX = x;
            int nextY = y;

            while ((nextX += direct[(startD + i) % 4][0]) >= 0 && nextX < N
                    && (nextY += direct[(startD + i) % 4][1]) >= 0 && nextY < M) {

                if (arr[nextX][nextY] == 6) {
                    break;
                }

                if (arr[nextX][nextY] > 0) {
                    continue;
                }
                arr[nextX][nextY] += check; // 확인한 곳 check 값으로 설정
            }

            // cctv 종류 별 감시 방향
            if (num == 1) {
                break;
            } else if (num == 2) {
                i++;
            } else if (num == 3) {
                i += 2;
            } else if (num == 4 && i == 2) {
                i++;
            }
        }
    }
}