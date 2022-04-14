package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean[] visited = new boolean[100001];

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{N, 0});
        visited[N] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();

            if (node[0] == K) {
                System.out.println(node[1]);
                break;
            }

            if (node[0] - 1 >= 0 && !visited[node[0] - 1]) {
                q.add(new int[]{node[0] - 1, node[1] + 1});
                visited[node[0] - 1] = true;
            }
            if (node[0] + 1 <= 100000 && !visited[node[0] + 1]) {
                q.add(new int[]{node[0] + 1, node[1] + 1});
                visited[node[0] + 1] = true;
            }
            if (2 * node[0] <= 100000 && !visited[2 * node[0]]) {
                q.add(new int[]{2 * node[0], node[1] + 1});
                visited[2 * node[0]] = true;
            }
        }
    }
}