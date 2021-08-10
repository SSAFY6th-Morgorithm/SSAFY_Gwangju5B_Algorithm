package BFS_DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 섬의 개수 (DFS)
 */

public class BJ_S2_4963 {
	static int cnt, R, C;
	static int[][] deltas = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 }, { 1, -1 }, { -1, 1 }, { 1, 1 }, { -1, -1 } };
	static boolean[][] isVisited;
	static int[][] island;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			StringTokenizer st = new StringTokenizer(br.readLine());

			C = Integer.parseInt(st.nextToken()); // 너비
			R = Integer.parseInt(st.nextToken()); // 높이

			if (C == 0 && R == 0)
				break;

			island = new int[R][C];
			isVisited = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			/*
			 * for (int[] k : map) { for (int i : k) System.out.print(i+" ");
			 * System.out.println(); }
			 */

			cnt = 0;

			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (island[i][j] == 1 && !isVisited[i][j]) {
						DFS(i, j, cnt++);
					}
				}
			}
			System.out.println(cnt);
		}

	}

	private static void DFS(int r, int c, int cnt) {
		isVisited[r][c] = true;

		for (int i = 0; i < deltas.length; i++) {
			int nr = r + deltas[i][0];
			int nc = c + deltas[i][1];

			if (isIn(nr, nc) && island[nr][nc] == 1 && !isVisited[nr][nc])
				DFS(nr, nc, cnt);
		}

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}

}
