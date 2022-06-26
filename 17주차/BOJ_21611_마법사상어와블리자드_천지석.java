package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] arr;
    static int[] count = new int[3];
    static int[][] direct = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] moveDirect = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static List<Integer> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            blizzard(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
            bomb();
            group();
        }

        System.out.println(count[0] + count[1] * 2 + count[2] * 3);
    }

    static void blizzard(int d, int s) {
        for (int i = 1; i <= s; i++) {
            arr[N / 2 + direct[d][0] * i][N / 2 + direct[d][1] * i] = 0;
        }

        list = new ArrayList<>();

        int x = N / 2;
        int y = N / 2;
        int size = 1;
        d = 0;

        outer:
        while (true) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < size; i++) {
                    x += moveDirect[d][0];
                    y += moveDirect[d][1];
                    if (x < 0 || x >= N || y < 0 || y >= N) {
                        break outer;
                    }
                    if (arr[x][y] != 0) {
                        list.add(arr[x][y]);
                    }
                }
                d = (d + 1) % 4;
            }
            size++;
        }
        list.add(-1);
    }

    static void bomb() {
        int cnt = 0;
        int curNum = -1;
        boolean check = false;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 0) {
                continue;
            }
            if (curNum != list.get(i)) {
                if (cnt >= 4) {
                    int index = i - 1;
                    count[curNum - 1] += cnt;

                    while (cnt > 0) {
                        if (list.get(index) != curNum) {
                            index--;
                        } else {
                            list.set(index--, 0);
                            cnt--;
                        }
                    }
                    check = true;
                }
                curNum = list.get(i);
                cnt = 1;
            } else {
                cnt++;
            }
            if (i == list.size() - 1 && check) {
                i = -1;
                check = false;
            }
        }
    }

    static void group() {
        List<Integer> findList = new ArrayList<>();
        int cnt = 0;
        int curNum = 0;

        for (int i : list) {
            if (i == 0) {
                continue;
            }
            if (curNum != i) {
                findList.add(cnt);
                findList.add(curNum);
                cnt = 1;
                curNum = i;
            } else {
                cnt++;
            }
        }

        arr = new int[N][N];

        int x = N / 2;
        int y = N / 2;
        int size = 1;
        int d = 0;
        int index = 2;

        outer:
        while (true) {
            for (int k = 0; k < 2; k++) {
                for (int i = 0; i < size; i++) {
                    x += moveDirect[d][0];
                    y += moveDirect[d][1];
                    if (x < 0 || x >= N || y < 0 || y >= N || index >= findList.size()) {
                        break outer;
                    }
                    arr[x][y] = findList.get(index++);
                }

                d = (d + 1) % 4;
            }
            size++;
        }
    }
}

