package CombAndPerm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_S2_10819_차이를최대로 {

	static int[] arr;
	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		answer = 0;
		perm(N, new int[N], new boolean[N]);
		System.out.println(answer);

	}

	private static void perm(int toChoose, int[] choosed, boolean[] isSelected) {
		if (toChoose == 0) {
			int sum = 0;
			for (int i = 1; i < N; i++) {
				sum += Math.abs(choosed[i] - choosed[i - 1]);
			}
			answer = Math.max(answer, sum);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!isSelected[i]) {
				isSelected[i] = true;
				choosed[choosed.length - toChoose] = arr[i];
				perm(toChoose - 1, choosed, isSelected);
				isSelected[i] = false;
			}
		}
	}

}
