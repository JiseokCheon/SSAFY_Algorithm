package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int[][] direct = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};    // 시작 방향
        int[][] arr = new int[101][101];    // 격자
        int count = 0;                      // 정사각형 갯수
        ArrayList<int[]> list = null;       // 드래곤 커브 좌표 담을 리스트

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();

            // 0세대
            list.add(new int[]{y, x});
            list.add(new int[]{y + direct[d][0], x + direct[d][1]});
            arr[y][x]++;
            arr[y + direct[d][0]][x + direct[d][1]]++;

            for (int j = 0; j < g; j++) {   // 세대 만큼 반복
                int size = list.size();     // 현재 세대 사이즈
                for (int k = size - 2; k >= 0; k--) {
                    int[] node = rotate(list.get(size - 1), list.get(k));   // 끝점 기준으로 회전
                    arr[node[0]][node[1]]++;
                    list.add(node);
                }
            }
        }

        // 정사각형 갯수 카운트
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (arr[i][j] > 0 && arr[i + 1][j] > 0 && arr[i][j + 1] > 0 && arr[i + 1][j + 1] > 0) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    // 90도 회전
    static int[] rotate(int[] a, int[] b) {
        return new int[]{(a[0] - (a[1] - b[1])), (a[1] + (a[0] - b[0]))};
    }
}

