class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int unit = 1; unit <= (s.length()+1)/2 ; unit++){ //길이의 절반까지만 단위길이로
            StringBuilder sb = new StringBuilder();
            String token = s.substring(0, unit); //처음 토큰
            int cnt = 0;
            for(int idx = 0; idx <= s.length(); idx+= unit){
                if(idx+unit > s.length()){ //자르려는 문자열의 범위가 벗어나면 
                    sb.append(cnt==1? token : cnt+token); //남아있던 토큰 추가
                    sb.append(s.substring(idx)); //그 뒤에 남은 것들 추가
                    break;
                }                
                if(s.substring(idx, idx+ unit).equals(token)) cnt++; //토큰과 비교 문자열이 같으면 cnt에 1추가
                else{
                    sb.append(cnt==1? token : cnt+token); //다르면 1개 이상이면 숫자와 같이 추가
                    token = s.substring(idx, idx+ unit); //토큰 값 새롭게 지정
                    cnt = 1;
                }
            }
            answer = Math.min(answer, sb.length());
        }
        return answer;
    }
}
