package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static char[][][] cube;     // 큐브
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());
        Map<Character, int[]> map = new HashMap<>();    // 큐브 돌리는 인덱스??
        // 첫번째 : 0 -> 위아래, 1 -> 앞뒤, 2-> 왼오
        // 두번째, 세번째 회전할 인덱스
        map.put('U', new int[]{0, 0, 1});
        map.put('D', new int[]{0, 3, 4});
        map.put('F', new int[]{1, 3, 4});
        map.put('B', new int[]{1, 0, 1});
        map.put('L', new int[]{2, 0, 1});
        map.put('R', new int[]{2, 3, 4});

        for (int tc = 0; tc < T; tc++) {
            cube = new char[5][5][5];
            cubeInit();

            int n = Integer.parseInt(br.readLine());
            String[] s = br.readLine().split(" ");

            for (int i = 0; i < n; i++) {
                char c = s[i].charAt(0);
                int d = s[i].charAt(1) == '+' ? 1 : -1;

                int[] arr = map.get(c);

                // 오른쪽, 아래, 뒤 방향은 회전 방향 반대로 바꿈
                if (arr[0] == 2 && arr[1] >= 3 || arr[0] == 0 && arr[1] >= 3 || arr[0] == 1 && arr[1] < 3) {
                    d = -d;
                }

                rotate(arr[0], arr[1], d);
                rotate(arr[0], arr[2], d);
            }
            printCube();
        }
        System.out.println(sb);
    }

    // 큐브 초기 상태
    static void cubeInit() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cube[0][i + 1][j + 1] = 'w';    // 위
                cube[4][i + 1][j + 1] = 'y';    // 아래
                cube[i + 1][0][j + 1] = 'o';    // 뒤
                cube[i + 1][4][j + 1] = 'r';    // 앞
                cube[i + 1][j + 1][0] = 'g';    // 왼
                cube[i + 1][j + 1][4] = 'b';    // 오
            }
        }
    }

    // 회전 x : 나눈 그룹, y : 회전할 인덱스, d: 회전 방향
    static void rotate(int x, int y, int d) {
        char[][] temp = new char[5][5];

        // 회전할 면 복사
        for (int i = 0; i < 5; i++) {
            if (x == 0) {
                temp[i] = cube[y][i].clone();
            } else if (x == 1) {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = cube[i][y][j];
                }
            } else {
                for (int j = 0; j < 5; j++) {
                    temp[i][j] = cube[i][j][y];
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (x == 0) {
                    if (d == 1)
                        cube[y][i][j] = temp[4 - j][i];
                    else
                        cube[y][i][j] = temp[j][4 - i];

                } else if (x == 1) {
                    if (d == 1)
                        cube[i][y][j] = temp[4 - j][i];
                    else
                        cube[i][y][j] = temp[j][4 - i];
                } else {
                    if (d == 1)
                        cube[i][j][y] = temp[4 - j][i];
                    else
                        cube[i][j][y] = temp[j][4 - i];
                }
            }
        }
    }

    static void printCube() {
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                sb.append(cube[0][i][j]);
            }
            sb.append('\n');
        }
    }
}

