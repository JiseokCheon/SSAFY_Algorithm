import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] shark;
    static int[][][] sharkD, arr;
    static int N, M, K, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N][2];     // shark, time
        shark = new int[M + 1][3]; // x, y, d
        sharkD = new int[M + 1][4][4];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j][0] = Integer.parseInt(st.nextToken());
                if (arr[i][j][0] > 0) {
                    shark[arr[i][j][0]][0] = i;
                    shark[arr[i][j][0]][1] = j;
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            shark[i][2] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int k = 1; k <= M; k++) {
            for (int i = 0; i < 4; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 4; j++) {
                    sharkD[k][i][j] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        int time = 0;
        while (time <= 1000) {
            time++;
            moveShark(time);
            if (M - count == 1) {
                break;
            }

        }
        System.out.println(time == 1001 ? -1 : time);
    }

    static void moveShark(int time) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);

        outer:
        for (int i = 1; i <= M; i++) {
            int x = shark[i][0];
            int y = shark[i][1];
            int direct = shark[i][2];
            if (x == -1) {
                continue;
            }

            for (int k = 0; k < 2; k++) {
                for (int j = 0; j < 4; j++) {
                    int nextDirect = sharkD[i][direct][j];
                    int nextX = x + d[nextDirect][0];
                    int nextY = y + d[nextDirect][1];
                    if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < N) {
                        if (k == 0 && (arr[nextX][nextY][0] == 0 || (arr[nextX][nextY][0] > 0 && time - arr[nextX][nextY][1] > K))
                                || k == 1 && arr[nextX][nextY][0] == i) {
                            pq.add(new int[]{nextX, nextY, nextDirect, i});
                            continue outer;
                        }
                    }
                }
            }
        }

        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            if (arr[node[0]][node[1]][1] == time) {
                shark[node[3]][0] = -1;
                count++;
                continue;
            }

            arr[node[0]][node[1]][0] = node[3];
            arr[node[0]][node[1]][1] = time;
            shark[node[3]][0] = node[0];
            shark[node[3]][1] = node[1];
            shark[node[3]][2] = node[2];
        }
    }
}
