package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G5_16469_소년점프 {
	static int R, C;
	static int[][] map;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int[][][] visited;
	static StringTokenizer st;

	static class RC {
		int r, c;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		visited = new int[R][C][3];

		for (int r = 0; r < R; r++) {
			String str = br.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}

		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			bfs(new RC(r, c), i);
		}

		int ans = 99999;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int a = visited[i][j][0];
				int b = visited[i][j][1];
				int c = visited[i][j][2];
				if (a > 0 && b > 0 && c > 0) {
					visited[i][j][0] = Math.max(a, Math.max(b, c));
					ans = Math.min(ans, visited[i][j][0]);
				} else
					visited[i][j][0] = -1;
			}
		}
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (visited[i][j][0] == ans) {
					cnt++;
				}
			}
		}
		if (cnt == 0)
			System.out.println(-1);
		else {
			System.out.println(ans - 1);
			System.out.println(cnt);
		}

	}

	static void bfs(RC rc, int dim) {
		Queue<RC> queue = new LinkedList<>();
		queue.offer(rc);
		visited[rc.r][rc.c][dim] = 1;

		while (!queue.isEmpty()) {
			RC temp = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = temp.r + deltas[i][0];
				int nc = temp.c + deltas[i][1];

				if (isIn(nr, nc) && map[nr][nc] == 0 && visited[nr][nc][dim] == 0) {
					queue.offer(new RC(nr, nc));
					visited[nr][nc][dim] = visited[temp.r][temp.c][dim] + 1;
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < R && c < C;
	}
}
