package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    static int[][] arr;
    static int[] tornado;
    static int[][] d = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][] sandPer = {{0, -2, 5}, {-1, -1, 10}, {1, -1, 10}, {-2, 0, 2}, {2, 0, 2}, {-1, 0, 7}, {1, 0, 7}, {-1, 1, 1}, {1, 1, 1}};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        answer = 0;
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        tornado = new int[2];
        tornado[0] = tornado[1] = N / 2;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        moveTornado();
        System.out.println(answer);
    }

    static void moveTornado() {
        int x = 0;
        int direct = 0;

        outer:
        while (true) {
            x++;
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < x; i++) {
                    tornado[0] = tornado[0] + d[direct][0];
                    tornado[1] = tornado[1] + d[direct][1];
                    if (tornado[0] < 0 || tornado[1] < 0 || tornado[0] >= N || tornado[1] >= N) {
                        break outer;
                    }
                    moveSand(direct);
                }
                direct = (direct + 1) % 4;
            }
        }
    }

    static void moveSand(int direct) {

        int sand, sandX, sandY;
        int total = 0;
        for (int[] ints : sandPer) {
            if (direct == 0) {
                sandX = tornado[0] + ints[0];
                sandY = tornado[1] + ints[1];
            } else if (direct == 1) {
                sandX = tornado[0] - ints[1];
                sandY = tornado[1] + ints[0];
            } else if (direct == 2) {
                sandX = tornado[0] + ints[0];
                sandY = tornado[1] - ints[1];
            } else {
                sandX = tornado[0] + ints[1];
                sandY = tornado[1] + ints[0];
            }
            sand = arr[tornado[0]][tornado[1]] * ints[2] / 100;
            total += sand;

            if (sandX < 0 || sandY < 0 || sandX >= N || sandY >= N) {
                answer += sand;
            } else {
                arr[sandX][sandY] += sand;
            }
        }
        if (tornado[0] + d[direct][0] < 0 || tornado[1] + d[direct][1] < 0 || tornado[0] + d[direct][0] >= N || tornado[1] + d[direct][1] >= N) {
            answer += (arr[tornado[0]][tornado[1]] - total);
        } else {
            arr[tornado[0] + d[direct][0]][tornado[1] + d[direct][1]] += (arr[tornado[0]][tornado[1]] - total);
        }
        arr[tornado[0]][tornado[1]] = 0;
    }
}
