package com.java;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_1920 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();	
		int[] nArr = new int[n];
		for(int i=0;i<n;i++) {
			nArr[i] = sc.nextInt();
		}
		
		Arrays.sort(nArr);//nArr 오름차순 정렬
		
		int m = sc.nextInt();
		for(int i=0;i<m;i++) {//입력받음과 동시에 이진탐색으로 답 출력
			System.out.println(binarySearch(nArr,sc.nextInt()));
		}
		
		
	}
	
	public static int binarySearch(int[] arr, int n) {//이진탐색 알고리즘
		int start = 0;
		int end = arr.length - 1;
		int mid;
		
		while(start <= end) {
			mid = (start+end)/2;
			
			if(arr[mid] == n)
				return 1;//일치하는 숫자가 있을 경우 1 리턴
			else if(arr[mid] > n)
				end = mid-1;
			else
				start = mid+1;
		}
		return 0;//일치하는 숫자가 없을 경우 0 리턴
	}
}
