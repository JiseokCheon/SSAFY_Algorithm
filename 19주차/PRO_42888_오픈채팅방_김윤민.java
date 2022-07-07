import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap<>(); //유저 아이디, 닉네임
        ArrayList<String[]> list = new ArrayList<>(); // 아이디와 입장/퇴장 정보
        
        for(String str : record){
            String[] token = str.split(" ");
            if(token[0].equals("Enter")){ //입장
                list.add(new String[]{token[1], "e"}); //유저 아이디와 타입
                map.put(token[1], token[2]); //닉네임 변경
            }else if(token[0].equals("Leave")){ //퇴장
                list.add(new String[]{token[1], "l"});
            }else{ //닉네임 변경
                map.put(token[1], token[2]); //닉네임 변경
            }
        }
        
        ArrayList<String> ans = new ArrayList<>(); //결과 저장할 list
        for(String[] str : list){
            StringBuilder sb = new StringBuilder();
            sb.append(map.get(str[0])).append("님이 ")
                .append(str[1].equals("e")? "들어왔습니다.": "나갔습니다.");
            
            ans.add(sb.toString());
        }
        return ans.toArray(new String[ans.size()]);
    }
}
