package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 예산*/

public class BJ_S3_2512 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int sum= 0;
		
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sum += arr[i]; // 배정된 예산들의 합
		}
		
		Arrays.sort(arr);
		
		int M = Integer.parseInt(br.readLine()); // 최대 예산
		
		if(sum <= M) {
			System.out.println(arr[N-1]);
		}else {
			System.out.println(BinarySearch(arr, 0, arr[N-1], M));
		}
	}
	
	private static long BinarySearch(int[] arr, int start, int end, int M) {
		int val = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int sum = 0;
			for(int target : arr) {
				if(target >= mid) 
					sum += mid;
				else 
					sum += target;
			}
			
			if(sum > M) {
				end = mid - 1;				
			} else {
				start = mid + 1;
				val = mid;
			}
		}
		return val;
		
	}

}
