import java.util.*;
import java.io.*;

class Solution {
    static int max, M, N;
    static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] map;
    
    public int[] solution(int m, int n, int[][] picture) throws Exception {
        M = m;
        N = n;
        max = 0;
        int[] answer = new int[2];
        
        map = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++)
                map[i][j] = picture[i][j];
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 0) {
                    answer[0]++;
                    max = Math.max(dfs(i, j), max);
                }
            }
        }
        answer[1] = max;
        return answer;
    }
    
    public static int dfs(int r, int c) {
        int tmp = map[r][c];
        int cnt = 1;
        map[r][c] = 0;
        for (int i = 0; i < 4; i++) {
            int dr = r + delta[i][0];
            int dc = c + delta[i][1];
            if (dr < 0 || dc < 0 || dr >= M || dc >= N || map[dr][dc] != tmp)
                continue;
            cnt += dfs(dr, dc);
        }
        return cnt;
    }
}
