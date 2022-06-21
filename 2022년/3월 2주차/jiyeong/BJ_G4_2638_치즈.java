package mar08_implementation;

import java.io.*;
import java.util.*;

public class BJ_G4_2638_치즈 {

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<Node> cheese;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cheese = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) { // 치즈 저장
					cheese.add(new Node(i, j));
				}
			}
		}

		int time = 0;

		while (!cheese.isEmpty()) {
			time++;
			visited = new boolean[N][M];
			bfs();
			melt(); // 치즈 녹이기
		}

		System.out.println(time);
	}

	static void melt() {
		for (int i = 0; i < cheese.size(); i++) {
			Node cur = cheese.get(i);
			int cnt = 0;

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + deltas[d][0];
				int nc = cur.c + deltas[d][1];

				if (map[nr][nc] == -1)
					cnt++;
			}

			if (cnt >= 2) { // 외부 공기와 2변 이상 접촉
				map[cur.r][cur.c] = 0;
				cheese.remove(i);
				i--;
			}
		}
	}

	public static void bfs() {
		Queue<Node> q = new LinkedList<>();

		q.add(new Node(0, 0));
		visited[0][0] = true;
		map[0][0] = -1;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nr = cur.r + deltas[i][0];
				int nc = cur.c + deltas[i][1];

				if (isIn(nr, nc) && !visited[nr][nc] && map[nr][nc] != 1) {
					q.add(new Node(nr, nc)); // 공기 큐에 넣기
					map[nr][nc] = -1; // 외부 공기 표시
					visited[nr][nc] = true;
				}
			}
		}
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < M;
	}

}