import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        ArrayList<int[]>[] list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int q = Integer.parseInt(st.nextToken()) - 1;
            int r = Integer.parseInt(st.nextToken());
            list[p].add(new int[]{q, r});
            list[q].add(new int[]{p, r});
        }

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken()) - 1;
            int count = 0;

            boolean[] visited = new boolean[N];
            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{v, Integer.MAX_VALUE});

            while (!q.isEmpty()) {
                int[] node = q.poll();

                for (int[] next:  list[node[0]]) {
                    int min = Math.min(next[1], node[1]);
                    if (!visited[next[0]] && min >= k) {
                        q.add(new int[]{next[0], min});
                        visited[node[0]] = true;
                        count++;
                    }
                }
            }
            sb.append(count).append('\n');
        }

        System.out.println(sb);
    }
}
