package com.company;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());   // 사람 수
            int M = Integer.parseInt(st.nextToken());   // 붕어빵 생산 시간
            int K = Integer.parseInt(st.nextToken());   // 붕어빵 생산 개수
            int bread = 0;  // 현재 붕어빵 갯수
            int time = 0;   // 붕어빵 생산한 최근 시간
            boolean check = true;   // 제공 판별

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N]; // 사람들 도착 시간
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);   // 시간순서대로 정렬

            for (int i = 0; i < N; i++) {
                bread += ((arr[i] - time) / M * K);     // 최근 붕어빵 생산 시간 이후 사람이 도착할때 까지 생산 가능한 붕어빵 갯수 더함
                time = arr[i] / M * M;  // 최근 붕어빵 생산시간 업데이트
                if (bread < 1) {
                    check = false;  // 현재 붕어빵을 사람에게 제공할 수 없다면 false
                    break;
                }
                bread--;    // 붕어빵 1개 제공
            }
            System.out.println("#" + tc + " " + (check ? "Possible" : "Impossible"));
        }
    }
}