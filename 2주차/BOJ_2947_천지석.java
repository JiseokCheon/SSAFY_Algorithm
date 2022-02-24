package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[5];
        int temp = 0;
        boolean check = false;  // 1,2,3,4,5 순서 맞는지 확인

        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 순서 맞지 않다면 반복
        while (!check) {
            check = true;
            for (int i = 0; i < 4; i++) {
                // 현재 수가 다음 수보다 크면 순서 바꿈
                if (arr[i] > arr[i + 1]) {
                    temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;

                    // 변경되었으면 출력
                    for (int j : arr) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                }

                // 현재 값과 인덱스 + 1 이 동일하지 않으면 false 로 바꿔서 순서 맞지 않다고 알림
                if (arr[i] != i + 1) {
                    check = false;
                }
            }
            // 반복문 끝나고도 check 가 true 면 1,2,3,4,5 순서가 맞아서 반복문 종료
        }
    }
}

