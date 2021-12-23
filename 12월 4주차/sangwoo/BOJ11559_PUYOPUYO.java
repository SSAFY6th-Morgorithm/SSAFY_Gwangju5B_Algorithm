import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ11559_PUYOPUYO {
	private final static int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	private final static int R = 12, C = 6;
	static char[][] map;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[R][C];
		answer = 0;
		for (int r = 0; r < R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		List<int[]> groupList;
		do {
			groupList = new ArrayList<>();
			boolean[][] visited = new boolean[R][C];
			for (int r = 0; r < R; r++) {
				for (int c = 0; c < C; c++) {
					if (!visited[r][c] && map[r][c] != '.') {
						visited[r][c] = true;
						// 터질수 있는 그룹 모두 찾기
						if (findGroup(r, c, visited))
							groupList.add(new int[] { r, c });
					}
				}
			}
			if (!groupList.isEmpty()) {
				// 그룹 터트리기
				for (int[] position : groupList) {
					deleteGroup(position[0], position[1]);
				}
				answer++;
				// 남은 블록 밑으로 보내기
				drop();
			}
		} while (!groupList.isEmpty());
		System.out.println(answer);
	}

	private static boolean findGroup(int startR, int startC, boolean[][] visited) {
		// TODO Auto-generated method stub

		Queue<int[]> q = new LinkedList<>();
		int count = 1;
		char color = map[startR][startC];

		visited[startR][startC] = true;
		q.offer(new int[] { startR, startC });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1];
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == color) {
					visited[nr][nc] = true;
					count++;
					q.offer(new int[] { nr, nc });
				}
			}
		}
		if (count >= 4)
			return true;
		return false;
	}

	private static void deleteGroup(int startR, int startC) {
		// TODO Auto-generated method stub

		Queue<int[]> q = new LinkedList<>();
		char color = map[startR][startC];
		boolean[][] visited = new boolean[R][C];
		visited[startR][startC] = true;
		q.offer(new int[] { startR, startC });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1];
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && map[nr][nc] == color) {
					visited[nr][nc] = true;
					q.offer(new int[] { nr, nc });
				}
			}
			map[r][c] = '.';
		}
	}

	private static void drop() {
		// TODO Auto-generated method stub
		for (int r = R - 1; r >= 0; r--) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != '.') {
					int nr = r + 1;
					while (nr < R && map[nr][c] == '.')
						nr++;
					if (nr - 1 != r) {
						map[nr - 1][c] = map[r][c];
						map[r][c] = '.';
					}
				}
			}
		}
	}

}
