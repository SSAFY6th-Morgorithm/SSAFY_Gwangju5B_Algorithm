package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S3_14889 {
	static int N;
	static int[][] data;
	static boolean[] select;
	
	static int min = Integer.MAX_VALUE;

	static void Combination(int cnt, int start) {
		if (cnt == N / 2) {
			min = Math.min(min, getAbility());
			return;
		}

		for (int i = start; i <= N; i++) {
			if (select[i] == true)
				continue;
			select[i] = true;
			Combination(cnt + 1, i + 1);
			select[i] = false;

		}
	}

	static int getAbility() {
		int sumStart = 0;
		int sumLink = 0;
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if (select[i] && select[j])
					sumStart += data[i][j];
				if (!select[i] && !select[j])
					sumLink += data[i][j];
			}
		}
		return Math.abs(sumStart - sumLink);

	}

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		try {
			N = Integer.parseInt(br.readLine());
			data = new int[N+1][N+1];
			select = new boolean[N + 1];
			for (int r = 1; r <= N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 1; c <= N; c++) {
					data[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			Combination(0, 1);
			System.out.println(min);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
