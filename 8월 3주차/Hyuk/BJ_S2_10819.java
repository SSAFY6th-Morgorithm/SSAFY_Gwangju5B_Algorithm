package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S2_10819 {
	static int N;
	static int[] arr;
	static StringTokenizer st;

	static boolean np(int[] num) {
		int n = num.length - 1;

		int i = n;
		while (i > 0 && num[i - 1] >= num[i])
			--i;
		if (i == 0)
			return false;

		int j = n;
		while (num[i - 1] >= num[j])
			--j;
		swap(i - 1, j, num);

		int k = n;
		while (k > i)
			swap(i++, k--, num);
		return true;

	}

	static void swap(int i, int j, int[] arr) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static int calc(int[] arr) {
		int cnt=0;
		for(int i=1; i<arr.length; i++) {
			cnt += Math.abs(arr[i-1]-arr[i]);
		}
		
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int max = Integer.MIN_VALUE;
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i - 1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		do {
			int result = calc(arr);
			max = max < result ? result : max;
		}while(np(arr));
		System.out.println(max);

	}
}
