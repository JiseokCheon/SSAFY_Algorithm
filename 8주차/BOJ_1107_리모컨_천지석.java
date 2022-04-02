package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] num = new boolean[10];
        boolean[] visited = new boolean[1000001]; // 채널 방문 처리

        if (M > 0)
            st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            num[Integer.parseInt(st.nextToken())] = true;
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{100, 0, 0});    // {시작채널, count, 증감버튼 클릭 체크}

        while (!q.isEmpty()) {
            int[] node = q.poll();

            if (node[0] == N) {
                System.out.println(node[1]);
                break;
            }

            if (node[2] == 0) {
                int x = node[0] == 100 && node[1] == 0 ? 0 : node[0] * 10;  // 처음만 0으로 나머지는 숫자 이전 숫자에 이어서
                for (int i = 0; i < 10; i++) {
                    if (!num[i] && x + i <= 1000000 && !visited[x + i]) {
                        visited[x + i] = true;
                        q.add(new int[]{x + i, node[1] + 1, 0});
                    }
                }
            }

            if (node[0] < N && !visited[node[0] + 1]) {
                q.add(new int[]{node[0] + 1, node[1] + 1, 1});
            }
            if (node[0] > N && !visited[node[0] - 1]) {
                q.add(new int[]{node[0] - 1, node[1] + 1, 1});
            }
        }
    }
}

