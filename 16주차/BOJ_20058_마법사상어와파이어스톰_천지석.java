import java.io.*;
import java.util.*;

public class Main {

    static int[][] arr;
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static boolean[][] visited;
    static int size, answer, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        size = (int) Math.pow(2, N);
        arr = new int[size][size];
        visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            fireStorm(Integer.parseInt(st.nextToken()));
            iceMelt();
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                count += arr[i][j];
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    answer = Math.max(answer, arr[i][j] == 0 ? iceCheck(i, j) - 1 : iceCheck(i, j));
                }
            }
        }
        System.out.println(count);
        System.out.println(answer);
    }

    static void fireStorm(int L) {
        int x = (int) Math.pow(2, L);

        for (int i = 0; i < size; ) {
            for (int j = 0; j < size; ) {
                rotate(i, j, x);
                j += x;
            }
            i += x;
        }

    }

    static void rotate(int a, int b, int x) {
        int[][] temp = new int[x][x];
        int u = 0;
        int v = 0;

        for (int j = b; j < b + x; j++) {
            v = 0;
            for (int i = a + x - 1; i >= a; i--) {
                temp[u][v++] = arr[i][j];
            }
            u++;
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < x; j++) {
                arr[a + i][b + j] = temp[i][j];
            }
        }
    }

    static void iceMelt() {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (arr[i][j] <= 0) {
                    continue;
                }
                int count = 0;

                for (int direct = 0; direct < 4; direct++) {
                    int nextX = i + d[direct][0];
                    int nextY = j + d[direct][1];

                    if (nextX >= 0 && nextY >= 0 && nextX < size && nextY < size && arr[nextX][nextY] > 0) {
                        count++;
                    }
                }

                if (count < 3) {
                    q.add(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] node = q.poll();
            arr[node[0]][node[1]]--;
        }
    }


    static int iceCheck(int x, int y) {
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (nextX >= 0 && nextY >= 0 && nextX < size && nextY < size && arr[nextX][nextY] > 0 && !visited[nextX][nextY]) {
                visited[nextX][nextY] = true;
                count += iceCheck(nextX, nextY);
            }
        }
        return count;
    }
}
