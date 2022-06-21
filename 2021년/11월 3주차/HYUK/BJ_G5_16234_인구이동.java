package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G5_16234_인구이동 {
	static int N, L, R;
	static int count;
	static int[][] map;
	static boolean[][] open;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
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
		int answer=0;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[N][N];


		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			open = new boolean[N][N];
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!open[i][j]) {
						if (checkOpen(new RC(i, j)))
							cnt++;
					}
				}
			}
			if(cnt == 0) break;
			answer++;
		}
//		for (int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(map[i]));
//		}
		System.out.println(answer);
	}

	static boolean checkOpen(RC rc) {
		Queue<RC> queue = new LinkedList<>();
		Queue<RC> subqueue = new LinkedList<>();

		queue.offer(rc);
		subqueue.offer(rc);
		open[rc.r][rc.c] = true;
		count = 1;
		int sum = map[rc.r][rc.c];

		while (!queue.isEmpty()) {
			RC temp = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nr = temp.r + deltas[i][0];
				int nc = temp.c + deltas[i][1];

				if (isIn(nr, nc)) {
					int gap = Math.abs(map[nr][nc] - map[temp.r][temp.c]);
					if (L <= gap && gap <= R && !open[nr][nc]) {
						queue.offer(new RC(nr, nc));
						subqueue.offer(new RC(nr, nc));

						open[nr][nc] = true;
						sum += map[nr][nc];
						count++;
					}
				}
			}

		}
		while (!subqueue.isEmpty()) {
			RC temp = subqueue.poll();
			map[temp.r][temp.c] = sum / count;
		}

		if (count > 1)
			return true;
		return false;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && c < N && r < N;
	}
}
