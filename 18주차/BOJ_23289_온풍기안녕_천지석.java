import java.io.*;
import java.util.*;

public class Main {
    // 오른쪽 왼쪽 위 아래
    static int[][][] d = {{{0, 1}, {-1, 1}, {1, 1}},
            {{0, -1}, {-1, -1}, {1, -1}},
            {{-1, 0}, {-1, -1}, {-1, 1}},
            {{1, 0}, {1, -1}, {1, 1}}};
    static int[][] arr;
    static HashSet<Wall> walls;
    static ArrayList<int[]> heaters, checkPlaces;
    static int R, C, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int choco = 0;

        arr = new int[R][C];
        walls = new HashSet<>();
        heaters = new ArrayList<>();
        checkPlaces = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int x = Integer.parseInt(st.nextToken());
//                arr[i][j] = x;
                if (x == 5) {
                    checkPlaces.add(new int[]{i, j, x});
                } else if (x > 0) {
                    heaters.add(new int[]{i, j, x - 1});
                }
            }
        }

        int W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            if (d == 0) {
                walls.add(new Wall(x, y, x - 1, y));
                walls.add(new Wall(x - 1, y, x, y));
            } else {
                walls.add(new Wall(x, y, x, y + 1));
                walls.add(new Wall(x, y + 1, x, y));
            }
        }

        while (choco <= 100) {
            heaterON();
            controlTemperature();
            decreaseTemperature();
            choco++;
            if (checkTemperature()) {
                break;
            }

        }
        
        System.out.println(choco);
    }

    static void heaterON() {
        for (int[] heater : heaters) {
            int[][] directs = d[heater[2]];
            boolean[][] visited = new boolean[R][C];

            Queue<int[]> q = new LinkedList<>();
            visited[heater[0] + directs[0][0]][heater[1] + directs[0][1]] = true;
            arr[heater[0] + directs[0][0]][heater[1] + directs[0][1]] += 5;
            q.add(new int[]{heater[0] + directs[0][0], heater[1] + directs[0][1], 5});

            while (!q.isEmpty()) {
                int[] node = q.poll();

                if (node[2] == 1) {
                    break;
                }

                for (int i = 0; i < 3; i++) {
                    int nextR = directs[i][0] + node[0];
                    int nextC = directs[i][1] + node[1];

                    if (nextR < R && nextC < C && nextR >= 0 && nextC >= 0 && !visited[nextR][nextC] && checkDirect(directs, i, node)) {
                        visited[nextR][nextC] = true;
                        arr[nextR][nextC] += node[2] - 1;
                        q.add(new int[]{nextR, nextC, node[2] - 1});
                    }
                }
            }
        }
    }

    static void controlTemperature() {
        int[][] temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < 4; k += 3) {
                    int checkR = i + d[k][0][0];
                    int checkC = j + d[k][0][1];
                    if (checkR >= 0 && checkC >= 0 && checkR < R && checkC < C && !walls.contains(new Wall(i, j, checkR, checkC))) {
                        if (arr[i][j] > arr[checkR][checkC]) {
                            temp[i][j] -= (arr[i][j] - arr[checkR][checkC]) / 4;
                            temp[checkR][checkC] += (arr[i][j] - arr[checkR][checkC]) / 4;
                        } else {
                            temp[i][j] += (arr[checkR][checkC] - arr[i][j]) / 4;
                            temp[checkR][checkC] -= (arr[checkR][checkC] - arr[i][j]) / 4;
                        }
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += temp[i][j];
            }
        }
    }

    static void decreaseTemperature() {
        for (int i = 0; i < R; i++) {
            arr[i][0] = Math.max(0, arr[i][0] - 1);
            arr[i][C - 1] = Math.max(0, arr[i][C - 1] - 1);
        }
        for (int i = 1; i < C - 1; i++) {
            arr[0][i] = Math.max(0, arr[0][i] - 1);
            arr[R - 1][i] = Math.max(0, arr[R - 1][i] - 1);
        }
    }

    static boolean checkTemperature() {
        for (int[] checkPlace : checkPlaces) {
            if (arr[checkPlace[0]][checkPlace[1]] < K) {
                return false;
            }
        }

        return true;
    }

    static boolean checkDirect(int[][] directs, int i, int[] node) {
        int nextR = directs[i][0] + node[0];
        int nextC = directs[i][1] + node[1];
        return i == 0 && !walls.contains(new Wall(node[0], node[1], nextR, nextC)) ||
                i != 0 && (directs[0][0] == 0 && !walls.contains(new Wall(node[0], node[1], nextR, node[1]))
                        && !walls.contains(new Wall(nextR, nextC, nextR, node[1])) ||
                        directs[0][1] == 0 && !walls.contains(new Wall(node[0], node[1], node[0], nextC))
                                && !walls.contains(new Wall(nextR, nextC, node[0], nextC)));
    }


    static class Wall {
        int x1, y1, x2, y2;

        public Wall(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Wall wall = (Wall) o;
            return x1 == wall.x1 && y1 == wall.y1 && x2 == wall.x2 && y2 == wall.y2;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x1, y1, x2, y2);
        }
    }
}


