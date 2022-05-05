import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr;
    static int[] color;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        color = new int[2];
        arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        func(0, 0, N);
        System.out.println(color[0] + "\n" + color[1]);
    }

    static void func(int x, int y, int n) {
        int check = check(x, y, n);

        if (check != -1) {
            color[check]++;
            return;
        }
        int[][] d = {{x, y}, {x, y + n / 2}, {x + n / 2, y}, {x + n / 2, y + n / 2}};
        for (int i = 0; i < 4; i++) {
            func(d[i][0], d[i][1], n / 2);
        }
    }

    static int check(int x, int y, int n) {
        for (int i = x; i < n + x; i++) {
            for (int j = y; j < n + y; j++) {
                if (arr[x][y] != arr[i][j]) {
                    return -1;
                }
            }
        }
        return arr[x][y];
    }
}
