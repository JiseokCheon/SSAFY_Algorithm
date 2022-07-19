import java.util.*;
class Solution {
    public long solution(int w, int h) {
        long answer = 0;
        
        for(int i=1; i<w;i++){
            answer+=(int)Math.floor(((double)h * i)/w);
        }
        
        return answer*2;
    }
}
