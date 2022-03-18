package com.company;

import java.io.*;
import java.util.*;


public class Main {
    // 방향 : 아래, 왼쪽, 위, 오른쪽
    static int[][] d = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        boolean[][][] visited = new boolean[M][N][4];   // 각 좌표의 방향마다 방문 확인
        int[][] arr = new int[M][N];
        int[] start, end;   // 출발좌표, 목적좌표 [세로, 가로, 방향, 명령 횟수]

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        start = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0};
        st = new StringTokenizer(br.readLine());
        end = new int[]{Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()), 0};

        // 방향 설정
        start[2] = findDir(start[2]);
        end[2] = findDir(end[2]);

        // 명령 횟수 작은 순으로 정렬된 우선순위 큐
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);

        pq.add(start);
        visited[start[0]][start[1]][start[2]] = true;

        while (!pq.isEmpty()) {
            int[] robot = pq.poll();

            // 목적 좌표에 도달하면 종료
            if (robot[0] == end[0] && robot[1] == end[1] && robot[2] == end[2]) {
                System.out.println(robot[3]);
                break;
            }

            // 왼쪽으로 90도
            if (!visited[robot[0]][robot[1]][(robot[2] + 1) % 4]) {
                pq.add(new int[]{robot[0], robot[1], (robot[2] + 1) % 4, robot[3] + 1});
                visited[robot[0]][robot[1]][(robot[2] + 1) % 4] = true;
            }

            // 오른쪽으로 90도
            if (!visited[robot[0]][robot[1]][(robot[2] + 3) % 4]) {
                pq.add(new int[]{robot[0], robot[1], (robot[2] + 3) % 4, robot[3] + 1});
                visited[robot[0]][robot[1]][(robot[2] + 3) % 4] = true;
            }

            int nextX = robot[0];
            int nextY = robot[1];

            // 향하는 방향으로 Go 움직임 처리
            int x = 0;
            while (x < 3) {
                nextX += d[robot[2]][0];
                nextY += d[robot[2]][1];

                if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || arr[nextX][nextY] == 1) {
                    break;
                }

                if (!visited[nextX][nextY][robot[2]]) {
                    pq.add(new int[]{nextX, nextY, robot[2], robot[3] + 1});
                    visited[nextX][nextY][robot[2]] = true;
                }
                x++;
            }
        }
    }

    static int findDir(int x) {
        if (x == 1) {
            return 3;
        }
        if (x == 2) {
            return 1;
        }
        if (x == 3) {
            return 0;
        }
        return 2;
    }
}



