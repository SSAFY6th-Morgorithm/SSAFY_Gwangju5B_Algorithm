package simulation;

import java.io.*;
import java.util.*;

public class BJ_G4_2573_빙산 {

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	static int N, M;
	static int[][] iceMap;
	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		iceMap = new int[N][M];
		
		iceMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
            	iceMap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

		int year = 0;
		while (true) {
			int cnt = cntIce();
			if (cnt == 0) {
				System.out.println(0);
				break;
			} else {
				if(cnt > 1) {
					System.out.println(year);
					break;
				}
			}
			
			melt();
			year++;
		}

	}
	
	private static void melt() {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(iceMap[i][j] != 0) {
					q.offer(new Node(i,j));
					visited[i][j] = true; // 녹일 수 있는 땅 표시(0이 될 경우를 대비)
				}
			}
		}
		
		while (!q.isEmpty()) {
			Node cur = q.poll();
			
			int sea = 0;

			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && iceMap[nr][nc] == 0 && !visited[nr][nc]) {
					sea++;
				}
			}
			
			if(iceMap[cur.r][cur.c] - sea <0) iceMap[cur.r][cur.c] = 0;
			else iceMap[cur.r][cur.c] -= sea;
			
		}
	}

	private static int cntIce() {
		int iceCnt = 0;
		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (iceMap[i][j] != 0 && !visited[i][j]) {
					bfs(i, j, visited);
					iceCnt++;
				}
			}
		}
		return iceCnt;
	}
	
	
	private static void bfs(int r, int c, boolean[][] visited) {
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(r, c));
		visited[r][c] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int d = 0; d < deltas.length; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (isIn(nr, nc) && iceMap[nr][nc] > 0 && !visited[nr][nc]) {
					q.offer(new Node(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}
}
