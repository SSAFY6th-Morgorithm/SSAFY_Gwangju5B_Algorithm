package BJ_Practice.Gold;

import java.io.*;
import java.util.*;
import java.util.Map.*;

public class BJ_G3_1300 {
	private static int N;
	private static long K;

	private static long underCounter(long num) {
		long cnt = 0;
		for (int i = 1; i <= N; i++) {
			cnt += Math.min(num / i, N);
		}
		return cnt;
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextLong();

		long s = 1;
		long e = K;
		long ans = 1;
		while (s <= e) {
			long mid = (s + e) / 2;
			long cnt = underCounter(mid);
			if (cnt >= K) {
				ans = mid;
				e = mid - 1;
			} else {
				s = mid + 1;
			}

		}
		System.out.println(ans);
	}

}
