import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ21609_상어중학교 {
	static class BlockGroup {
		int size, numOfRainbow, r, c;

		public BlockGroup(int size, int numOfRainbow, int r, int c) {
			this.size = size;
			this.numOfRainbow = numOfRainbow;
			this.r = r;
			this.c = c;
		}
	}

	private static final int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M, Answer;
	static int[][] grid;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] ss = br.readLine().split(" ");
		N = Integer.parseInt(ss[0]);
		M = Integer.parseInt(ss[1]);
		Answer = 0;
		grid = new int[N][N];
		for (int r = 0; r < N; r++) {
			ss = br.readLine().split(" ");
			for (int c = 0; c < N; c++) {
				grid[r][c] = Integer.parseInt(ss[c]);
			}
		}
		BlockGroup biggestGroup;
		do {
			biggestGroup = null;
			boolean[][] visited = new boolean[N][N];
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c] && grid[r][c] > 0) {
						// find biggestGroup
						BlockGroup current = getBiggestGroup(r, c, visited);
						if (current == null)
							continue;
						if (biggestGroup == null) {
							biggestGroup = current;
						} else if (biggestGroup.size == current.size) {
							if (biggestGroup.numOfRainbow == current.numOfRainbow) {
								if (biggestGroup.r == current.r) {
									if (biggestGroup.c < current.c)
										biggestGroup = current;
								} else if (biggestGroup.r < current.r)
									biggestGroup = current;
							} else if (biggestGroup.numOfRainbow < current.numOfRainbow)
								biggestGroup = current;
						} else if (biggestGroup.size < current.size)
							biggestGroup = current;
					}
				}
			}
			// do game with biggestGroup
			playGame(biggestGroup);
		} while (biggestGroup != null);
		System.out.println(Answer);
	}

	private static void playGame(BlockGroup biggestGroup) {
		if (biggestGroup == null)
			return;
		// deleting block group
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		int startR = biggestGroup.r, startC = biggestGroup.c;
		int blockColor = grid[startR][startC];
		visited[startR][startC] = true;
		q.add(new int[] { startR, startC });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1];
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if (grid[nr][nc] == 0 || grid[nr][nc] == blockColor) {
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc });
					}
				}
			}
			// -2 means empty space
			grid[r][c] = -2;
		}
		// add scores
		Answer += Math.pow(biggestGroup.size, 2);
		// drop through empty space except black block
		drop();
		// rotate 90 degree with anti clockwise
		rotate();
		// drop again
		drop();
	}

	private static void rotate() {
		// (1, 2) -> (2, 1), (2, 1) -> (3, 2), (3, 2) -> (2, 3), (2, 3) -> (1, 2)
		int[][] tmp = new int[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				tmp[N - 1 - c][r] = grid[r][c];
			}
		}
		grid = tmp;
	}

	private static void drop() {
		for (int r = N - 1; r >= 0; r--) {
			for (int c = 0; c < N; c++) {
				if (grid[r][c] >= 0) {
					int nr = r + 1;
					while (nr < N && grid[nr][c] == -2) {
						nr++;
					}
					if (nr - 1 != r) {
						grid[nr - 1][c] = grid[r][c];
						grid[r][c] = -2;
					}
				}
			}
		}
	}

	private static BlockGroup getBiggestGroup(int startR, int startC, boolean[][] visited) {
		Queue<int[]> q = new LinkedList<>();
		List<int[]> rainbowList = new ArrayList<>();
		int size = 1;
		int blockColor = grid[startR][startC];
		visited[startR][startC] = true;
		q.add(new int[] { startR, startC });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1];
			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
					if (grid[nr][nc] == 0 || grid[nr][nc] == blockColor) {
						if (grid[nr][nc] == 0) {
							rainbowList.add(new int[] { nr, nc });
						}
						size++;
						visited[nr][nc] = true;
						q.add(new int[] { nr, nc });
					}
				}
			}
		}
		if (size < 2)
			return null;
		BlockGroup blockGroup = new BlockGroup(size, rainbowList.size(), startR, startC);
		for (int[] rainbow : rainbowList) {
			visited[rainbow[0]][rainbow[1]] = false;
		}
		return blockGroup;
	}
}
