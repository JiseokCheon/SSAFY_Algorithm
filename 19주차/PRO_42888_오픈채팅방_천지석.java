import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        
        String[] answer = new String[record.length];
        HashMap<String, String> map = new HashMap<>();
        
        int count = 0;
        
        for(int i = 0 ; i < record.length; i++){
            if(record[i].startsWith("Enter")){
                String[] s = record[i].split(" ");
                map.put(s[1], s[2]);
                answer[count++] = s[1] + "님이 들어왔습니다.";
            }
            else if(record[i].startsWith("Change")){
                String[] s = record[i].split(" ");
                map.put(s[1], s[2]);
            }
            else {
                String[] s = record[i].split(" ");
                answer[count++] = s[1] + "님이 나갔습니다.";
            }
        }
            
        String[] result = new String[count];
        
        for(int i = 0 ; i < count; i++){
            int index = answer[i].indexOf("님");
            String s = answer[i].substring(0, index);
            result[i] = answer[i].replace(s, map.get(s));
        }
        
        return result;
    }
}
