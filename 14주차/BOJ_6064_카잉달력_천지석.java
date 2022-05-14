import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            long k = -1;
            long M = Long.parseLong(st.nextToken());
            long N = Long.parseLong(st.nextToken());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());

            if (x == y) {
                sb.append(x).append("\n");
            } else {
                for (int i = 1; i <= Math.max(N, M); i++) {
                    long temp = Math.min(N, M) * i + (N > M ? x : y);
                    if ((temp - (N > M ? y : x)) % Math.max(N, M) == 0) {
                        k = temp;
                        break;
                    }
                }
                sb.append(k).append("\n");
            }
        }
        System.out.println(sb);
    }
}
