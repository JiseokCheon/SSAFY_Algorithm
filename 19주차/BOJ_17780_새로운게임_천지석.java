package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] d = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        int[][] board = new int[N][N];
        List<Integer>[][] lists = new ArrayList[N][N];
        int[][] horses = new int[K][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                lists[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            horses[i][0] = Integer.parseInt(st.nextToken()) - 1;
            horses[i][1] = Integer.parseInt(st.nextToken()) - 1;
            horses[i][2] = Integer.parseInt(st.nextToken()) - 1;
            lists[horses[i][0]][horses[i][1]].add(i);
        }

        int turn = 0;

        outer:
        while (++turn <= 1000) {
            for (int j = 0; j < horses.length; j++) {
                int[] horse = horses[j];
                int curX = horse[0];
                int curY = horse[1];
                int nextX = curX + d[horse[2]][0];
                int nextY = curY + d[horse[2]][1];

                if (lists[horse[0]][horse[1]].get(0) != j) {
                    continue;
                }

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || board[nextX][nextY] == 2) {
                    horse[2] = horse[2] % 2 == 1 ? horse[2] - 1 : horse[2] + 1;
                    nextX = curX + d[horse[2]][0];
                    nextY = curY + d[horse[2]][1];
                    if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || board[nextX][nextY] == 2) {
                        continue;
                    }
                }
                List<Integer> temp = lists[curX][curY].subList(0, lists[curX][curY].size());

                if (board[nextX][nextY] == 1) {
                    Collections.reverse(temp);
                }
                lists[nextX][nextY].addAll(temp);

                for (int num : lists[curX][curY].subList(0, lists[curX][curY].size())) {
                    horses[num][0] = nextX;
                    horses[num][1] = nextY;
                }

                lists[curX][curY].subList(0, lists[curX][curY].size()).clear();


                if (lists[nextX][nextY].size() >= 4) {
                    break outer;
                }
            }
        }
        System.out.println(turn == 1001 ? -1 : turn);
    }
}
