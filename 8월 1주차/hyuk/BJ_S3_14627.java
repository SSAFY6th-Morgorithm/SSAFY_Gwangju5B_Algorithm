package BJ_Practice.Silver;

import java.io.*;
import java.util.*;

public class BJ_S3_14627 {
	private static int K;
	private static int N;
	private static long Len = 0;

	private static int cut(long[] arr, long n) {
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			long size = n;
			while (size <= arr[i]) {
				size += n;
				cnt++;
			}
		}

		return cnt;
	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			long[] arr = new long[K];
			for (int i = 0; i < K; i++) {
				arr[i] = Long.parseLong(br.readLine());
			}
			Arrays.sort(arr);
			long s = 0, e = 0;
			if (cut(arr, arr[K - 1]) < N) {

				s = 0;
				e = arr[K - 1];
				while (s < e) {
					long mid = (s + e) / 2;
					int num = cut(arr, mid);
					if (num < N)
						e = mid;
					else if (num >= N) {
						s = mid + 1;
						Len = mid;
					}

				}
			} else
				Len = arr[K - 1];

			long sum = 0;
			for(int i=0; i<K;i++)
				sum+=arr[i];
			System.out.println(sum-(Len*N));

	
		} catch (Exception e) {
		}
	}
}
