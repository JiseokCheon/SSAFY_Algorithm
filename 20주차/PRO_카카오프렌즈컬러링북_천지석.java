class Solution {
    static int[][] way = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    static boolean[][] check;
    
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        check = new boolean[m][n];
        
        for(int i = 0; i< m ;i++){
            for(int j = 0; j< n; j++){
                if(!check[i][j] && picture[i][j]!=0){
                    numberOfArea++;
                    maxSizeOfOneArea = Math.max(dfs(i, j, picture[i][j], picture), maxSizeOfOneArea);
                }
            }
        }        
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    static int dfs(int x, int y, int color, int[][] picture){      
        
        int num = 0;
        
        if (check[x][y]){
            return num;
        }
        
        num = 1;
        check[x][y] = true;
        
        for(int i = 0; i< 4; i++){
            if(x + way[i][0] >= 0 && y + way[i][1] >= 0 && x + way[i][0] < picture.length && y + way[i][1] < picture[0].length && picture[x + way[i][0]][y + way[i][1]] == color)
                num += dfs(x + way[i][0], y + way[i][1], color, picture);
        }
          
        return num;
        
    }
    
}
