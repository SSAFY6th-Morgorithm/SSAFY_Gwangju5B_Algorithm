package week9_4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//시간제한: 초
public class BOJ_G5_로봇청소기 {
	private static int[][] map;
	static int[][] vector = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int answer = 1;
	private static int R;
	private static int C;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int MR = Integer.parseInt(st.nextToken());
		int MC = Integer.parseInt(st.nextToken());
		int direction = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(MR, MC, direction, 0);

	}

	private static void dfs(int mr, int mc, int d, int count) {
		map[mr][mc] = 2; // 청소 하기

		if (count == 4) {
			int d2 = (d + 2) % 4;
			int nr = mr + vector[d2][0];
			int nc = mc + vector[d2][1];
			if (map[nr][nc] == 2)
				dfs(nr, nc, d, 0);
			else
				System.out.println(answer);
				System.exit(0);
		}

		d--;
		if (d == -1)
			d = 3;

		int nr = mr + vector[d][0];
		int nc = mc + vector[d][1];
		if (isIn(nr, nc) && map[nr][nc] == 0) { // 회전하고 앞에 있는 공간이 비어있으면
			answer++;
			dfs(nr, nc, d, 0);
		} else {
			dfs(mr, mc, d, count + 1);
		}

	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
