package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        char[][] arr = new char[N][N];
        ArrayList<int[]> listB = new ArrayList<>();     // 초기 통나무
        ArrayList<int[]> listE = new ArrayList<>();     // 목적지
        boolean[][][] visited = new boolean[N][N][2];   // 방문 체크 ( 0 : 가로, 1: 세로)

        // 움직임 (U, D, L, R, T)
        int[][][] d = {{{-1, 0}, {-1, 0}, {-1, 0}}, {{1, 0}, {1, 0}, {1, 0}}, {{0, -1}, {0, -1}, {0, -1}},
                {{0, 1}, {0, 1}, {0, 1}}, {{-1, -1}, {0, 0}, {1, 1}}};
        int[][] rotation = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}};    // 회전 가능한지

        // 0과 1 입력
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = s.charAt(j);
                arr[i][j] = c == '1' ? '1' : '0';
                if (c == 'B') {
                    listB.add(new int[]{i, j}); // 통나무 좌표
                }
                if (c == 'E') {
                    listE.add(new int[]{i, j}); // 목적지 좌표
                }
            }
        }
        // 가로, 세로 확인 (좌표들의 가로방향 좌표가 같으면 0 (가로), 아니면 1(세로) )
        listB.add(new int[]{listB.get(0)[0] == listB.get(1)[0] ? 0 : 1, 0, 0}); // 가로세로, 움직임, 회전 횟수
        listE.add(new int[]{listE.get(0)[0] == listE.get(1)[0] ? 0 : 1});

        Queue<ArrayList<int[]>> q = new LinkedList<>();
        q.add(listB);
        visited[listB.get(1)[0]][listB.get(1)[1]][listB.get(3)[0]] = true;

        while (!q.isEmpty()) {
            ArrayList<int[]> list = q.poll();

            // 가운데 좌표랑 가로세로 맞는지 확인해서 같으면 중단
            if (listE.get(1)[0] == list.get(1)[0]
                    && listE.get(1)[1] == list.get(1)[1]
                    && listE.get(3)[0] == list.get(3)[0]) {
                answer = list.get(3)[1];
                break;
            }

            // 움직임
            outer:
            for (int i = 0; i < d.length; i++) {
                ArrayList<int[]> temp = new ArrayList<>();

                // 리스트 복사
                for (int j = 0; j < 4; j++) {
                    // 리스트 마지막이 원소 갯수 많음
                    if (j == 3) {
                        temp.add(new int[]{list.get(j)[0], list.get(j)[1], list.get(j)[2]});
                    } else {
                        temp.add(new int[]{list.get(j)[0], list.get(j)[1]});
                    }
                }

                // 이동
                for (int j = 0; j < 3; j++) {
                    // 회전 횟수 비교해서 처리
                    temp.get(j)[0] += list.get(3)[2] % 2 == 1 ? d[i][j][0] : d[i][2 - j][0];
                    temp.get(j)[1] += list.get(3)[2] % 2 == 1 ? d[i][j][1] : d[i][2 - j][1];
                    // 1에 걸리거나 벗어나면 continue
                    if (temp.get(j)[0] < 0 || temp.get(j)[0] >= N
                            || temp.get(j)[1] < 0 || temp.get(j)[1] >= N || arr[temp.get(j)[0]][temp.get(j)[1]] == '1') {
                        continue outer;
                    }
                }

                // 회전 하는 경우
                if (i == d.length - 1) {
                    temp.get(3)[0] = (temp.get(3)[0] + 1) % 2;  // 가로 세로 방향 전환
                    temp.get(3)[2]++;   // 회전 횟수 +1
                    // 회전 하는 경로에 1에 걸리면 continue
                    for (int j = 0; j < 4; j++) {
                        if (arr[temp.get(1)[0] + rotation[j][0]][temp.get(1)[1] + rotation[j][1]] == '1') {
                            continue outer;
                        }
                    }
                }

                // 이동한 가운데 좌표와 가로, 세로에 맞게 방문 한지 확인
                if (!visited[temp.get(1)[0]][temp.get(1)[1]][temp.get(3)[0]]) {
                    visited[temp.get(1)[0]][temp.get(1)[1]][temp.get(3)[0]] = true;
                    temp.get(3)[1]++;   // 이동 횟수 +1
                    q.add(temp);
                }
            }
        }
        System.out.println(answer);
    }
}
