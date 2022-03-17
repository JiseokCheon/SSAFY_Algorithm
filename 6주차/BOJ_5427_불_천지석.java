package com.company;

import java.io.*;
import java.util.*;
/*
불이 번지는 시간 저장 후
상근이가 빠져나가는 경로 찾음
 */
public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            int[] sang = {};    // 상근이 좌표
            int answer = -1;    // 탈출 시간
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            int[][] arr = new int[h + 2][w + 2];    // 건물
            boolean[][] visited = new boolean[h + 2][w + 2];
            Queue<int[]> q = new LinkedList<>();    // bfs 수행할 큐

            // 모두 -1로 설정해서 바깥인지 확인
            for (int i = 0; i < h + 2; i++) {
                Arrays.fill(arr[i], -1);
            }


            for (int i = 1; i <= h; i++) {
                String s = br.readLine();
                for (int j = 1; j <= w; j++) {
                    arr[i][j] = Integer.MAX_VALUE;  // 건물 내부
                    char c = s.charAt(j - 1);
                    if (c == '#') {
                        visited[i][j] = true;   // 벽 방문 처리
                        arr[i][j] = -2;         // 벽은 -2로 설정
                    } else if (c == '*') {
                        q.add(new int[]{i, j, 0}); // 불을 큐에 저장
                        visited[i][j] = true;   // 불 방문 처리
                    } else if (c == '@') {
                        sang = new int[]{i, j, 0};  // 상근이 위치
                    }
                }
            }

            // 불 번지는 bfs
            while (!q.isEmpty()) {
                int[] fire = q.poll();

                // 위치마다 불이 번지는 시간 저장
                arr[fire[0]][fire[1]] = Math.min(arr[fire[0]][fire[1]], fire[2]);

                for (int i = 0; i < 4; i++) {
                    int nextH = fire[0] + d[i][0];
                    int nextW = fire[1] + d[i][1];

                    if (nextH > 0 && nextW > 0 && nextH <= h && nextW <= w && !visited[nextH][nextW] && arr[nextH][nextW] != -1) {
                        q.add(new int[]{nextH, nextW, fire[2] + 1});
                        visited[nextH][nextW] = true;
                    }
                }
            }

            q.add(sang);
            visited = new boolean[h + 2][w + 2];    // 방문 처리 배열 초기화
            visited[sang[0]][sang[1]] = true;

            // 상근이 탈출 경로 찾는 bfs
            while (!q.isEmpty()) {
                int[] man = q.poll();
                visited[man[0]][man[1]] = true;

                // 바깥으로 나오면 answer 변경
                if (arr[man[0]][man[1]] == -1) {
                    answer = man[2];
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nextH = man[0] + d[i][0];
                    int nextW = man[1] + d[i][1];

                    // 방문 X, 벽 X, 불이 아직 번지지 않은곳
                    if (nextH >= 0 && nextW >= 0 && nextH <= h + 1 && nextW <= w + 1 && !visited[nextH][nextW]
                            && arr[nextH][nextW] != -2 && (arr[nextH][nextW] == -1 || arr[nextH][nextW] > man[2] + 1)) {
                        q.add(new int[]{nextH, nextW, man[2] + 1});
                        visited[nextH][nextW] = true;
                    }
                }
            }

            System.out.println(answer == -1 ? "IMPOSSIBLE": answer);
        }
    }
}



