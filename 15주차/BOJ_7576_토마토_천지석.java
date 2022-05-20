import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int day = 0;
        int total = 0;
        int[][] arr = new int[N][M];
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    q.add(new int[]{i, j, 0});
                }
                if (arr[i][j] != -1) {
                    total++;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] tomato = q.poll();
            day = tomato[2];
            total--;
            for (int i = 0; i < 4; i++) {
                int nextX = tomato[0] + d[i][0];
                int nextY = tomato[1] + d[i][1];

                if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && arr[nextX][nextY] == 0) {
                    arr[nextX][nextY] = 1;
                    q.add(new int[]{nextX, nextY, tomato[2] + 1});
                }
            }
        }
        System.out.println(total == 0 ? day : -1);
    }
}
