import java.io.*;
import java.util.*;

public class Main {

    static int stage, count, N, K, size;
    static int[][] arr;
    static ArrayList<Integer> robots = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = 2 * N;
        stage = 0;
        count = 0;

        arr = new int[2 * N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            arr[i][0] = Integer.parseInt(st.nextToken());
        }


        while (count < K) {
            stage++;
            int index = ((size - stage) % size + size) % size;
            moveRobot(index);

            if (arr[index][0] > 0) {
                arr[index][0]--;
                arr[index][1] = 1;
                robots.add(index);
                if (arr[index][0] == 0) {
                    count++;
                }
            }
        }
        System.out.println(stage);
    }

    static void moveRobot(int index) {
        int x = index <= N ? index + N - 1 : (index + N - 1) % N;

        for (int i = 0; i < robots.size(); i++) {
            int robotIndex = robots.get(i);
            int robotNextIndex = (robots.get(i) + 1) % size;
            if (robotIndex == x) {
                arr[robotIndex][1] = 0;
                robots.remove(i--);
            } else if (robotNextIndex == x && arr[robotNextIndex][1] == 0 && arr[robotNextIndex][0] > 0) {
                arr[robotNextIndex][0]--;
                arr[robotIndex][1] = 0;
                robots.remove(i--);
                if (arr[robotNextIndex][0] == 0) {
                    count++;
                }
            } else if (robotNextIndex != x && arr[robotNextIndex][1] == 0 && arr[robotNextIndex][0] > 0) {
                arr[robotIndex][1] = 0;
                arr[robotNextIndex][1] = 1;
                robots.set(i, robotNextIndex);
                arr[robotNextIndex][0]--;
                if (arr[robotNextIndex][0] == 0) {
                    count++;
                }
            }
        }
    }
}
