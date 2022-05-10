import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringBuilder result = new StringBuilder();
            String p = br.readLine().trim();
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine().trim();

            String[] arr = s.substring(1, s.length() - 1).split(",");
            Deque<Integer> dq = new ArrayDeque<>();
            boolean check = true;

            for (int i = 0; i < n; i++) {
                dq.push(Integer.parseInt(arr[i]));
            }
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == 'R') {
                    check = !check;
                } else if (p.charAt(i) == 'D') {
                    if (dq.isEmpty()) {
                        n = -1;
                        break;
                    }
                    if (check) {
                        dq.pollLast();
                    } else {
                        dq.pollFirst();
                    }
                }
            }
            result.append("[");
            while (!dq.isEmpty()) {
                if (check) {
                    result.append(dq.pollLast());
                } else {
                    result.append(dq.poll());
                }

                if (dq.size() > 0) {
                    result.append(",");
                }
            }
            result.append("]");
            sb.append(n == -1 ? "error" : result).append("\n");
        }
        System.out.println(sb);
    }
}


