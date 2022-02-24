package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];   // 처음 주어진 카드 순서
        int[] P = new int[N];   // 어떤 플레이어에게 가야하는지에 대한 순서
        int[] card, temp;   // card = 현재 카드 상태, temp = 다음 카드 상태를 나타내기 위해 필요한 배열
        boolean check;      // 카드가 맞게 섞였는지 확인용
        HashMap<Integer, Integer> map = new HashMap<>();    // 각 카드가 어떤 플레이어에게 가야하는지 map 으로 저장
        int count = -1;     // 섞는 횟수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
            map.put(S[i], P[i]);    // 카드가 어떤 플레이어게 가는지 저장
        }

        card = S.clone();   // 현재 카드를 처음 카드로 업데이트
        temp = new int[N];

        while (true) {
            count++;

            check = true;

            // 카드가 해당 플레이어에게 알맞게 가는지 확인
            for (int i = 0; i < N; i++) {
                if (map.get(card[i]) != i % 3) {
                    check = false;  // 알맞게 가지 않으면 false
                    break;
                }
            }

            // 알맞게 갔다면 종료
            if (check) {
                break;
            }

            // 카드를 섞음
            for (int i = 0; i < N; i++) {
                temp[S[i]] = card[i];
            }

            card = temp.clone();    // 섞은 카드를 현재 카드 상태에 업데이트

            // 처음에 주어진 카드와 비교해서 같으면 가능하지 않다고 판단 -> 반복문 종료
            int x = 0;

            for (int i = 0; i < N; i++) {
                if (S[i] == card[i]) {
                    x++;
                }
            }

            if (x == N) {
                count = -1;
                break;
            }
        }
        System.out.println(count);
    }

}



