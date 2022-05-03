package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    static int[][] horse, arr;
    static int N, K, answer;
    static boolean check;
    static ArrayList<Integer>[][] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N + 2][N + 2];
        horse = new int[K][3];
        list = new ArrayList[N + 1][N + 1];
        check = false;
        answer = -1;

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < N + 1; j++) {
                list[i][j] = new ArrayList<>();
            }
        }

        for (int[] i : arr) {
            Arrays.fill(i, 2);
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direct = Integer.parseInt(st.nextToken()) - 1;
            list[x][y].add(i);
            horse[i] = new int[]{x, y, direct};
        }

        outer:
        for (int i = 1; i <= 1000; i++) {
            for (int j = 0; j < K; j++) {
                moveHorse(j);
                if (check) {
                    answer = i;
                    break outer;
                }
            }
        }
        System.out.println(answer);
    }

    static void moveHorse(int i) {
        int nextX = horse[i][0] + d[horse[i][2]][0];
        int nextY = horse[i][1] + d[horse[i][2]][1];

        if (arr[nextX][nextY] == 2) {
            nextX = nextX - 2 * d[horse[i][2]][0];
            nextY = nextY - 2 * d[horse[i][2]][1];
            horse[i][2] = horse[i][2] >= 2 ? 2 + (horse[i][2] + 1) % 2 : (horse[i][2] + 1) % 2;

            if (arr[nextX][nextY] == 2) {
                return;
            }
        }

        ArrayList<Integer> temp = findHorse(list[horse[i][0]][horse[i][1]], i, arr[nextX][nextY]);

        for (Integer index : temp) {
            horse[index][0] = nextX;
            horse[index][1] = nextY;
        }

        list[nextX][nextY].addAll(temp);
        if (list[nextX][nextY].size() >= 4) {
            check = true;
        }
    }

    static ArrayList<Integer> findHorse(ArrayList<Integer> list, int index, int color) {
        ArrayList<Integer> temp = new ArrayList<>();
        boolean check = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == index) {
                check = true;
            }
            if (check) {
                if (color == 1) {
                    temp.add(0, list.get(i));
                } else {
                    temp.add(list.get(i));
                }
                list.remove(i--);
            }
        }

        return temp;
    }
}