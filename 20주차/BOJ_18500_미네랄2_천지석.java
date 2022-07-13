import java.io.*;
import java.util.*;

public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static char[][] arr;
    static int R, C;
    static boolean[][] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int count = C;
        int direct = 1;

        ArrayList<int[]> list = null;

        arr = new char[R + 1][C];
        Arrays.fill(arr[R], 'x');

        for (int i = 0; i < R; i++) {
            String s = br.readLine();

            for (int j = 0; j < C; j++) {
                arr[i][j] = s.charAt(j);
                if (arr[i][j] == 'x') {
                    count++;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken());
            int y = direct == 1 ? -1 : C;
            while (true) {
                y += direct;
                if (y < 0 || y >= C) {
                    direct = -direct;
                    break;
                } else if (arr[R - x][y] == 'x') {
                    arr[R - x][y] = '.';
                    count--;
                    direct = -direct;
                    visited = new boolean[R + 1][C];
                    if (count == bfs(R, 0, true).size()) {
                        break;
                    }

                    for (int j = 0; j < 4; j++) {
                        int nextR = d[j][0] + R - x;
                        int nextC = d[j][1] + y;
                        if (nextR >= 0 && nextC >= 0 && nextR < R && nextC < C
                                && !visited[nextR][nextC] && arr[nextR][nextC] == 'x') {
                            list = bfs(nextR, nextC, false);
                            break;
                        }
                    }

                    int dis = findDistance(list);
                    for (int[] node : list) {
                        arr[node[0] + dis - 1][node[1]] = 'x';
                    }

                    break;
                }
            }


        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(arr[i][j]);
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static ArrayList<int[]> bfs(int x, int y, boolean ch) {
        Queue<int[]> q = new LinkedList<>();
        ArrayList<int[]> list = new ArrayList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        while (!q.isEmpty()) {
            int[] node = q.poll();
            list.add(node);
            if (!ch) {
                arr[node[0]][node[1]] = '.';
            }

            for (int i = 0; i < 4; i++) {
                int nextR = node[0] + d[i][0];
                int nextC = node[1] + d[i][1];
                if (nextR >= 0 && nextC >= 0 && nextR <= R && nextC < C
                        && arr[nextR][nextC] == 'x' && !visited[nextR][nextC]) {
                    visited[nextR][nextC] = true;
                    q.add(new int[]{nextR, nextC});
                }
            }
        }

        return list;
    }

    static int findDistance(ArrayList<int[]> list) {
        int dis = 100;

        for (int[] i : list) {
            int temp = 1;

            while (arr[i[0] + temp][i[1]] != 'x') {
                temp++;
            }
            dis = Math.min(temp, dis);
        }
        return dis;
    }
}



