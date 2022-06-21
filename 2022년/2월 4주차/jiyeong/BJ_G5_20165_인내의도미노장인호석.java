package feb22_implementation;

import java.io.*;
import java.util.*;

public class BJ_G5_20165_인내의도미노장인호석 {

	static int N, M, R, cnt;
	static int[][] map;
	static char[][] status;
	static int[][] deltas = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		status = new char[N][M];
		cnt = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				status[i][j] = 'S';
			}
		}

		for (int i = 0; i < R; i++) {
			// 공격
			st = new StringTokenizer(br.readLine());
			int ar = Integer.parseInt(st.nextToken()) - 1;
			int ac = Integer.parseInt(st.nextToken()) - 1;
			String ad = st.nextToken();

			attack(ar, ac, ad);

			// 수비
			st = new StringTokenizer(br.readLine());
			int dr = Integer.parseInt(st.nextToken()) - 1;
			int dc = Integer.parseInt(st.nextToken()) - 1;
			status[dr][dc] = 'S';

		}

		sb.append(cnt).append("\n");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(status[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}

	private static void attack(int ar, int ac, String ad) {
		if (status[ar][ac] == 'F')
			return;
		status[ar][ac] = 'F';
		cnt++;
		int point = map[ar][ac] - 1;
		int d = 0;

		switch (ad) {
		case "E":
			d = 0;
			break;
		case "W":
			d = 1;
			break;
		case "S":
			d = 2;
			break;
		case "N":
			d = 3;
			break;
		}

		while (point > 0) {
			int nr = ar + deltas[d][0];
			int nc = ac + deltas[d][1];

			if (isIn(nr, nc)) {
				if (status[nr][nc] == 'F') {
					point--;
					ar = nr;
					ac = nc;
					continue;
				}
				point--;
				status[nr][nc] = 'F';
				cnt++;
				ar = nr;
				ac = nc;
				int newPoint = map[nr][nc] - 1;
				if (point < newPoint) {
					point = newPoint;
				}
			} else {
				break;
			}
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
