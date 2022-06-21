import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ2933_미네랄 {
	static int R, C;
	static char[][] cave;
	static boolean isLeft;
	private final static int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ss = br.readLine().split(" ");
		R = Integer.parseInt(ss[0]);
		C = Integer.parseInt(ss[1]);
		cave = new char[R][C];

		for (int r = 0; r < R; r++)
			cave[r] = br.readLine().toCharArray();

		int N = Integer.parseInt(br.readLine());
		ss = br.readLine().split(" ");

		isLeft = true;
		for (int i = 0; i < N; i++) {
			throwStick(R - Integer.parseInt(ss[i]));
		}
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++)
				sb.append(cave[r][c]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void throwStick(int row) {
		int col = -1;
		if (isLeft) { // 왼쪽부터 시작
			col = 0;
			while (col < C) {
				if (cave[row][col] == 'x') {
					cave[row][col] = '.';
					break;
				}
				col++;
			}
		} else {
			col = C - 1;
			while (col >= 0) {
				if (cave[row][col] == 'x') {
					cave[row][col] = '.';
					break;
				}
				col--;
			}
		}
		// 미네랄 검사한 후 떨어질 미네랄 있으면 미네랄 떨구기
		checkCave();
		isLeft = !isLeft;
	}

	static void checkCave() {
		List<int[]> clusterList = new ArrayList<>();
		boolean[][] visited = new boolean[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (cave[r][c] == 'x' && !visited[r][c]) {
					// 행 번호가 가장 큰게 R - 1이 아니라면 클러스터 리스트에 넣기
					int largestRow = checkLargestRow(r, c, visited);
					if (largestRow != R - 1)
						clusterList.add(new int[] { r, c });
				}
			}
		}
		// 클러스터 리스트를 돌면서 클러스터들을 중력에 맞게 아래로 내려주기
		// 클러스트의 노드들을 순회하면서 빈칸의 개수가 0이 아니면서 가장 작은것 찾기
		// 클러스트의 노드들을 순회하면서 빈칸의 개수만큼 내려주기
		for (int[] cluster : clusterList) {
			dropCluster(cluster[0], cluster[1]);
		}
	}

	static void dropCluster(int startR, int startC) {
		int shortestSpace = Integer.MAX_VALUE;
		Queue<int[]> q = new LinkedList<>();
		List<int[]> node = new ArrayList<>();
		boolean[][] visited = new boolean[R][C];
		visited[startR][startC] = true;
		q.add(new int[] { startR, startC });
		node.add(new int[] { startR, startC });

		while (!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0], c = current[1];

			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && cave[nr][nc] == 'x') {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
					node.add(new int[] { nr, nc });
				}
			}
		}
		for (int[] current : node) {
			int r = current[0], c = current[1];
			int height = 0;
			r++;
			while (r < R && cave[r][c] == '.') {
				height++;
				r++;
			}
			if (r < R && visited[r][c])
				height = 0;
			if (height != 0 && shortestSpace > height)
				shortestSpace = height;
		}
		for (int[] current : node) {
			int r = current[0], c = current[1];
			cave[r][c] = '.';
		}
		for (int[] current : node) {
			int r = current[0], c = current[1];
			cave[r + shortestSpace][c] = 'x';
		}
	}

	static int checkLargestRow(int startR, int startC, boolean[][] visited) {
		int largestRow = Integer.MIN_VALUE;
		Queue<int[]> q = new LinkedList<>();
		visited[startR][startC] = true;
		q.add(new int[] { startR, startC });
		while (!q.isEmpty()) {
			int[] current = q.poll();
			int r = current[0], c = current[1];
			if (largestRow < r)
				largestRow = r;
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && !visited[nr][nc] && cave[nr][nc] == 'x') {
					visited[nr][nc] = true;
					q.add(new int[] { nr, nc });
				}
			}
		}
		return largestRow;
	}
}
