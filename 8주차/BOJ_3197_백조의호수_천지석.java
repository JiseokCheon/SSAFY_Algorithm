package com.company;

import java.io.*;
import java.util.*;


/**
 * 먼저 호수가 물로 변하는 시간을 저장
 * 백조가 최소로 움직이는 날 구함
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] birdA = new int[3];
        int[] birdB = new int[2];
        int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        int[][] arr = new int[R][C];
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = s.charAt(j);
                if (c != 'X') {
                    q.add(new int[]{i, j, 0});
                    visited[i][j] = true;
                    if (c == 'L') {
                        if (birdA[0] == 0) {
                            birdA[0] = i;
                            birdA[1] = j;
                        } else {
                            birdB[0] = i;
                            birdB[1] = j;
                        }
                    }
                }
            }
        }

        // 호수가 물로 변하는 시간 찾음
        while (!q.isEmpty()) {
            int[] node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = d[i][0] + node[0];
                int nextY = d[i][1] + node[1];

                if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    arr[nextX][nextY] = node[2] + 1;
                    q.add(new int[]{nextX, nextY, node[2] + 1});
                }
            }
        }

        visited = new boolean[R][C];
        visited[birdA[0]][birdA[1]] = true;
        q.add(birdA);

        // 새가 만나는 시간 찾음
        while (!q.isEmpty()) {
            int[] node = q.poll();

            if (node[0] == birdB[0] && node[1] == birdB[1]) {
                System.out.println(node[2]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = d[i][0] + node[0];
                int nextY = d[i][1] + node[1];

                if (nextX >= 0 && nextY >= 0 && nextX < R && nextY < C && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY, Math.max(node[2], arr[nextX][nextY])});
                }
            }
        }
    }
}
