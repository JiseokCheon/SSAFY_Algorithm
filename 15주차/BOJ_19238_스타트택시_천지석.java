import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int[][] way = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static int[][] arr;
    static int N, M, fuel;
    static boolean[][] visited;
    static HashMap<Integer, int[]> map = new HashMap<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());

        int curX = Integer.parseInt(st.nextToken());
        int curY = Integer.parseInt(st.nextToken());

        for (int i = 2; i < M + 2; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x][y] = i;
            map.put(i, new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        for (int i = 0; i < M; i++) {
            Node n = find(new Node(curX, curY, 0), 0, 0);

            if (n == null || fuel - n.dis < 0) {
                System.out.println(-1);
                return;
            }

            curX = n.x;
            curY = n.y;

            fuel -= n.dis;
            n.dis = 0;
            int[] des = map.get(arr[n.x][n.y]);
            n = find(n, des[0], des[1]);
            arr[curX][curY] = 0;

            if (n == null || fuel - n.dis < 0) {
                System.out.println(-1);
                return;
            }


            fuel += n.dis;
            curX = n.x;
            curY = n.y;
        }
        System.out.println(fuel);
    }

    static Node find(Node n, int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(n);
        visited = new boolean[N + 1][N + 1];

        while (!pq.isEmpty()) {

            Node node = pq.poll();
            
            if(visited[node.x][node.y]){
               continue;
            }
            
            if (x == 0 && y == 0 && arr[node.x][node.y] > 1) {
                return node;
            } else if (node.x == x && node.y == y) {
                return node;
            }

            for (int i = 0; i < 5; i++) {
                int tempX = node.x + way[i][0];
                int tempY = node.y + way[i][1];

                if (tempX <= N && tempX > 0 && tempY <= N && tempY > 0 && arr[tempX][tempY] != 1 && !visited[tempX][tempY]) {
                    pq.add(new Node(tempX, tempY, node.dis + 1));
                }
            }

            visited[node.x][node.y] = true;

        }
        return null;
    }

    static class Node implements Comparable<Node> {
        int x, y, dis;

        public Node(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            if (dis - o.dis != 0)
                return dis - o.dis;
            else if (x - o.x != 0) {
                return x - o.x;
            }
            return y - o.y;
        }
    }

}
