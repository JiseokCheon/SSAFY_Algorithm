import java.io.*;
import java.util.*;

public class Main {
    static List[] dice = {new LinkedList<>(List.of(2, 1, 5, 6)), new LinkedList<>(List.of(4, 1, 3, 6))};
    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static boolean[][] visited;
    static int[][] arr;
    static int N, M, answer;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        int direct = 0;
        int[] cur = {0, 0};

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < K; i++) {
            cur[0] += d[direct][0];
            cur[1] += d[direct][1];

            if (cur[0] < 0 || cur[1] < 0 || cur[0] >= N || cur[1] >= M) {
                cur[0] -= 2 * d[direct][0];
                cur[1] -= 2 * d[direct][1];
                direct = (direct + 2) % 4;
            }

            rotate(direct);
            if ((int) dice[0].get(3) > arr[cur[0]][cur[1]]) {
                direct = (direct + 1) % 4;
            } else if ((int) dice[0].get(3) < arr[cur[0]][cur[1]]) {
                direct = (direct + 3) % 4;
            }
            visited = new boolean[N][M];
            dfs(cur[0], cur[1], arr[cur[0]][cur[1]]);
        }
        System.out.println(answer);
    }

    static void dfs(int x, int y, int num) {
        visited[x][y] = true;
        answer += arr[x][y];

        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && !visited[nextX][nextY] && arr[nextX][nextY] == num) {
                dfs(nextX, nextY, num);
            }
        }
    }


    // 동남서북
    static void rotate(int d) {
        if (d == 0) {
            dice[1].add(0, dice[1].remove(3));
        } else if (d == 1) {
            dice[0].add(0, dice[0].remove(3));
        } else if (d == 2) {
            dice[1].add(dice[1].remove(0));
        } else {
            dice[0].add(dice[0].remove(0));
        }

        if (d % 2 == 0) {
            dice[0].set(1, dice[1].get(1));
            dice[0].set(3, dice[1].get(3));
        } else {
            dice[1].set(1, dice[0].get(1));
            dice[1].set(3, dice[0].get(3));
        }
    }
}


