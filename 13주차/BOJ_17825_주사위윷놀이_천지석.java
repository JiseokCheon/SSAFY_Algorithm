import java.io.*;
import java.util.*;

public class Main {

    static int[][] blue = {{10, 13, 16, 19, 25, 30, 35, 40}, {20, 22, 24, 25, 30, 35, 40}, {30, 28, 27, 26, 25, 30, 35, 40}};
    static boolean[] check, checkBlue;
    static int[][] horse;
    static int[] move = new int[10];

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 10; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }
        horse = new int[4][3];
        check = new boolean[41];
        checkBlue = new boolean[41];
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int index, int score) {
        answer = Math.max(score, answer);

        if (index >= 10) {
            return;
        }

        for (int i = 0; i < horse.length; i++) {
            if (horse[i][0] == -1) {
                continue;
            }

            if (horse[i][1] == 0) {
                int next = move[index] * 2 + horse[i][0];

                if (next <= 40) {

                    if (check[next]) {
                        continue;
                    }
                    int temp = horse[i][0];
                    boolean ch = false;
                    check[horse[i][0]] = false;
                    horse[i][0] = next;
                    check[horse[i][0]] = true;

                    if (horse[i][0] % 10 == 0 && horse[i][0] < 40) {
                        horse[i][1] = horse[i][0] / 10;
                        ch = true;
                    }

                    dfs(index + 1, score + horse[i][0]);
                    check[horse[i][0]] = false;
                    horse[i][0] = temp;
                    check[horse[i][0]] = true;
                    if (ch) {
                        horse[i][1] = 0;
                    }
                } else {
                    int temp = horse[i][0];
                    check[horse[i][0]] = false;
                    horse[i][0] = -1;
                    dfs(index + 1, score);
                    horse[i][0] = temp;
                    check[horse[i][0]] = true;
                }
            } else {
                if (horse[i][2] + move[index] < blue[horse[i][1] - 1].length) {
                    int nextIndex = horse[i][2] + move[index];
                    int next = blue[horse[i][1] - 1][nextIndex];
                    if (checkBlue[next] || (next == 40 && check[40])) {
                        continue;
                    }
                    if (next == 40) {
                        check[next] = true;
                    }
                    boolean ch = false;
                    int temp = horse[i][0];
                    if (horse[i][0] % 10 == 0 && horse[i][2] == 0) {
                        check[horse[i][0]] = false;
                        ch = true;
                    }
                    checkBlue[next] = true;
                    horse[i][0] = next;
                    horse[i][2] += move[index];
                    checkBlue[temp] = false;

                    dfs(index + 1, score + horse[i][0]);

                    if (next == 40) {
                        check[next] = false;
                    }
                    checkBlue[next] = false;
                    horse[i][0] = temp;
                    horse[i][2] -= move[index];
                    if (ch) {
                        check[horse[i][0]] = true;
                    }
                    if (horse[i][2] != 0) {
                        checkBlue[horse[i][0]] = true;
                    }
                } else {
                    int temp = horse[i][0];
                    checkBlue[temp] = false;
                    horse[i][0] = -1;
                    if (temp == 40) {
                        check[40] = false;
                    }
                    dfs(index + 1, score);
                    checkBlue[temp] = true;
                    horse[i][0] = temp;
                    if (temp == 40) {
                        check[40] = true;
                    }
                }
            }
        }
    }
}
