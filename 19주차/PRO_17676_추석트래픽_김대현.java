import java.util.*;
import java.io.*;

class Solution {
    
    public static class sae implements Comparable<sae>{
        int h,m,s,ms;
        boolean start;
        
        public sae(int h, int m, int s,int ms, boolean start){
            this.h=h;
            this.m=m;
            this.s=s;
            this.ms=ms;
            this.start=start;
        }
        
        @Override
        public int compareTo(sae o){
            if(this.h==o.h){
                if(this.m==o.m){
                    if(this.s==o.s){
                        if(this.ms==o.ms){
                            if(this.start){
                                return -1;
                            }else{ 
                                return 1;
                            }
                        } 
                        return this.ms-o.ms;
                    }
                    
                    return this.s-o.s;
                }
                return this.m-o.m;
            }
            return this.h-o.h;
        }
    }
    
    
    
    public int solution(String[] lines) {
        PriorityQueue<sae> q=new PriorityQueue<sae>();
        StringTokenizer st; // 라인 전체 읽은거
        StringTokenizer st2; // 종료시간(문자열)
        StringTokenizer st3; // 실행시간
        StringTokenizer st4; // h,m,s
        String time;
        String time2;
        int h,m,s,ms;
        float ps;
        int p_s,p_ms;
        for(int i=0; i<lines.length;i++){
            st=new StringTokenizer(lines[i]);
            st.nextToken();
            st2=new StringTokenizer(st.nextToken(),":");
            st3=new StringTokenizer(st.nextToken(),"s");
            h=Integer.parseInt(st2.nextToken());
            m=Integer.parseInt(st2.nextToken());
            st4=new StringTokenizer(st2.nextToken(),".");
            s=Integer.parseInt(st4.nextToken());
            ms=Integer.parseInt(st4.nextToken());
            ps=Float.parseFloat(st3.nextToken());
            p_s=(int)ps;
            p_ms=(int)((ps-(int)ps)*1000);
            if(ms==0){
                q.offer(new sae(h,m,s,999,false));
            }else{
                if(s<59){
                    q.offer(new sae(h,m,s+1,ms-1,false));
                }else{
                    if(m<59){
                        q.offer(new sae(h,m+1,0,ms-1,false));
                    }else{
                        q.offer(new sae(h+1,0,0,ms-1,false));
                    }
                }
            }
            
            
            
            if(ms+1 >= p_ms){
                
                ms=ms+1-p_ms;
            }else{
                if(s==0){
                    if(m==0){
                        if(h==0){
                            h=0;
                            m=0;
                            s=0;
                            ms=0;
                        }else{
                            h--;
                            m+=59;
                            s+=59;
                            ms=ms+1001-p_ms;
                        }
                    }else{
                        m--;
                        s+=59;
                        ms=ms+1001-p_ms;
                    }
                }else{
                    s--;
                    ms=ms+1001-p_ms;
                }
            }
            
            if(s>=p_s){
                s-=p_s;
            }else{
                if(m==0){
                        if(h==0){
                            h=0;
                            m=0;
                            s=0;
                            ms=0;
                        }else{
                            h--;
                            m+=59;
                            s=s+60-p_s;
                        }
                    }else{
                        m--;
                        s=s+60-p_s;
                    }
            }
            
            q.offer(new sae(h,m,s,ms,true));
            
        }
        
        
        int answer = 0;
        int now=0;
        boolean nows;
        while(!q.isEmpty()){
            if(q.poll().start){
                now++;
            }else{
                now--;
            }
            if(now >answer){
                answer=now;
            }
            
        }
        
        
        
        return answer;
    }
}
