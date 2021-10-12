package BFS_DFS;

import java.io.*;
import java.util.*;

public class BJ_G5_2636_치즈 {

	static class Pos {
		int r, c;

		public Pos(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int R, C, cheese;
	static int[][] map;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		solve();
	}

	static void solve() {
		int time = 0;
		int prev = 0;
		
		while (true) {
			bfs();
			
			if (cheese == 0)
				break;
			
			prev = cheese;
			time++;
		}
		
		System.out.println(time);
		System.out.println(prev);

	}

	static void bfs() {
		boolean visited[][] = new boolean[R][C];
		Queue<Pos> q = new LinkedList<>();

		q.offer(new Pos(0, 0));
		visited[0][0] = true;

		int copiedMap[][] = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				copiedMap[i][j] = map[i][j];
			}
		}

		cheese = 0;
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					visited[nr][nc] = true;
					if (map[nr][nc] == 1) {
						copiedMap[nr][nc] = 0;
						cheese++;
					} else {
						q.offer(new Pos(nr, nc));
					}
				}
			}
		}
		map = copiedMap;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
}