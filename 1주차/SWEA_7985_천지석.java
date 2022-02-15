package com.company;

import java.io.*;
import java.util.*;

public class Solution {
    static LinkedList<Integer> list;
    static int[] result;
    static int K, T, len;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            System.out.print("#" + tc + " ");
            K = Integer.parseInt(br.readLine());
            len = (int) Math.pow(2, K); // 트리 크기 (깊이가 K 이면 2 ^ K)
            list = new LinkedList<>();  // 중위 순위 읽은 순서대로 담을 리스트
            result = new int[len];      // 정점 위치마다 맞는 번호 담을 배열

            StringTokenizer st = new StringTokenizer(br.readLine());

            // 중위 순회 결과 저장
            for (int i = 1; i < len; i++) {
                list.add(Integer.parseInt(st.nextToken()));
            }

            // 루트를 시작으로 중위 순회
            dfs(1);

            // result 배열에서 트리 모양으로 출력
            int index = 1;
            for (int i = 0; i < K; i++) {
                for (int j = 0; j < (int) Math.pow(2, i); j++) {
                    System.out.print(result[index++] + " ");
                }
                System.out.println();
            }
        }
    }

    // i = 해당 위치 (루트 = 1, 왼쪽 자식 = i * 2, 오른쪽 자식 = i * 2 + 1)
    // 중위 순회
    static void dfs(int i) {
        if (i * 2 < len)    // 왼쪽 자식 먼저 탐색
            dfs(i * 2);

        result[i] = list.remove(0); //  현재 탐색할 위치에 값을 넣어줌 (중위 순회한 결과를 순서대로 동일하게 순서대로 넣어줌)

        if (i * 2 + 1 < len)    // 오른쪽 자식 탐색
            dfs(i * 2 + 1);

    }
}
