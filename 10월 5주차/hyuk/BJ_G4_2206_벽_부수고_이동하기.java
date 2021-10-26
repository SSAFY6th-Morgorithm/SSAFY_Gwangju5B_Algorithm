package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G4_2206_벽_부수고_이동하기 {
	static int N, M;
	static int[][] map;
	static int[][][] visited;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static StringTokenizer st;

	static class RC {
		int r, c, cnt;

		public RC(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public RC(int r, int c, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M][2];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<RC> queue = new LinkedList<>();
		queue.offer(new RC(0, 0, 1));
		visited[0][0][1] = visited[0][0][0] = 1;

		while (!queue.isEmpty()) {
			RC temp = queue.poll();

			if(temp.r == N-1 && temp.c == M-1) return visited[temp.r][temp.c][temp.cnt];
			
			for (int i = 0; i < 4; i++) {
				int nr = temp.r + deltas[i][0];
				int nc = temp.c + deltas[i][1];
				if (isIn(nr, nc)) {
					if (map[nr][nc] == 0 && visited[nr][nc][temp.cnt] == 0) {
						queue.offer(new RC(nr, nc, temp.cnt));
						visited[nr][nc][temp.cnt] = visited[temp.r][temp.c][temp.cnt] + 1;
					}
					if (map[nr][nc] == 1 && temp.cnt > 0 && visited[nr][nc][temp.cnt - 1] == 0) {
						queue.offer(new RC(nr, nc, temp.cnt - 1));
						visited[nr][nc][temp.cnt - 1] = visited[temp.r][temp.c][temp.cnt] + 1;
					}
				}
			}
		}
		return -1;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
