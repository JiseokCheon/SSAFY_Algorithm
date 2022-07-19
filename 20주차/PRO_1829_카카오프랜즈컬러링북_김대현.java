import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        int[][] dir={{-1,0},{1,0},{0,1},{0,-1}};
        boolean[][] visited=new boolean[m][n];
        Queue<point> q=new LinkedList<point>();
        int tempr,tempc;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(picture[i][j]==0){
                    visited[i][j]=true;
                }else if(!visited[i][j]){
                    numberOfArea++;
                    visited[i][j]=true;
                    q.offer(new point(i,j));
                    int count=1;
                    while(!q.isEmpty()){
                        point p=q.poll();
                        for(int d=0; d<4; d++){
                            tempr=p.r+dir[d][0];
                            tempc=p.c+dir[d][1];
                            if(tempr < 0 || tempc <0 || tempr >=m || tempc >=n || visited[tempr][tempc]){
                                continue;
                            }
                            if(picture[tempr][tempc]==picture[i][j]){
                                count++;
                                visited[tempr][tempc]=true;
                                q.offer(new point(tempr,tempc));
                            }
                        }
                    }
                    if(count > maxSizeOfOneArea){
                        maxSizeOfOneArea=count;
                    }
                }
            }
        }
        
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    public static class point{
    int r,c;
    public point(int r, int c){
        this.r=r;
        this.c=c;
    }
}
}

