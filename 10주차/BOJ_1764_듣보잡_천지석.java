package com.company;

import java.io.*;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            set.add(s);
        }
        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (set.contains(s)) {
                list.add(s);
            }
        }

        Collections.sort(list);
        System.out.println(list.size());

        for (String s : list) {
            System.out.println(s);
        }

    }
}

