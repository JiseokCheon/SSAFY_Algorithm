package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= H; i++) {
            String s = br.readLine();
            int x = -1;
            for (int j = 0; j < W; j++) {
                sb.append((x = (s.charAt(j) == 'c' ? 0 : x)) != -1 ? x++ : x).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

