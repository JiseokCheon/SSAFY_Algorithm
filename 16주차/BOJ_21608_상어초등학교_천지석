import java.io.*;
import java.util.*;


public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int answer = 0;
        int N = Integer.parseInt(br.readLine());
        int N2 = (int) Math.pow(N, 2);
        int[][] arr = new int[N + 1][N + 1];
        int[][] likeNum = new int[N2 + 1][4];
        for (int t = 0; t < N2; t++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            likeNum[num] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int x = -1;
            int y = -1;
            int maxSpace = 0;
            int maxLike = 0;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][j] != 0) {
                        continue;
                    }
                    if (x == -1 && y == -1) {
                        x = i;
                        y = j;
                    }
                    int space = 0;
                    int like = 0;

                    for (int k = 0; k < 4; k++) {
                        int aroundX = i + d[k][0];
                        int aroundY = j + d[k][1];

                        if (aroundX > 0 && aroundX <= N && aroundY > 0 && aroundY <= N) {
                            if (arr[aroundX][aroundY] == 0) {
                                space++;
                                continue;
                            }
                            for (int l : likeNum[num]) {
                                if (arr[aroundX][aroundY] == l) {
                                    like++;
                                    break;
                                }
                            }
                        }
                    }


                    if (like > maxLike) {
                        x = i;
                        y = j;
                        maxLike = like;
                        maxSpace = space;
                    } else if (like == maxLike && space > maxSpace) {
                        x = i;
                        y = j;
                        maxSpace = space;
                    }
                }
            }
            arr[x][y] = num;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int count = 0;
                for (int k = 0; k < 4; k++) {
                    int aroundX = i + d[k][0];
                    int aroundY = j + d[k][1];

                    if (aroundX > 0 && aroundX <= N && aroundY > 0 && aroundY <= N) {
                        for (int l : likeNum[arr[i][j]]) {
                            if (l == arr[aroundX][aroundY]) {
                                count++;
                                break;
                            }
                        }
                    }
                }

                answer += count == 0 ? 0 : Math.pow(10, count - 1);
            }
        }
        System.out.println(answer);

    }
}

