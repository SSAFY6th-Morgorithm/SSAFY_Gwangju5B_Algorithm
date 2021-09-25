package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_14503 {
	static int N, M;
	static RC curr = new RC(0, 0);
	static int d;
	static int[][] map;
	static boolean[][] visited;
	static int count = 0;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static StringTokenizer st;

	static class RC {
		int r, c;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "RC [r=" + r + ", c=" + c + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		curr.r = Integer.parseInt(st.nextToken());
		curr.c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited[curr.r][curr.c] = true;

		while (true) {
			count++;
			while (move1()) {
				if (move2()) {
					if (move3()) {
						System.out.println(count);
						return;
					}
				}
			}
		}
	}

	static boolean move1() {
		int direction = (d + 3) % 4;
		int nr = curr.r + deltas[direction][0];
		int nc = curr.c + deltas[direction][1];

		if (isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc]) {
			visited[nr][nc] = true;
			curr.r = nr;
			curr.c = nc;
			d = direction;
			return false;
		}
		return true;
	}

	static boolean move2() {
		int direction = d;
		int r = curr.r;
		int c = curr.c;
		for (int i = 0; i < 4; i++) {
			direction = (direction + 3) % 4;
			int nr = r + deltas[direction][0];
			int nc = c + deltas[direction][1];
			if (isIn(nr, nc) && map[nr][nc] == 0 && !visited[nr][nc]) {
				return false;
			}
			d = direction;

		}
		return true;
	}

	static boolean move3() {
		int direction = (d + 2) % 4;
		int nr = curr.r + deltas[direction][0];
		int nc = curr.c + deltas[direction][1];
		if (isIn(nr, nc) && map[nr][nc] == 0) {
			curr.r = nr;
			curr.c = nc;
			return false;
		}
		return true;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
