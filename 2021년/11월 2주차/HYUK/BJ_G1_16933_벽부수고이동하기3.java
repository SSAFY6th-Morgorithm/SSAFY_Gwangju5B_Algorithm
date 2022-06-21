package BJ_Practice.Gold;

import java.io.*;
import java.util.*;

public class BJ_G1_16933_벽부수고이동하기3 {
	static int N, M, K;
	static int[][] map;
	static int[][][] visited;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static StringTokenizer st;

	static class RC {
		int r, c, count;

		public RC(int r, int c, int count) {
			super();
			this.r = r;
			this.c = c;
			this.count = count;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
	
			
		map = new int[N][M];
		visited = new int[N][M][K + 1];
		String str = "";
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		if(N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		System.out.println(bfs());
	}

	static int bfs() {
		Queue<RC> queue = new LinkedList<>();
		queue.offer(new RC(0, 0, 0));
		for (int i = 0; i <= K; i++) {
			visited[0][0][i] = 1;
		}
		int size = 0;
		int cnt = 0;
		while (!queue.isEmpty()) {
			size = queue.size();
			cnt++;
			for (int step = 0; step < size; step++) {

				RC temp = queue.poll();

				if (temp.r == N - 1 && temp.c == M - 1) {
					return ++visited[temp.r][temp.c][temp.count];
				}

				for (int i = 0; i < 4; i++) {
					int nr = temp.r + deltas[i][0];
					int nc = temp.c + deltas[i][1];

					if (isIn(nr, nc) && visited[nr][nc][temp.count] == 0 && map[nr][nc] == 0) {
						queue.offer(new RC(nr, nc, temp.count));
						visited[nr][nc][temp.count] = cnt;
					}
					if (isIn(nr, nc) && temp.count < K && map[nr][nc] == 1 && visited[nr][nc][temp.count + 1] == 0) {
						if (cnt % 2 == 1) {
							queue.offer(new RC(nr, nc, temp.count + 1));
							visited[nr][nc][temp.count + 1] = cnt;
						}else {
							queue.offer(new RC(temp.r,temp.c,temp.count));
							visited[temp.r][temp.c][temp.count] = cnt;
						}
					}

				}
			}
		}

		return -1;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
