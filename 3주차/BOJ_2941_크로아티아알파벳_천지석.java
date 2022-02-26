package com.company;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine().trim();

        int index = 0;  // 확인할 인덱스
        int count = 0;  // 알파벳 갯수
        // 크로아티아 알파벳
        String[] arr = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

        while (index < s.length()) {
            for (int i = 0; i < 8; i++) {
                // 확인할 인덱스에 크로아티아 알파벳이 포함되어 있는지 확인
                // 포함되어 있으면 알파벳 만큼 인덱스 이동
                if (s.substring(index).startsWith(arr[i])) {
                    index += arr[i].length() - 1;
                    break;
                }
            }

            count++;
            index++;
        }
        System.out.println(count);
    }
}