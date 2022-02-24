package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int dasom = Integer.parseInt(br.readLine());

        for (int i = 1; i < N; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }
        int x;  // 다솜이에게 한표 줄 후보의 득표수
        int count = 0;  // 다솜이가 매수해야하는 사람 수

        while (!pq.isEmpty()) {
            x = pq.poll();  // 득표수가 가장 많은 후보
            if (dasom > x) {    // 가장 많은 득표수를 가진 후보보다 다솜이가 더 득표수가 많으면 종료
                break;
            }
            count++;    // 매수한 사람 +1
            dasom++;    // 다솜이 득표수 +1
            pq.add(x - 1);  // 득표수 가장 많은 후보 -1
        }

        System.out.println(count);
    }
}