package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, answer;
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};  // 이동 방향
    static boolean[][][][] visited; // 방문 처리 (R, B 동시에 확인)
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M][N][M];
        answer = -1;
        arr = new char[N][M];
        // 현재 위치 입력받을 객체
        Location location = new Location(1, 0, 0, 0, 0);

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = s.charAt(j);
                // R 위치 입력
                if (arr[i][j] == 'R') {
                    arr[i][j] = '.';
                    location.RX = i;
                    location.RY = j;
                }
                // B 위치 입력
                else if (arr[i][j] == 'B') {
                    arr[i][j] = '.';
                    location.BX = i;
                    location.BY = j;
                }
            }
        }

        System.out.println(bfs(location));
    }

    static int bfs(Location location) {
        // 현재 R, B 방문
        visited[location.BX][location.BY][location.RX][location.RY] = true;
        Queue<Location> q = new LinkedList<>();
        q.add(location);

        while (!q.isEmpty()) {
            Location lo = q.poll();

            // 10 넘으면 종료
            if (lo.count > 10) {
                return -1;
            }

            for (int i = 0; i < 4; i++) {
                // 다음 위치
                int nextBX = lo.BX;
                int nextBY = lo.BY;
                int nextRX = lo.RX;
                int nextRY = lo.RY;
                // 구멍에 빠졌는지 확인
                boolean BCheck = false;
                boolean RCheck = false;

                // 움직임
                while (true) {
                    // 전에 구멍 빠지지 않았고 이동할 칸이 벽이 아니고 빨간 구슬이 없어야 됨
                    if (!BCheck && (nextBX + d[i][0] != nextRX || nextBY + d[i][1] != nextRY)
                            && arr[nextBX + d[i][0]][nextBY + d[i][1]] != '#') {
                        nextBX += d[i][0];
                        nextBY += d[i][1];
                        // 구멍에 빠지면 위치 -1, -1 로 바꿔서 없애줌
                        if (arr[nextBX][nextBY] == 'O') {
                            nextBX = -1;
                            nextBY = -1;
                            BCheck = true;
                        }
                        continue;
                    }
                    if (!RCheck && (nextRX + d[i][0] != nextBX || nextRY + d[i][1] != nextBY)
                            && arr[nextRX + d[i][0]][nextRY + d[i][1]] != '#') {
                        nextRX += d[i][0];
                        nextRY += d[i][1];
                        if (arr[nextRX][nextRY] == 'O') {
                            nextRX = -1;
                            nextRY = -1;
                            RCheck = true;
                        }
                        continue;
                    }
                    break;
                }
                // 파란 공이 빠지면 실패
                if (BCheck) {
                    continue;
                }
                // 파란 공이 빠지지 않고 빨간 공만 빠지면 성공
                if (RCheck) {
                    return lo.count;
                }

                // 방문 처리
                if (!visited[nextBX][nextBY][nextRX][nextRY]) {
                    visited[nextBX][nextBY][nextRX][nextRY] = true;
                    q.add(new Location(lo.count + 1, nextRX, nextRY, nextBX, nextBY));
                }
            }
        }
        return -1;
    }
}

// 움직임 횟수
// R, B 좌표
class Location {
    int count, RX, RY, BX, BY;

    public Location(int count, int RX, int RY, int BX, int BY) {
        this.count = count;
        this.RX = RX;
        this.RY = RY;
        this.BX = BX;
        this.BY = BY;
    }
}