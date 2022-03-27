package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pqRight = new PriorityQueue<>(); // 중간보다 큰쪽(오른쪽)
        PriorityQueue<Integer> pqLeft = new PriorityQueue<>(Collections.reverseOrder()); // 중간보다 작은쪽(왼쪽)

        // 큐 빈거 고려하지 않기 위해 추가
        pqRight.add(100000);
        pqLeft.add(-100000);

        for (int i = 1; i <= N; i++) {
            int x = Integer.parseInt(br.readLine());

            // 양쪽 사이즈가 같으면 왼쪽에 추가
            if (pqLeft.size() == pqRight.size()) {
                if (pqRight.peek() >= x) {  // 중간에서 오른쪽 수보다 작으면 왼쪽에 추가
                    pqLeft.add(x);
                } else {                    // 크면 오르쪽에 추가 후 오르쪽 수 왼쪽으로 옮김
                    pqRight.add(x);
                    pqLeft.add(pqRight.poll());
                }
            } else {        // 양쪽 사이즈가 다르면 (왼쪽이 1만큼 김)
                if (pqRight.peek() >= x) {  // 중간에서 오른쪽 수보다 작으면 왼쪽 추가 후 왼쪽 수 오른쪽으로 옮김
                    pqLeft.add(x);
                    pqRight.add(pqLeft.poll());
                } else {                    // 크면 오르쪽에 추가
                    pqRight.add(x);
                }
            }
            sb.append(pqLeft.peek()).append("\n");  // 중간 수 출력
        }
        System.out.println(sb);
    }
}

