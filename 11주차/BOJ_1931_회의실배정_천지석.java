package com.company;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) ->
        {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int time = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        while (!pq.isEmpty()) {
            int[] node = pq.poll();

            if (node[0] >= time) {
                time = node[1];
                count++;
            }
        }

        System.out.println(count);
    }
}
