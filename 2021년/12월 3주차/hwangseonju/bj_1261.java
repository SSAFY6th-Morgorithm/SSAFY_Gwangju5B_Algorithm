package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_1261 {

	static int N; // 세로
	static int M; // 가로
	static int[][] map; // 미로
	static int[][] d; // 최소 벽 부시기
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 알고스팟
		st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		d = new int[N][M];

		for (int r = 0; r < N; r++) {
			String str = br.readLine();
			for (int c = 0; c < M; c++) {
				map[r][c] = str.charAt(c) - '0';
			}
		}

		for(int r=0; r<N; r++) {
			Arrays.fill(d[r], Integer.MAX_VALUE);
		}
		
		search();
		
		System.out.println(d[N-1][M-1]);

	}

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static void search() {
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		pq.offer(new Dot(0, 0, map[0][0])); // 출발
		d[0][0] = 0;

		while (!pq.isEmpty()) {
			Dot current = pq.poll();
			
			if (current.x == N - 1 && current.y == M - 1)
				break;

			for (int dir = 0; dir < 4; dir++) {
				int nr = current.x + deltas[dir][0];
				int nc = current.y + deltas[dir][1];

				if (isIn(nr, nc)) {
					if (d[nr][nc] > d[current.x][current.y] + map[nr][nc]) {
						d[nr][nc] = d[current.x][current.y] + map[nr][nc];
						pq.offer(new Dot(nr, nc, d[nr][nc]));
					}
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

	static class Dot implements Comparable<Dot> {
		int x, y, cost;

		public Dot(int x, int y, int cost) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public int compareTo(Dot o) {
			if (this.cost > o.cost)
				return 1;
			return -1;
		}
	}
}
