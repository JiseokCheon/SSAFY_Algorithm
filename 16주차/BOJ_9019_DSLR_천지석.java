import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;
        String[] visited;
        String[] dslr = "DSLR".split("");


        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            visited = new String[10000];

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            Queue<Integer> q = new LinkedList<>();
            visited[A] = "";
            q.add(A);

            while (!q.isEmpty() && visited[B] == null) {
                int node = q.poll();
                String s = visited[node];

                for (int i = 0; i < 4; i++) {
                    int x = dslr(node, dslr[i]);
                    if (visited[x] == null) {
                        visited[x] = s + dslr[i];
                        q.add(x);
                    }
                }
            }
            sb.append(visited[B]).append("\n");
        }
        System.out.println(sb);
    }

    static int dslr(int x, String s) {
        switch (s) {
            case "D":
                return (2 * x) % 10000;
            case "S":
                return (x + 9999) % 10000;
            case "L":
                return (x % 1000) * 10 + (x / 1000);
            default:
                return (x / 10) + (x % 10) * 1000;
        }
    }
}
