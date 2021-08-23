package conquer;

import java.io.*;
import java.util.*;

public class BJ_S2_1780_종이의개수 {

	static int N, zero, one, mone;
	static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(0, 0, N);
		System.out.println(mone);
		System.out.println(zero);
		System.out.println(one);
		
		br.close();

	}

	private static void recur(int r, int c, int len) {
		if (check(r, c, len)) {
			if (arr[r][c] == 0)
				zero++;
			else if (arr[r][c] == 1)
				one++;
			else
				mone++;
			return;
		}

		int next = len / 3;

		// 상
		recur(r, c, next);
		recur(r, c + next, next);
		recur(r, c + 2 * next, next);

		// 중
		recur(r + next, c, next);
		recur(r + next, c + next, next);
		recur(r + next, c + 2 * next, next);

		// 하
		recur(r + 2 * next, c, next);
		recur(r + 2 * next, c + next, next);
		recur(r + 2 * next, c + 2 * next, next);

	}

	private static boolean check(int r, int c, int len) {
		int num = arr[r][c];

		for (int i = r; i < r + len; i++) {
			for (int j = c; j < c + len; j++) {
				if (arr[i][j] != num)
					return false;
			}
		}
		return true;
	}
}
