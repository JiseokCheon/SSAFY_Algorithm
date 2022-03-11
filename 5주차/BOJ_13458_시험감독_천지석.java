package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];     // 시험장마다 응시자 수

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long count = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = arr[i] > B ? arr[i] - B : 0;   // 총감독관이 감시할 수 있는 학생 수 계산
            count += 1 + (arr[i] / C + (arr[i] % C > 0 ? 1 : 0));   // +1 총감독관, 부감독관이 필요한 수 계산
        }
        System.out.println(count);
    }
}
