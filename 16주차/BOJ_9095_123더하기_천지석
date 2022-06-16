import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            System.out.println(count(Integer.parseInt(br.readLine())));
        }
    }

    static int count(int n) {
        int cnt = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(0);

        while (!q.isEmpty()) {
            int num = q.poll();

            if (num == n) {
                cnt++;
                continue;
            }

            if (num + 1 <= n) {
                q.add(num + 1);
            }

            if (num + 2 <= n) {
                q.add(num + 2);
            }

            if (num + 3 <= n) {
                q.add(num + 3);
            }
        }
        return cnt;
    }
}
