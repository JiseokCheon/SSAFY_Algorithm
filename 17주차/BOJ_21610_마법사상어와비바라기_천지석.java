package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] direct = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] arr;
    static ArrayList<int[]> clouds;
    static boolean[][] cloudCheck;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        clouds = new ArrayList<>(List.of(new int[]{N - 1, 0}, new int[]{N - 1, 1}, new int[]{N - 2, 0}, new int[]{N - 2, 1}));
        int M = Integer.parseInt(st.nextToken());
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            moveCloud(d - 1, s);
            rain();
            findCloud();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += arr[i][j];
            }
        }

        System.out.println(answer);
    }

    static void moveCloud(int d, int s) {
        cloudCheck = new boolean[N][N];

        for (int[] cloud : clouds) {
            cloud[0] = ((cloud[0] + direct[d][0] * s) % N + N) % N;
            cloud[1] = ((cloud[1] + direct[d][1] * s) % N + N) % N;
            cloudCheck[cloud[0]][cloud[1]] = true;
        }
    }

    static void rain() {
        for (int[] cloud : clouds) {
            arr[cloud[0]][cloud[1]]++;
        }

        for (int[] cloud : clouds) {
            for (int i = 1; i < 8; i += 2) {
                int nextR = cloud[0] + direct[i][0];
                int nextC = cloud[1] + direct[i][1];

                if (nextR >= 0 && nextC >= 0 && nextR < N && nextC < N && arr[nextR][nextC] > 0) {
                    arr[cloud[0]][cloud[1]]++;
                }
            }
        }
        clouds.clear();
    }

    static void findCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!cloudCheck[i][j] && arr[i][j] > 1) {
                    arr[i][j] -= 2;
                    clouds.add(new int[]{i, j});
                }
            }
        }
    }

}
