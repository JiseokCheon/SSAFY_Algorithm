import java.util.*;




class Solution {
    public static class str {
		String id;
		boolean in;

		public str(String id, boolean in) {
			this.id = id;
			this.in= in;
		}
	}
    public String[] solution(String[] record) {
        StringTokenizer st;
        Map<String,String> map=new HashMap<String,String>();
        Queue<str> q=new LinkedList<str>();
        for(int i=0; i<record.length;i++){
            st=new StringTokenizer(record[i]);
            String s1=st.nextToken();
            String s2=st.nextToken();
            if(s1.equals("Enter")){
                String s3=st.nextToken();
                map.put(s2,s3);
                q.offer(new str(s2,true));
            }else if(s1.equals("Leave")){
                q.offer(new str(s2,false));
            }else if(s1.equals("Change")){
                String s3=st.nextToken();
                map.put(s2,s3);
            }
            
        }
        int count=0;
        StringBuilder sb;
        
        String[] answer = new String[q.size()];
        while(!q.isEmpty()){
            str str=q.poll();
            sb=new StringBuilder();
            sb.append(map.get(str.id)).append("님이 ");
            if(str.in){
                sb.append("들어왔습니다.");
            }else{
                sb.append("나갔습니다.");
            }
            answer[count++]=sb.toString();
        }
        
        return answer;
    }
}
