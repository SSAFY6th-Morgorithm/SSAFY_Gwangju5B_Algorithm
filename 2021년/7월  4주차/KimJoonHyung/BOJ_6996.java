package com.java;

import java.util.*;

public class BOJ_6996 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);	
		int n = sc.nextInt();
		
		for(int i=0;i<n;i++) {
			String a = sc.next();
			String b = sc.next();
			
			System.out.print(a + " & " + b + " are ");
			if(!sort(a).equals(sort(b))) {
				System.out.print("NOT ");
			}
			System.out.println("anagrams.");
		}
	}

	public static String sort(String str) {
		char[] Anagram = str.toCharArray();
		Arrays.sort(Anagram);
		return new StringBuilder(new String(Anagram)).toString();
	}
}
