package com.company;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String a = st.nextToken();
        String b = st.nextToken();

        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                // 문자가 같은 부분 찾음
                if (a.charAt(i) == b.charAt(j)) {
                    for (int u = 0; u < b.length(); u++) {
                        for (int v = 0; v < a.length(); v++) {
                            // 인덱스에 맞게 출력
                            if (u == j) {
                                System.out.print(a.charAt(v));
                            } else if (v == i) {
                                System.out.print(b.charAt(u));
                            } else {
                                System.out.print(".");
                            }
                        }
                        System.out.println();
                    }
                    return;
                }
            }
        }
    }
}