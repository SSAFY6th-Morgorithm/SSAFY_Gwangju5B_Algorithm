package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 수 찾기*/

public class BJ_S4_1920 {

	public static void main(String[] args) throws NumberFormatException, IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		

		
		int M = Integer.parseInt(br.readLine());
		int[] arr2 = new int[M];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < M; i++) {
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			System.out.println(binarySearch(arr,arr2[i], 0, arr.length-1));
		}
		
	}
	
	private static int binarySearch(int[]arr, int num, int start, int end) {
		while (start <= end) {
			int mid = (start + end) /2;
			if(arr[mid] == num) return 1;
			else if(arr[mid] > num) end = mid-1;
			else if(arr[mid] < num) start = mid+1;
		}
		return 0;
	}

}
