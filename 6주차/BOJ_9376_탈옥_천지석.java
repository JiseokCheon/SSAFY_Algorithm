package com.company;

// 최대한 문이 겹치도록 해야함
// -> 죄수 두명이 만나서 같이 나가도록 해서 문에 겹치도록 함
// 만나서 밖으로 나감 -> 반대로 하면 밖에서 죄수 두명이 만나는 지점까지 이동
// -> 상근이가 밖에서 안으로 들어온다고 생각해서 3명이 만나는 지점을 찾아줌
public class Main {
    static int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int h, w, answer;
    static int[][][] route;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            arr = new int[h + 2][w + 2];        // 감옥
            route = new int[3][h + 2][w + 2];   // 죄수와 상근이가각 좌표마다 이동할때 만나는 문의 갯수 저장
            int[] prisonerA = new int[3];   // 죄수 위치
            int[] prisonerB = new int[3];   // 죄수 위치
            int[] sang = {0, 0, 0};         // 상근이?
            answer = Integer.MAX_VALUE;

            // 최소 경로를 찾기위해 일단 최대로 설정
            for (int k = 0; k < 3; k++) {
                for (int i = 0; i < h + 2; i++) {
                    Arrays.fill(route[k][i], 10000);
                }
            }

            route[0][0][0] = 0; // 상근이 시작하는 위치

            for (int i = 1; i <= h; i++) {
                String s = br.readLine();
                for (int j = 1; j <= w; j++) {
                    char c = s.charAt(j - 1);
                    if (c == '*') {         // 벽은 -1로 설정
                        arr[i][j] = -1;
                    } else if (c == '#') {  // 문은 1로 설정
                        arr[i][j] = 1;
                    } else if (c == '$') {  // 죄수 위치 설정
                        if (prisonerA[0] == 0) {
                            prisonerA[0] = i;
                            prisonerA[1] = j;
                            route[1][i][j] = 0;
                        } else {
                            prisonerB[0] = i;
                            prisonerB[1] = j;
                            route[2][i][j] = 0;
                        }
                    }
                }
            }

            bfs(0, sang);   // 상근이의 최소 경로 구함
            bfs(1, prisonerA);  // 죄수들의 최소 경로 구함
            bfs(2, prisonerB);

            System.out.println(answer);
        }
    }

    static void bfs(int x, int[] man) {

        // 문의 갯수가 작은 순서대로 정렬한 우선 순위 큐
        PriorityQueue<int[]> q = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);

        q.add(man);
        boolean[][] visited = new boolean[h + 2][w + 2];

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            visited[pos[0]][pos[1]] = true;
            // 2두번째 죄수 탐색이면 정답 찾음
            if (x == 2) {
                int count = route[0][pos[0]][pos[1]] + route[1][pos[0]][pos[1]] + route[2][pos[0]][pos[1]];

                answer = Math.min(answer, arr[pos[0]][pos[1]] == 1 ? count - 2 : count);
            }

            for (int i = 0; i < 4; i++) {
                int nextX = pos[0] + d[i][0];
                int nextY = pos[1] + d[i][1];

                // 다음 위치가 벽이 아니면 탐색
                if (nextX >= 0 && nextY >= 0 && nextX < h + 2 && nextY < w + 2
                        && !visited[nextX][nextY] && arr[nextX][nextY] != -1) {
                    // 다음 위치가 문이면 +1
                    route[x][nextX][nextY] = arr[nextX][nextY] == 1 ? route[x][pos[0]][pos[1]] + 1 : route[x][pos[0]][pos[1]];
                    visited[nextX][nextY] = true;
                    q.add(new int[]{nextX, nextY, route[x][nextX][nextY]});
                }
            }
        }
    }
}
