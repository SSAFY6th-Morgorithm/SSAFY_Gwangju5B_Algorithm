package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_1012 {

	static int T;
	static int M;
	static int N;
	static int K;
	static int[][] map;
	static boolean[][] visited;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 유기농 배추(DFS-재귀)
		T = Integer.parseInt(br.readLine()); // 테스트 케이스
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			K = Integer.parseInt(st.nextToken()); // 배추 개수

			map = new int[M][N];
			while (K-- > 0) { // 지도에 배추 위치 표시하기
				st = new StringTokenizer(br.readLine(), " ");
				map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}

			visited = new boolean[M][N]; // 배추 방문 여부 검사
			int area = 0; // 지렁이가 필요한 구역 수
			for (int r = 0; r < M; r++) {
				for (int c = 0; c < N; c++) {
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

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void dfs(int r, int c) {
		visited[r][c] = true;

		for (int d = 0; d < 4; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];

			if (isIn(nr, nc) && map[nr][nc] == 1 && visited[nr][nc] == false) {
				dfs(nr, nc);
			}
		}
		return;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < M && c >= 0 && c < N;
	}
}
