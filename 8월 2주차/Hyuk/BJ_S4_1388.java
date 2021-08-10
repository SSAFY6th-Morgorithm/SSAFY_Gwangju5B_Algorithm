package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_S4_1388 {
	static int N, M;
	static int cnt = 0;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] data = new char[N][M];
		for (int i = 0; i < N; i++) {
			data[i] = br.readLine().toCharArray();
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (data[r][c] == '-') {
					cnt++;
					while (data[r][c] == '-') {
						c++;
						if (c == M) {
							break;
						}
					}
				}
			}
		}
		for (int c = 0; c < M; c++) {
			for (int r = 0; r < N; r++) {
				if (data[r][c] == '|') {
					cnt++;
					while (data[r][c] == '|') {
						r++;
						if (r == N) {
							break;
						}
					}
				}
			}
		}
		System.out.println(cnt);

	}
}
