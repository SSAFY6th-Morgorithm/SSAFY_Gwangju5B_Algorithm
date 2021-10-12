package BFS_DFS;

import java.io.*;
import java.util.*;

public class BJ_G4_1600_말이되고픈원숭이 {

	static class Monkey {
		int r, c, k; // 좌표, 말로 이동할 수 있는 회수

		public Monkey(int r, int c, int k) {
			super();
			this.r = r;
			this.c = c;
			this.k = k;
		}
	}

	static int K, R, C;
	static int[][] map;

	static int[][] deltasM = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][] deltasH = { { -2, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(bfs());
	}

	static int bfs() {
		boolean[][][] visited = new boolean[R][C][K + 1]; // row, col, 말/원숭이
		Queue<Monkey> q = new LinkedList<>();

		q.offer(new Monkey(0, 0, 0));
		visited[0][0][0] = true;

		int depth = 0; // 횟수
		while (!q.isEmpty()) {
			int size = q.size(); // 현재 큐의 크기만큼만 돌기
			while (size-- > 0) {
				Monkey cur = q.poll();

				// 목적지
				if (cur.r == R - 1 && cur.c == C - 1)
					return depth;

				// 원숭이로 탐색
				move(deltasM, cur, true, visited, q);
				// 말로 탐색
				if (cur.k < K) {
					move(deltasH, cur, false, visited, q);
				}
			}
			depth++;
		}

		return -1;
	}

	static void move(int[][] deltas, Monkey cur, boolean isMonkey, boolean[][][] visited, Queue<Monkey> q) {
		for (int d = 0; d < deltas.length; d++) {
			int nr = cur.r + deltas[d][0];
			int nc = cur.c + deltas[d][1];

			if(isIn(nr,nc) && map[nr][nc] == 0) { // 땅이면 
				 int horse = 0;
				 if(isMonkey) {
					 horse = cur.k;
				 }else { // 말로 오면 횟수 증가
					 horse = cur.k+1;
				 }
				 
				 if(!visited[nr][nc][horse]) { // 원숭인지 말인지 확인 후 방문처리
					 visited[nr][nc][horse] = true;
					 q.offer(new Monkey(nr,nc,horse));
				 }
			 }
		}
	}

	static boolean isIn(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

}