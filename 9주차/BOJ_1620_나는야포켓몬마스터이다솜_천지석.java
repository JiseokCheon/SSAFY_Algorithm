package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, String> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            String s = br.readLine();
            map1.put(i, s);
            map2.put(s, i);
        }

        for (int j = 0; j < M; j++) {
            String s = br.readLine();
            int num = s.charAt(0) - '0';
            if (num >= 0 && num < 10) {
                sb.append(map1.get(Integer.parseInt(s))).append('\n');
            } else {
                sb.append(map2.get(s)).append('\n');
            }
        }

        System.out.println(sb);
    }
}

