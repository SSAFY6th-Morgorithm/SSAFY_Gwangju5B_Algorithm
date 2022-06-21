package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 83720	632
 * @see https://www.acmicpc.net/problem/23747
 * @author CHO
 * @category BFS
 * @info 시간초과 주의
 */
public class BOJ_23747_와드 {
	static StringTokenizer st;
	static int R, C;
	static int[][] dir = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // UDLR
	static char[][] map, result;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 1][C + 1];
		result = new char[R + 1][C + 1];
		for (int r = 1; r <= R; r++) {
			Arrays.fill(result[r], '#');
		}
		for (int r = 1; r <= R; r++) {
			String input = br.readLine();
			for (int c = 1; c <= C; c++) {
				map[r][c] = input.charAt(c - 1);
			}
		}
		st = new StringTokenizer(br.readLine());
		int HR = Integer.parseInt(st.nextToken());
		int HC = Integer.parseInt(st.nextToken());
		String input = br.readLine(); // 여행기록
		for (int i = 0; i < input.length(); i++) {
			char cur = input.charAt(i);
			if (cur == 'U') {
				HR += dir[0][0];
				HC += dir[0][1];
			} else if (cur == 'D') {
				HR += dir[1][0];
				HC += dir[1][1];
			} else if (cur == 'L') {
				HR += dir[2][0];
				HC += dir[2][1];
			}
			else if (cur == 'R') {
				HR += dir[3][0];
				HC += dir[3][1];
			}
			else {
				// 이동하지 않고 ward
				ward(HR, HC);
			}
			if (i == input.length() - 1) {
				result[HR][HC] = '.';
				for (int j = 0; j < dir.length; j++) {
					int nr = HR + dir[j][0];
					int nc = HC + dir[j][1];
					if (nr > 0 && nc > 0 && nr <= R && nc <= C && result[nr][nc]=='#') {
						result[nr][nc] = '.';
					}
				}
			}
		}
		for (int r = 1; r <= R; r++) {
			for (int c = 1; c <= C; c++) {
				sb.append(result[r][c]);
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}

	private static void ward(int x, int y) {
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { x, y });
		result[x][y] = '.';
		char alpha=map[x][y];
		while (!q.isEmpty()) {
			int cur[] = q.poll();
			x = cur[0];
			y = cur[1];
			for (int j = 0; j < dir.length; j++) {
				int nx = x + dir[j][0];
				int ny = y + dir[j][1];
				if (nx > 0 && ny > 0 && nx <= R && ny <= C && map[nx][ny]==alpha && result[nx][ny] == '#') {
					result[nx][ny] = '.';
					q.add(new int[] { nx, ny });

				}
			}
		}
	}

}