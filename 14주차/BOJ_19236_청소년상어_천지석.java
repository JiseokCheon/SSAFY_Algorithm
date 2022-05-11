import java.io.*;
import java.util.*;

public class Main {

    static int[][] d = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
    static int answer, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int[][][] space = new int[4][4][2];
        int[][] fishes = new int[17][2];
        int[] shark = new int[3];
        answer = 0;
        fishes[0][0] = -1;
        fishes[0][1] = -1;

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                space[i][j][0] = a;
                space[i][j][1] = b;
                fishes[a][0] = i;
                fishes[a][1] = j;
            }
        }

        shark[2] = space[0][0][1];
        dfs(fishes, space, shark, sum);
        System.out.println(answer);
    }

    static void dfs(int[][] fishes, int[][][] space, int[] shark, int sum) {

        sum += space[shark[0]][shark[1]][0];
        fishes[space[shark[0]][shark[1]][0]][0] = -1;
        fishes[space[shark[0]][shark[1]][0]][1] = -1;
        space[shark[0]][shark[1]][0] = 0;
        space[shark[0]][shark[1]][1] = 0;

        answer = Math.max(answer, sum);
        
        moveFishes(fishes, space, shark);
        
        int[][] tempFishes = new int[17][2];
        int[][][] tempSpace = new int[4][4][2];
        int[] tempShark = new int[3];

        for (int i = 1; i < 4; i++) {
            int nextX = shark[0] + d[shark[2]][0] * i;
            int nextY = shark[1] + d[shark[2]][1] * i;
            if (nextX < 0 || nextY < 0 || nextX > 3 || nextY > 3) {
                break;
            }

            if (space[nextX][nextY][0] > 0) {
                makeTemp(fishes, space, tempFishes, tempSpace);

                tempShark[0] = nextX;
                tempShark[1] = nextY;
                tempShark[2] = tempSpace[nextX][nextY][1];

                dfs(tempFishes, tempSpace, tempShark, sum);
            }
        }
    }

    static void makeTemp(int[][] fishes, int[][][] space, int[][] tempFishes, int[][][] tempSpace) {
        for (int i = 0; i < 17; i++) {
            tempFishes[i] = fishes[i].clone();
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempSpace[i][j] = space[i][j].clone();
            }
        }
    }

    static void moveFishes(int[][] fishes, int[][][] space, int[] shark) {
        for (int i = 1; i < fishes.length; i++) {
            if (fishes[i][0] == -1) {
                continue;
            }
            int direct = space[fishes[i][0]][fishes[i][1]][1];
            for (int j = 0; j < 8; j++) {
                int nextX = fishes[i][0] + d[(direct + j) % 8][0];
                int nextY = fishes[i][1] + d[(direct + j) % 8][1];
                if (nextX >= 0 && nextY >= 0 && nextX < 4 && nextY < 4 && (shark[0] != nextX || shark[1] != nextY)) {
                    space[fishes[i][0]][fishes[i][1]][1] = (direct + j) % 8;

                    int a = space[fishes[i][0]][fishes[i][1]][0];
                    int b = space[nextX][nextY][0];

                    int[] temp = new int[2];
                    temp[0] = space[fishes[i][0]][fishes[i][1]][0];
                    temp[1] = space[fishes[i][0]][fishes[i][1]][1];

                    space[fishes[i][0]][fishes[i][1]][0] = space[nextX][nextY][0];
                    space[fishes[i][0]][fishes[i][1]][1] = space[nextX][nextY][1];

                    space[nextX][nextY][0] = temp[0];
                    space[nextX][nextY][1] = temp[1];

                    temp[0] = fishes[a][0];
                    temp[1] = fishes[a][1];

                    fishes[a][0] = nextX;
                    fishes[a][1] = nextY;

                    fishes[b][0] = temp[0];
                    fishes[b][1] = temp[1];

                    break;
                }
            }
        }
    }
}
