import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] list;
    static int N, M, T;
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        list = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list[i] = new ArrayList<>();
            for (int j = 0; j < M; j++) {
                list[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int tmp = x;
            while (tmp <= N) {
                rotate(tmp - 1, d, k);
                tmp += x;
            }
            nearRemove();
        }

        int[] answer = cal();

        System.out.println(answer[0]);
    }

    static void rotate(int x, int d, int k) {
        for (int i = 0; i < k; i++) {
            if (d == 1) {
                list[x].add(list[x].remove(0));
            } else {
                list[x].add(0, list[x].remove(list[x].size() - 1));
            }
        }
    }

    static void nearRemove() {
        Queue<int[]> q = new LinkedList<>();
        boolean check = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != -1) {
                    q.add(new int[]{i, j});
                    int x = list[i].get(j);
                    while (!q.isEmpty()) {
                        int[] node = q.poll();

                        for (int k = 0; k < 4; k++) {
                            int nextX = node[0] + d[k][0];
                            int nextY = (node[1] + d[k][1] + M) % M;
                            if (nextX >= 0 && nextX < N
                                    && list[nextX].get(nextY) == x) {
                                check = true;
                                list[nextX].set(nextY, -1);
                                list[node[0]].set(node[1], -1);
                                q.add(new int[]{nextX, nextY});
                            }
                        }
                    }
                }
            }
        }
        if (!check) {
            changeNum();
        }
    }

    static int[] cal() {
        int[] result = new int[2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != -1) {
                    result[0] += list[i].get(j);
                    result[1]++;
                }
            }
        }
        return result;
    }

    static void changeNum() {
        int[] result = cal();
        double x = (double) result[0] / result[1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (list[i].get(j) != -1) {
                    if (list[i].get(j) > x) {
                        list[i].set(j, list[i].get(j) - 1);
                    } else if (list[i].get(j) < x) {
                        list[i].set(j, list[i].get(j) + 1);
                    }
                }
            }
        }
    }
}
