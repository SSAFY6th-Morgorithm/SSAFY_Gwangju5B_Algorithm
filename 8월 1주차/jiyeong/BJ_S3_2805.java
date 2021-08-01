package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 나무자르기*/

public class BJ_S3_2805 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken()); // 나무 수
		int M = Integer.parseInt(st.nextToken()); // 가져가려는 나무 길이
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		
		System.out.println(BinarySearch(arr, N, M));

	}
	
	private static int BinarySearch(int[] arr, int N, int M) {
		int start = 0;
		int end = (int) 1e9;
		int result = 0;
		
		while (start <= end) {
			long total = 0;
			int mid = (start + end) / 2;
			for (int i = 0; i < N; i++) {
				if (arr[i] > mid)
					total += arr[i] - mid;
			}
			// 나무 길이가 부족한 경우
			if (total < M) {
				end = mid - 1;
			}
			// 나무 길이가 충분한 경우
			else {
				result = mid;
				start = mid + 1;
			}
		}
		
		return result;
		
	}

}
