class Solution {
    public int solution(String s) {
        int len = s.length();
        int answer = len;

        for (int i = 1; i <= len / 2; i++) {
            String str = "";
            String result = "";
            int index = 0;
            int end = i;
            int count = 1;

            while (true) {
            
                if (str.equals(s.substring(index, end))) {
                    count++;
                } else {
                    str = s.substring(index, end);
                    if (count != 1) {
                        result += count + str;
                        count = 1;
                    } else {
                        result += str;
                    }
                }

                if (end + i >= len) {
                    if (str.equals(s.substring(end, len))) {
                        count++;
                        result += count;
                    } else {
                        str = s.substring(end, len);
                        if (count != 1)
                            result += count + str;
                        else
                            result += str;
                    }
                    break;
                }
                index = end;
                end += i;
            }

            answer = Math.min(result.length(), answer);
        }
        return answer;
    }
}
