package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static char[][] arr;
    static int R, C;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int count = C;      // 미네랄 갯수 (바닥에 한줄을 미네랄로 설정)
        int direct = 1;     // 막대 던질 방향

        ArrayList<int[]> list = null;   // 아래로 떨어질 미네랄 클러스터

        arr = new char[R + 1][C];
        Arrays.fill(arr[R], 'x');   // 바닥 한줄

        for (int i = 0; i < R; i++) {
            String s = br.readLine();

            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'x') {
                    count++;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = direct == 1 ? -1 : C;   // 던질 방향에 따라 시작 위치 설정

            while (true) {
                y += direct;    // 한칸씩 전진하면서 확인

                if (y < 0 || y >= C) {  // 끝에 도달하면 종료하고 방향 전환
                    direct = -direct;
                    break;

                } else if (arr[R - x][y] == 'x') {  // 미네랄 발견하면 미네랄 삭제 후 방향 전환
                    arr[R - x][y] = '.';
                    count--;
                    direct = -direct;

                    visited = new boolean[R + 1][C];

                    if (count == bfs(R, 0, true).size()) {  // 바닥과 붙어있는 미네랄 갯수 확인
                        break;
                    }

                    for (int j = 0; j < 4; j++) {   // 떨어질 미네랄 시작 위치 찾음
                        int nextR = d[j][0] + R - x;
                        int nextC = d[j][1] + y;

                        if (nextR >= 0 && nextC >= 0 && nextR < R && nextC < C
                                && !visited[nextR][nextC] && arr[nextR][nextC] == 'x') {

                            list = bfs(nextR, nextC, false);    // 떨어질 미네랄 좌표값
                            break;
                        }
                    }

                    int dis = findDistance(list);   // 떨어질 높이

                    for (int[] node : list) {   // 떨어진 위치로 옮김
                        arr[node[0] + dis - 1][node[1]] = 'x';
                    }

                    break;
                }
            }
        }

        // 출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    // 인접한 미네랄 찾음
    static ArrayList<int[]> bfs(int x, int y, boolean ch) {
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> list = new ArrayList<>(); // 인접한 미네랄들

        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            list.add(node); // 찾은 미네랄 넣음

            if (!ch) {  // ch로 떨어질 미네랄과 땅에 붙어있는 미네랄 확인
                arr[node[0]][node[1]] = '.';    // 떨어질 미네랄 일단 제거
            }

            for (int i = 0; i < 4; i++) {
                int nextR = node[0] + d[i][0];
                int nextC = node[1] + d[i][1];
                if (nextR >= 0 && nextC >= 0 && nextR <= R && nextC < C
                        && arr[nextR][nextC] == 'x' && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }

        return list;
    }

    // 떨어질 높이
    static int findDistance(ArrayList<int[]> list) {
        int dis = 100;

        for (int[] i : list) {
            int temp = 1;

            while (arr[i[0] + temp][i[1]] != 'x') {
                temp++;
            }
            dis = Math.min(temp, dis);
        }
        return dis;
    }
}

