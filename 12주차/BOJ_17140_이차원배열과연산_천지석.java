package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int countR, countC, r, c, k;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[100][100];

        countR = countC = 3;

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (time <= 100) {
            if (arr[r - 1][c - 1] == k) {
                break;
            }
            time++;
            int count = 0;

            if (countR >= countC) {
                for (int i = 0; i < countR; i++) {
                    count = Math.max(func(i, 0), count);
                }
                countC = count;
            } else {
                for (int i = 0; i < countC; i++) {
                    count = Math.max(func(i, 1), count);
                }
                countR = count;
            }
        }

        System.out.println(time > 100 ? -1 : time);
    }

    // 행 또는 열 최대 크기 반환
    static int func(int x, int y) {
        int[][] temp = new int[101][2]; // 숫자별 갯수 저장

        for (int i = 0; i < 100; i++) {
            int a = 0;
            if (y == 0) {
                a = arr[x][i];
                arr[x][i] = 0;  // 숫자 counting 했으면 0으로 만들어서 제거
            } else {
                a = arr[i][x];
                arr[i][x] = 0;
            }
            temp[a][0] = a;
            temp[a][1]++;
        }

        Arrays.sort(temp, (o1, o2) -> o1[1] - o2[1]);   // 숫자 등장횟수 순서대로 정렬

        int i = 0;
        for (int index = 0; index <= 100; index++) {
            if (i >= 50) {
                break;
            }
            if (temp[index][0] == 0) {
                continue;
            }
            if (y == 0) {
                arr[x][2 * i] = temp[index][0];
                arr[x][2 * i + 1] = temp[index][1];
            } else {
                arr[2 * i][x] = temp[index][0];
                arr[2 * i + 1][x] = temp[index][1];
            }
            i++;
        }
        return 2 * i;
    }
}