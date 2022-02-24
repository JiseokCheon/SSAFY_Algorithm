package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String[] arr = new String[N];   // 원판
        int[] visited = new int[26];    // 알파벳 체크
        boolean check = false;      // 행운의 바퀴 체크
        int cur = 0;        // 현재 가르키는 글자 인덱스

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            cur = (cur + Integer.parseInt(st.nextToken())) % N; // 회전 후 가르키는 글자 인덱스
            String s = st.nextToken();              // 회전 후 가르키는 글자

            // 지금 가르키는 원판의 인덱스에 대한 정보가 없거나 지금 가르키는 글자와 기존 정보가 같으면
            if (arr[cur] == null || arr[cur].equals(s)) {
                arr[cur] = s;
                visited[(int) s.charAt(0) - 65] = 1;    // 알파벳 사용 처리
            } else if (!check) {    // 지금 가르키는 원판의 인덱스에 대한 정보가 존재하는데 s와 다르면 행운의 바퀴 불가
                check = true;
            }
        }

        // 마지막 가르키는 글자부터 순서대로 원판의 정보를 보고 출력할 내용 결정
        for (int i = 0; i < N; i++) {
            String s = arr[(N + cur - i) % N];
            // 글자의 정보가 존재하면
            if (s != null) {
                // 글자 출력
                sb.append(s);
                // 알파벳은 중복되어 나올 수 없어서 중복 체크
                // 지금 가르키는 알파벳의 갯수가 0이면 중복되어 사용되었다고 판단 -> 행운 바퀴 불가
                if (visited[(int) s.charAt(0) - 65] == 0) {
                    check = true;
                    break;
                }
                // 중복 체크를 위해 1 감소
                visited[(int) s.charAt(0) - 65]--;
            }
            // 글자에 대한 정보가 존재하지 않으면 ? 출력
            else {
                sb.append("?");
            }
        }

        System.out.println(check ? "!" : sb);
    }
}