package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        ArrayList<int[]> list = new ArrayList<>();  // 바이러스 좌표
        ArrayList<int[]> vir = new ArrayList<>();   // 빈칸 좌표

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 0) {
                    list.add(new int[]{i, j});
                } else if (arr[i][j] == 2) {
                    vir.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            arr[list.get(i)[0]][list.get(i)[1]] = 1;

            for (int j = i + 1; j < list.size(); j++) {
                arr[list.get(j)[0]][list.get(j)[1]] = 1;

                for (int k = j + 1; k < list.size(); k++) {
                    arr[list.get(k)[0]][list.get(k)[1]] = 1;

                    Queue<int[]> q = new LinkedList<>();
                    q.addAll(vir);
                    boolean[][] visited = new boolean[N][M];
                    int count = 3;  // 빈칸인 부분에서 벽이 된 갯수

                    while (!q.isEmpty()) {
                        int[] node = q.poll();

                        for (int u = 0; u < 4; u++) {
                            int nextX = node[0] + d[u][0];
                            int nextY = node[1] + d[u][1];

                            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] && arr[nextX][nextY] == 0) {
                                visited[nextX][nextY] = true;
                                count++;    // 바이러스 갯수 counting
                                q.add(new int[]{nextX, nextY});
                            }
                        }
                    }
                    answer = Math.max(answer, list.size() - count);

                    arr[list.get(k)[0]][list.get(k)[1]] = 0;
                }
                arr[list.get(j)[0]][list.get(j)[1]] = 0;
            }
            arr[list.get(i)[0]][list.get(i)[1]] = 0;
        }
        System.out.println(answer);
    }
}