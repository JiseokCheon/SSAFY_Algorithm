import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static int[][] sharks, arr;
    static int R, C, M, size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        size = 0;

        sharks = new int[M + 1][6];
        arr = new int[R][C];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            sharks[i][0] = Integer.parseInt(st.nextToken()) - 1;    // r
            sharks[i][1] = Integer.parseInt(st.nextToken()) - 1;    // c
            sharks[i][2] = Integer.parseInt(st.nextToken());    // s 속력
            int d = Integer.parseInt(st.nextToken()) - 1;

            sharks[i][3] = d == 1 ? 2 : (d == 2 ? 1 : d);    // d 이동 방향
            sharks[i][2] = sharks[i][2] % (sharks[i][3] % 2 == 1 ? 2 * C - 2 : 2 * R - 2);

            sharks[i][4] = Integer.parseInt(st.nextToken());    // z 크기
            sharks[i][5] = 1;

            arr[sharks[i][0]][sharks[i][1]] = i;
        }

        for (int i = 0; i < C; i++) {
            int r = 0;
            while (r < R) {
                if (arr[r][i] != 0) {
                    sharks[arr[r][i]][5] = -1;
                    size += sharks[arr[r][i]][4];
                    break;
                }
                r++;
            }

            arr = new int[R][C];

            for (int j = 1; j <= M; j++) {
                if (sharks[j][5] == 1)
                    move(sharks[j], j);
            }
        }
        System.out.println(size);
    }

    static void move(int[] shark, int index) {
        int nextX = shark[0];
        int nextY = shark[1];
        int sec = 0;

        while (sec++ < shark[2]) {
            nextX += d[shark[3]][0];
            nextY += d[shark[3]][1];

            if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C) {
                shark[3] = (shark[3] + 2) % 4;
                nextX += 2 * d[shark[3]][0];
                nextY += 2 * d[shark[3]][1];
            }
        }
        shark[0] = nextX;
        shark[1] = nextY;

        if (arr[nextX][nextY] == 0) {
            arr[nextX][nextY] = index;
        } else {
            if (sharks[arr[nextX][nextY]][4] < shark[4]) {
                sharks[arr[nextX][nextY]][5] = -1;
                arr[nextX][nextY] = index;
            } else {
                sharks[index][5] = -1;
            }
        }
    }
}
