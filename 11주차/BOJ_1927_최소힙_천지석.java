package com.company;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            if (num == 0) {
                sb.append(pq.size() != 0 ? pq.poll() : 0).append("\n");
            }else{
                pq.add(num);
            }
        }

        System.out.println(sb);
    }
}

