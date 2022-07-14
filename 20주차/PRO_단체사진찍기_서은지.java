import java.util.*;
import java.io.*;

class Solution {
    static char[] selected;
    static boolean[] visited;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    static int count;
    
    public int solution(int n, String[] data) throws Exception {
        count = 0;
        selected = new char[friends.length];
        visited = new boolean[friends.length];
        comb("", data);
        return count;
    }
    
    public static void comb(String str, String[] data) {
        if (str.length() == friends.length) {
            for (int i = 0; i < data.length; i++) {
                if (!check(str, data[i]))
                    return;
            }
            count++;
            return;
        }
        for (int i = 0; i < friends.length; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            comb(str + friends[i], data);
            visited[i] = false;
        }
        
    }
    
    public static boolean check(String str, String data) {
            char op = data.charAt(3);
            int diff = data.charAt(4) - '0';
            int fidx = str.indexOf(data.charAt(0));
            int sidx = str.indexOf(data.charAt(2));
            int real_diff = Math.abs(fidx - sidx) - 1;
            switch(op) {
                case '=':
                    if (real_diff != diff)
                        return false;
                    break;
                case '<':
                    if (real_diff >= diff)
                        return false;
                    break;
                case '>':
                    if (real_diff <= diff)
                        return false;
                    break;
            }
        return true;
    }
}
