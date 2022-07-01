import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] d2 = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    static int[][][] fishes = new int[5][5][8];
    static int[][] fishCount = new int[5][5];
    static int[][] smell = new int[5][5];
    static boolean[][] visited;
    static int[] shark;
    static int maxCount, minMove;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            fishes[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken()) - 1]++;
        }

        st = new StringTokenizer(br.readLine());
        shark = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

        int answer = 0;
        for (int k = 0; k < S; k++) {
            visited = new boolean[5][5];
            fishCount = new int[5][5];
            maxCount = 0;
            minMove = 1000;

            int[][][] moveFishes = moveFishes();
            findSharkDirect(shark[0], shark[1], 0, 0);
            moveShark(moveFishes);
            setSmell();
            answer = copyFish(fishes, moveFishes);
        }
        System.out.println(answer);
    }

    static int[][][] moveFishes() {
        int[][][] temp = new int[5][5][8];

        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int direct = 0; direct < 8; direct++) {
                    int x = direct;

                    if (fishes[i][j][direct] == 0) {
                        continue;
                    }

                    for (int k = 0; k < 8; k++) {
                        int nextX = i + d[x][0];
                        int nextY = j + d[x][1];

                        if (nextX > 0 && nextY > 0 && nextX < 5 && nextY < 5 && smell[nextX][nextY] >= 0
                                && (shark[0] != nextX || shark[1] != nextY)) {
                            temp[nextX][nextY][x] += fishes[i][j][direct];
                            fishCount[nextX][nextY] += fishes[i][j][direct];
                            break;
                        } else {
                            x = (x + 7) % 8;
                        }
                        if (k == 7){
                            temp[i][j][direct] += fishes[i][j][direct];
                            fishCount[i][j] += fishes[i][j][direct];
                        }
                    }
                }
            }
        }

        return temp;
    }

    static void findSharkDirect(int x, int y, int count, int move) {
        if (move > 100) {
            if (count > maxCount) {
                maxCount = count;
                minMove = move;
            } else if (count == maxCount) {
                minMove = Math.min(move, minMove);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + d2[i][0];
            int nextY = y + d2[i][1];

            if (nextX > 0 && nextY > 0 && nextX < 5 && nextY < 5) {
                if (visited[nextX][nextY]) {
                    findSharkDirect(nextX, nextY, count, move * 10 + i + 1);
                } else {
                    visited[nextX][nextY] = true;
                    findSharkDirect(nextX, nextY, count + fishCount[nextX][nextY], move * 10 + i + 1);
                    visited[nextX][nextY] = false;
                }
            }
        }
    }

    static void moveShark(int[][][] moveFishes) {
        for (int i = 2; i >= 0; i--) {
            int x = (int) Math.pow(10, i);
            shark[0] += d2[minMove / x - 1][0];
            shark[1] += d2[minMove / x - 1][1];

            if (fishCount[shark[0]][shark[1]] > 0) {
                fishCount[shark[0]][shark[1]] = 0;
                moveFishes[shark[0]][shark[1]] = new int[8];
                smell[shark[0]][shark[1]] = -3;
            }
            minMove %= x;
        }
    }

    static void setSmell() {
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                smell[i][j]++;
            }
        }
    }

    static int copyFish(int[][][] fishes, int[][][] moveFishes) {
        int sum = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                int count = 0;
                for (int k = 0; k < 8; k++) {
                    fishes[i][j][k] += moveFishes[i][j][k];
                    sum += fishes[i][j][k];
                    count += fishes[i][j][k];
                }
                fishCount[i][j] = count;
            }
        }
        return sum;
    }
}


