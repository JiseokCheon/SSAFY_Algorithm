package com.company;

import java.io.*;
import java.util.*;


public class Main {

    static int[][] d = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    static int[][] arr, winter;
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];    // 현재 양분
        winter = new int[N + 1][N + 1]; // 겨울에 추가되는 땅의 양분

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                winter[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = 5;
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);   // 나무

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        // K년 반복
        for (int i = 0; i < K; i++) {
            pq = func(pq);
        }

        System.out.println(pq.size());
    }

    static PriorityQueue<int[]> func(PriorityQueue<int[]> pq) {

        PriorityQueue<int[]> spring = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);   // 양분 먹고 나이 먹은 나무
        List<int[]> summer = new ArrayList<>(); // 죽어서 양분이 될 나무
        List<int[]> fall = new ArrayList<>();   // 번식할 나무

        // 봄
        while (!pq.isEmpty()) {
            int[] node = pq.poll();

            if (arr[node[0]][node[1]] >= node[2]) {
                arr[node[0]][node[1]] -= node[2];
                spring.add(new int[]{node[0], node[1], node[2] + 1});
                if ((node[2] + 1) % 5 == 0) {
                    fall.add(new int[]{node[0], node[1]});
                }
            } else {
                summer.add(new int[]{node[0], node[1], node[2]});
            }
        }

        // 여름
        for (int[] i : summer) {
            arr[i[0]][i[1]] += i[2] / 2;
        }

        // 가을
        for (int[] i : fall) {
            for (int j = 0; j < 8; j++) {
                int nextR = i[0] + d[j][0];
                int nextC = i[1] + d[j][1];

                if (nextR > 0 && nextC > 0 && nextR <= N && nextC <= N) {
                    spring.add(new int[]{nextR, nextC, 1});
                }
            }
        }

        // 겨울
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                arr[i][j] += winter[i][j];
            }
        }

        return spring;
    }
}

