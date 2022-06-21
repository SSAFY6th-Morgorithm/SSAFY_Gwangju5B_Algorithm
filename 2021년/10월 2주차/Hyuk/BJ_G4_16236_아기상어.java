package BJ_Practice;

import java.io.*;
import java.util.*;

public class BJ_G4_16236_아기상어 {
	static int N;
	static int[][] ocean;
	static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };
	static Fish shark;
	static int eatCnt = 0;
	static int moveDist = 0;
	static StringTokenizer st;

	static class Fish {
		int r, c;
		int size;

		public Fish(int r, int c, int size) {
			super();
			this.r = r;
			this.c = c;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Fish [r=" + r + ", c=" + c + ", size=" + size + "]";
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		ocean = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				ocean[i][j] = Integer.parseInt(st.nextToken());
				if (ocean[i][j] == 9)
					shark = new Fish(i, j, 2);
			}
		}
		Fish temp = null;

		while ((temp = findFish()) != null) {
			ocean[shark.r][shark.c] = 0;

			shark.r = temp.r;
			shark.c = temp.c;
			eatCnt++;
			if (eatCnt == shark.size) {
				shark.size++;
				eatCnt = 0;
			}

			ocean[temp.r][temp.c] = 9;
		}
		System.out.println(moveDist);
	}

	static Fish findFish() {
		Queue<Fish> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];

		queue.offer(shark);
		visited[shark.r][shark.c] = true;
		boolean flag = false;
		int cnt = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			cnt++;
			for (int step = 0; step < size; step++) {
				Fish temp = queue.poll();

				for (int i = 0; i < 4; i++) {
					int nr = temp.r + deltas[i][0];
					int nc = temp.c + deltas[i][1];

					if (isIn(nr, nc) && !visited[nr][nc] && shark.size >= ocean[nr][nc]) {
						queue.offer(new Fish(nr, nc, ocean[nr][nc]));
						visited[nr][nc] = true;
						if (ocean[nr][nc] != 0 && ocean[nr][nc] < shark.size) {
							flag = true;
						}
					}
				}
			}

			if (flag)
				break;
		}
		if (flag) {
			int size = queue.size();
			Fish eat = new Fish(100, 100, 0);
			for (int i = 0; i < size; i++) {
				Fish temp = queue.poll();
				if (temp.size == 0 || temp.size == shark.size)
					continue;
				if (temp.r < eat.r)
					eat = temp;
				else if (temp.r == eat.r && temp.c < eat.c)
					eat = temp;
			}
			moveDist += cnt;
			return eat;
		} else
			return null;
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}
}
