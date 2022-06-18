import java.io.*;
import java.util.*;

public class Main {
    static HashMap<String, Integer> map;
    static int answer;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            answer = 1;

            for (int i = 0; i < n; i++) {
                String[] s = br.readLine().split(" ");
                map.put(s[1], map.getOrDefault(s[1], 0) + 1);
            }

            for (String s : map.keySet()) {
                answer *= (map.get(s) + 1);
            }

            sb.append(answer - 1).append("\n");
        }
        System.out.println(sb);
    }
}

