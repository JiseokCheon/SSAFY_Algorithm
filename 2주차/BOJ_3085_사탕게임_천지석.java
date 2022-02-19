package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[N][N];          // 보드
        ArrayList<int[]> list = new ArrayList<>();  // 교환할 사탕 쌍 위치 (0,1) (2,3)

        int max = 0;            // 먹을 수 있는 사탕의 최대 개수
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        // 교환할 사탕 쌍 찾아줌
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N - 1; j++) {
                if (arr[i][j] != arr[i][j + 1]) {       // 가로 방향 확인
                    list.add(new int[]{i, j, i, j + 1});
                }
                if (arr[j][i] != arr[j + 1][i]) {       // 세로 방향 확인
                    list.add(new int[]{j, i, j + 1, i});
                }
            }
        }

        // 교환할 사탕 쌍의 개수만큼 반복
        for (int k = 0; k < list.size(); k++) {
            int[] changeIndex = list.get(k);    // 교환할 사탕 쌍

            // 사탕 교환
            char temp = arr[changeIndex[0]][changeIndex[1]];
            arr[changeIndex[0]][changeIndex[1]] = arr[changeIndex[2]][changeIndex[3]];
            arr[changeIndex[2]][changeIndex[3]] = temp;

            for (int i = 0; i < N; i++) {
                int count1 = 1;
                int count2 = 1;
                for (int j = 1; j < N; j++) {
                    // 열에서 모두 같은 색으로 이루어진 가장 긴 연속부분 찾음
                    if (arr[i][j] == arr[i][j - 1]) {   // 이전 사탕과 같으면 +1
                        count1++;
                    } else {                            // 이전 사탕과 다르면 갯수 최댓값과 비교후 1로 변경
                        max = Math.max(count1, max);
                        count1 = 1;
                    }

                    // 행에서 모두 같은 색으로 이루어진 가장 긴 연속부분 찾음
                    if (arr[j][i] == arr[j - 1][i]) {   // 이전 사탕과 같으면 +1
                        count2++;
                    } else {                            // 이전 사탕과 다르면 갯수 최댓값과 비교후 1로 변경
                        max = Math.max(count2, max);
                        count2 = 1;
                    }
                }
                max = Math.max(max, Math.max(count1, count2));  // 최댓값 확인
            }

            // 사탕 원래대로
            temp = arr[changeIndex[0]][changeIndex[1]];
            arr[changeIndex[0]][changeIndex[1]] = arr[changeIndex[2]][changeIndex[3]];
            arr[changeIndex[2]][changeIndex[3]] = temp;
        }
        System.out.println(max);
    }
}