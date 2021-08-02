package com.java;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_7795 {

	public static void main(String[] args) {                     
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); //테스트 케이스 갯수 입력
		for(int t=0;t<T;t++) {  //테스트 케이스만큼 반복
			int A = sc.nextInt(); //A 갯수
			int B = sc.nextInt(); //B 갯수
			int[] nA = new int[A]; //A 크기들
			int[] nB = new int[B]; //B 크기들
			for(int i=0;i<A;i++) {
				nA[i] = sc.nextInt();
			}
			for(int i=0;i<B;i++) {
				nB[i] = sc.nextInt();
			}
			Arrays.sort(nA); 
			Arrays.sort(nB); //오름차순 정렬
			
			int result = 0;
			for(int i=0;i<nA.length;i++)
				result += binarySearch(nA[i],nB);
			System.out.println(result);
		}
	}
	
	public static int binarySearch(int a, int[] b) { //A원소 하나와 비교할 B크기들
		int start = 0;
		int end = b.length - 1;
		int mid;

		while(start <= end) {
			mid = (start+end)/2;
			if(a>b[mid]) {
				start = mid + 1; //A가 B중앙값보다 클 시 오른쪽 이동
			}
			else {
				end = mid - 1; //아니면 반대(크기 같아도 못먹음)
			}
		}
		return start; //0부터 시작이라 먹을 수 있는 갯수 = start값
	}
}
