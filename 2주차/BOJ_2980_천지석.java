package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 신호등 개수
        int L = Integer.parseInt(st.nextToken());   // 도로 길이
        int time = -1;      // 시간
        int current = 0;    // 현재 위치
        int[][] arr = new int[L + 1][2];    // 도로 (arr[i][0] -> 빨간불 시간, arr[i][1] -> 초록불 시간)

        // 도로에 신호등 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());

            arr[D][0] = R;
            arr[D][1] = G;
        }

        // 현재 위치부터 끝까지 반복
        while (current < L) {
            time++;

            // 신호등이 없으면 지나감
            if (arr[current][0] == 0) {
                current++;
                continue;
            }
            // 현재 시간에서 신호등의 초록불과 빨간불의 합의 나머지로 현재 초록불인지 빨간불인지 계산
            int temp = time % (arr[current][0] + arr[current][1]);
            // 0 이거나 빨간불보다 크면 초록불
            if (temp > arr[current][0] || temp == 0) {
                current++;
            }
        }
        System.out.println(time);
    }
}

