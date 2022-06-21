package BJ_Practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_G5_15683_감시 {
	static int N, M;
	static int answer = 0;
	static int count = 0;
	static int map[][];
	static int[][] deltas = { { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 0 } };
	static int[][] direction = { { 0 }, { 0, 2 }, { 0, 1 }, { 0, 1, 2 }, { 0, 1, 2, 3 } };
	static List<CCTV> list = new ArrayList<>();
	static StringTokenizer st;

	static class CCTV {
		int r, c, dir;

		public CCTV(int r, int c, int dir) {
			super();
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0 && map[i][j] < 6)
					list.add(new CCTV(i, j, map[i][j] - 1));
				else if (map[i][j] == 0)
					count++;
			}
		}

		for (int i = 0; i < 4; i++) {
			observe(0, i, map);
		}
		System.out.println(count - answer);

	}

	static void observe(int cctv, int dir, int[][] map) {
		if (cctv == list.size()) {
			int count = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == -1)
						count++;
				}
			}
			answer = Math.max(count, answer);
			return;
		}
		int[][] temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			temp[i] = Arrays.copyOf(map[i], M);
		}

		int cctvdir = list.get(cctv).dir;
		for (int j = 0; j < direction[cctvdir].length; j++) {
			int nr = list.get(cctv).r;
			int nc = list.get(cctv).c;
			while (isIn(nr, nc) && map[nr][nc] < 6) {
				nr += deltas[(direction[cctvdir][j] + dir) % 4][0];
				nc += deltas[(direction[cctvdir][j] + dir) % 4][1];
				if (isIn(nr, nc) && temp[nr][nc] == 0) {
					temp[nr][nc] = -1;
				}
			}
		}
		for (int i = 0; i < 4 ; i++) {
			observe(cctv+1,i,temp);
		}

	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
