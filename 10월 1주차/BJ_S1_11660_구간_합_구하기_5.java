package BJ_Practice.Silver;

import java.io.*;
import java.util.*;

public class BJ_S1_11660_구간_합_구하기_5 {
	static int N,K;
	static int[][] data;
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		data = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				data[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				data[i][j] = data[i][j] + data[i - 1][j] + data[i][j - 1] - data[i - 1][j - 1];
			}
		}

		for (int i = 0; i < K; i++) {
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			answer += data[x2][y2] + data[x1 - 1][y1 - 1] - data[x1 - 1][y2] - data[x2][y1 - 1];
			sb.append(answer).append("\n");
		}
		System.out.println(sb);

	}
}
