package com.ssafy.im;

import java.util.*;
import java.io.*;

public class BOJ_2947_김혜진 {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int[] tree = new int[5];
		
		for(int i=0; i<tree.length; i++) {
			tree[i] = sc.nextInt();
		}
		
		for(int i=0; i<tree.length-1; i++) {
			for(int j=0; j<tree.length-1; j++) {
				if(tree[j]>tree[j+1]) {
					int tmp = tree[j];
					tree[j] = tree[j+1];
					tree[j+1] = tmp;
					for(int trees : tree) {
						System.out.print(trees+" ");
					}
					System.out.println();
				}
				
			}
		}
		
	}

}
