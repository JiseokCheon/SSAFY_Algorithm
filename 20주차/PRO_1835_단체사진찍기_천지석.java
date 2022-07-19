class Solution {
    static boolean[] check;
    static String[] array = {"A", "C", "F", "J", "M", "N", "R", "T"};
    static int count;
    
    public int solution(int n, String[] data) {        

        check = new boolean[8];
        count = 0;

            dfs(0, "", 0, data);

                
        return count;
    }
    
    static boolean comfirm(String str, String[] data){

            for(int i = 0 ;i < data.length; i++){
                String s1 = data[i].charAt(0) + ""; 
                String s3 = data[i].charAt(2) + ""; 
                String s4 = data[i].charAt(3) + ""; 
                String s5 = data[i].charAt(4) + ""; 
                
                int index1 = str.indexOf(s1);
                int index2 = str.indexOf(s3);
                int num = Integer.parseInt(s5);

                
                if(s4.equals("=")){
                    if(Math.abs(index1- index2) - 1 != num)
                        return false;
                }else if(s4.equals(">")){
                    if(Math.abs(index1- index2) - 1 <= num)
                        return false;
                }else if(s4.equals("<")){
                    if(Math.abs(index1- index2 ) - 1 >= num)
                        return false;
                }
            }
            
            return true;
            
    }
    
    static void dfs(int start , String str, int size, String[] data){
                
        if(size == 8 && comfirm(str, data)){
            count++;
        }
        
        
        for(int i = 0; i < 8; i++){
            if(!check[i]){
                check[i] = true;
                dfs(i, str + array[i], size + 1, data);
                check[i] = false;
            }
        }
        
    }
}
