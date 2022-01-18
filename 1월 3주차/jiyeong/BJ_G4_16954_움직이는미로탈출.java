package jan18_graph;

import java.io.*;
import java.util.*;

public class BJ_G4_16954_움직이는미로탈출 {

	static class Node {
		int r, c;

		public Node(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	static int[][] deltas = { { 0, 0 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 },
			{ -1, -1 } };
	static int[][] map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[8][8];

		for (int i = 0; i < 8; i++) {
			String s = br.readLine();
			for (int j = 0; j < 8; j++) {
				if (s.charAt(j) == '.') {
					map[i][j] = 0;
				} else if (s.charAt(j) == '#') {
					map[i][j] = -1;
				}
			}
		}

		bfs(7, 0);
	}

	private static void bfs(int r, int c) {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(r, c));

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Node cur = q.poll();

				if (map[cur.r][cur.c] == -1)
					continue;

				for (int d = 0; d < deltas.length; d++) {
					int nr = cur.r + deltas[d][0];
					int nc = cur.c + deltas[d][1];

					// 도착
					if (nr == 0 && nc == 7) {
						System.out.println(1);
						return;
					}

					if (isIn(nr, nc) && map[nr][nc] != -1) {
						q.add(new Node(nr, nc));
					}
				}
			}

			// 벽내리기
			for (int i = 7; i >= 0; i--) {
				for (int j = 0; j <= 7; j++) {
					if (i - 1 == -1) // 첫줄
						map[i][j] = 0;
					else
						map[i][j] = map[i - 1][j];
				}
			}
		}
		System.out.println(0);
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < 8 && c >= 0 && c < 8;
	}
}
