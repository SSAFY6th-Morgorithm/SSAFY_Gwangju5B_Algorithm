package simulation;

import java.io.*;
import java.util.*;

public class BJ_G5_1756_피자굽기 {

	static int D, N, start, end;
	static int oven[], pizza[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		D = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		oven = new int[D];
		pizza = new int[N];

		st = new StringTokenizer(br.readLine());
		int min = Integer.parseInt(st.nextToken());
		oven[0] = min;
		for (int i = 1; i < D; i++) {
			int no = Integer.parseInt(st.nextToken());
			min = no < min ? no : min;
			oven[i] = min;
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pizza[i] = Integer.parseInt(st.nextToken());
		}

		start = 0;
		end = D-1;
		for (int i = 0; i < N; i++) {
			solve(pizza[i]);
			if (end == -1) {
//				System.out.println(0);
				break;
			}
			start = 0;
		}

		System.out.println(end + 1);

	}

	private static void solve(int d) {

		while (start < end) {
			int mid = (start + end) / 2;
			if (oven[mid] < d) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		end--; // end에 피자 채웠으니까 -1
	}
}
