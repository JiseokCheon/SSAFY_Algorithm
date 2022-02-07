package com.company;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            System.out.print("#" + tc + " ");

            String s = br.readLine().trim();
            char[] c = new char[s.length()];

            for (int i = 0; i < s.length(); i++) {
                c[i] = s.charAt(i);
            }

            int min = Integer.parseInt(new String(c));
            int max = Integer.parseInt(new String(c));

            for (int i = 0; i < s.length(); i++) {
                for (int j = i + 1; j < s.length(); j++) {
                    char temp = c[i];
                    c[i] = c[j];
                    c[j] = temp;
                    if (c[0] != '0') {
                        min = Math.min(min, Integer.parseInt(new String(c)));
                        max = Math.max(max, Integer.parseInt(new String(c)));
                    }
                    temp = c[i];
                    c[i] = c[j];
                    c[j] = temp;
                }
            }
            System.out.println(min + " " + max);

        }

    }
}
