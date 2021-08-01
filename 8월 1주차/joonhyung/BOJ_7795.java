package com.java;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_7795 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=0;t<T;t++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int[] nA = new int[A];
			int[] nB = new int[B];
			for(int i=0;i<A;i++) {
				nA[i] = sc.nextInt();
			}
			for(int i=0;i<B;i++) {
				nB[i] = sc.nextInt();
			}
			Arrays.sort(nA);
			Arrays.sort(nB);
			
			int result = 0;
			for(int i=0;i<nA.length;i++)
				result += binarySearch(nA[i],nB);
			System.out.println(result);
		}
	}
	
	public static int binarySearch(int a, int[] b) {
		int start = 0;
		int end = b.length - 1;
		int mid;

		while(start <= end) {
			mid = (start+end)/2;
			if(a>b[mid]) {
				start = mid + 1;
			}
			else {
				end = mid - 1;
			}
		}
		return start;
	}
}
