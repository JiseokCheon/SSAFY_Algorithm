class Solution {
    
    static char[] chars={'A','C','F','J','M','N','R','T'};
    static boolean[] check;
    static char[] strs;
    static int answer;
    public int solution(int n, String[] data) {
        answer = 0;
        check=new boolean[8];
        strs=new char[8];
        findAll(0,n,data);
        return answer;
    }
    
    
    public static boolean checking(String str){
        int temp;
        char from=str.charAt(0);
        char to=str.charAt(2);
        char C=str.charAt(3);
        int gab=str.charAt(4)-'0';
        int temp_gab=0;
        
loop:    for(int i=0; i<8; i++){
            if(strs[i]==from){
                for(int j=1;j<8;j++){
                    temp=i+j;
                    if(temp <8 && strs[temp]==to){
                        temp_gab=j-1;
                        break loop;
                    }
                    temp=i-j;
                    if(temp >=0 && strs[temp]==to){
                        temp_gab=j-1;
                        break loop;
                    }
                }
            }
        }
        
        
        
                        
                    
             
                
            switch(C){
                case '>':
                    if(temp_gab <= gab){
                        return false;
                    }
                    break;
                case '=':
                    if(temp_gab != gab){
                        return false;
                    }
                    break;
                case '<':
                    if(temp_gab >= gab){
                        return false;
                    }
                    break;
            }
        return true;
    }
    
    public static void findAll(int index,int n, String[] data){
        
        if(index ==8){
            boolean ch=true;
     loop:   for(int i=0; i<n; i++){
                if(!checking(data[i])){
                    ch=false;
                    break loop;
                }
             }
            if(ch){
                answer++;
            }
        }else{
            for(int i=0; i<8; i++){
                if(!check[i]){
                    check[i]=true;
                    strs[index]=chars[i];
                    findAll(index+1,n,data);
                    check[i]=false;
                }
            }
        }
    }
}
