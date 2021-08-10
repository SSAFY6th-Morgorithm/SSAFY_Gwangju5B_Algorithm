package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_4963 {

	static int W;
	static int H;
	static int[][] map;
	static boolean[][] visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 섬의 개수(DFS-재귀) -> bj_1012와 풀이방식은 같고 대각선 방문이 추가됨

		while (true) {
			st = new StringTokenizer(br.readLine(), " ");
			W = Integer.parseInt(st.nextToken()); // 너비
			H = Integer.parseInt(st.nextToken()); // 높이
			if (W == 0 && H == 0) { // 입력 끝
				break;
			}

			map = new int[H][W];
			for (int r = 0; r < H; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < W; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			visited = new boolean[H][W]; // 섬 방문 여부 검사
			int area = 0; // 섬 개수
			for (int r = 0; r < H; r++) {
				for (int c = 0; c < W; c++) {
					if (map[r][c] == 1 && visited[r][c] == false) {
						dfs(r, c);
						area++;
					}
				}
			}
			sb.append(area).append("\n");
		}
		System.out.print(sb);
	}

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 } };

	public static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 8; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			if (isIn(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
				dfs(nr, nc);
			}
		}
		return;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < H && c >= 0 && c < W;
	}
}
