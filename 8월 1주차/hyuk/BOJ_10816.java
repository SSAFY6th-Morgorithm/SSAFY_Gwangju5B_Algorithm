package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S4_10816 {
	private static int N;
	private static int M;

	private static int check(int[] arr, int n) {
		int s = 0;
		int e = arr.length - 1;
		n += 10000000;
		int ans1 = -1, ans2 = -1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (arr[mid] >= n) {
				e = mid - 1;
				ans1 = mid;
			} else if (arr[mid] < n) {
				s = mid + 1;
			}
		}
		s = 0;
		e = arr.length - 1;

		while (s <= e) {
			int mid = (s + e) / 2;

			if (arr[mid] > n) {
				e = mid - 1;
			} else if (arr[mid] <= n) {
				s = mid + 1;
				ans2 = mid;
			}
		}

		if (ans1 == -1)
			return 0;
		else
			return ans2 - ans1 + 1;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			int[] arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken()) + 10000000;
			}
			Arrays.sort(arr);
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int a = check(arr, Integer.parseInt(st.nextToken()));
				bw.write(a+" ");
			}
			bw.flush();
			
		} catch (Exception e) {
		} 
		
	}
}
