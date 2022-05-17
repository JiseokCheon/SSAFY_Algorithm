import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{1, 0, 0}, {-1, 0, 0}, {0, 1, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
    static int N, M, H;
    static int[][][] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[H][N][M];
        Queue<int[]> q = new LinkedList<>();

        int total = 0;

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    arr[h][n][m] = Integer.parseInt(st.nextToken());
                    if (arr[h][n][m] == 1) {
                        q.add(new int[]{h, n, m, 0});
                    }
                    if (arr[h][n][m] > -1) {
                        total++;
                    }
                }
            }
        }
        int day = -1;
        int count = 0;

        while (!q.isEmpty()) {
            int[] tomato = q.poll();
            count++;
            day = tomato[3];

            for (int i = 0; i < 6; i++) {
                int nextH = tomato[0] + d[i][0];
                int nextN = tomato[1] + d[i][1];
                int nextM = tomato[2] + d[i][2];
                if (nextH >= 0 && nextN >= 0 && nextM >= 0 && nextH < H && nextM < M && nextN < N && arr[nextH][nextN][nextM] == 0) {
                    arr[nextH][nextN][nextM] = 1;
                    q.add(new int[]{nextH, nextN, nextM, tomato[3] + 1});
                }
            }
        }

        System.out.println(count == total ? day : -1);
    }
}
