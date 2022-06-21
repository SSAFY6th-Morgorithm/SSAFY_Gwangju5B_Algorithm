import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1938_통나무옮기기 {

	private final static int[][] deltas = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };

	static int N;
	static int[][] log, exit;
	static char[][] field;

	static class Info {
		int[][] log;
		int moveCount;

		public Info(int[][] log, int moveCount) {
			this.log = log;
			this.moveCount = moveCount;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(input.readLine());
		field = new char[N][N];
		log = new int[3][2];
		exit = new int[3][2];
		for (int r = 0, logIndex = 0, exitIndex = 0; r < N; r++) {
			field[r] = input.readLine().toCharArray();
			for (int c = 0; c < N; c++) {
				if (field[r][c] == 'B') {
					log[logIndex++] = new int[] { r, c };
				} else if (field[r][c] == 'E') {
					exit[exitIndex++] = new int[] { r, c };
				}
			}
		}
		int answer = getMinimalMove();
		System.out.println(answer);
	}

	private static int getMinimalMove() {
		// TODO Auto-generated method stub
		Queue<Info> q = new LinkedList<>();
		int minCount = Integer.MAX_VALUE;
		boolean[][][] visited = new boolean[N][N][2]; // 0 : vertical, 1 : horizontal

		int startR = log[1][0], startC = log[1][1];
		if (log[0][1] == log[1][1])
			visited[startR][startC][0] = true;
		else
			visited[startR][startC][1] = true;
		q.offer(new Info(log, 0));
		while (!q.isEmpty()) {
			Info current = q.poll();
			int midR = current.log[1][0], midC = current.log[1][1];
			if (midR == exit[1][0] && midC == exit[1][1]) {
				boolean isEnded = true;
				for (int i = 0; i < 3; i++) {
					if (current.log[i][0] != exit[i][0] || current.log[i][1] != exit[i][1]) {
						isEnded = false;
						break;
					}
				}
				if (isEnded && current.moveCount != 0 && minCount > current.moveCount)
					minCount = current.moveCount;
			}
			boolean isVertical = current.log[0][1] == current.log[1][1];
			int currentShape = isVertical ? 0 : 1;
			int nextShape = currentShape == 1 ? 0 : 1;
			for (int d = 0; d < deltas.length; d++) {
				boolean isMoveable = true;
				for (int i = 0; i < 3; i++) {
					int nr = current.log[i][0] + deltas[d][0], nc = current.log[i][1] + deltas[d][1];
					if(!(nr >= 0 && nr < N && nc >= 0 && nc < N && field[nr][nc] != '1')) {
						isMoveable = false;
						break;
					}
				}
				if (isMoveable) {
					int nr = midR + deltas[d][0], nc = midC + deltas[d][1];
					if (!visited[nr][nc][currentShape]) {
						int[][] nextLog = new int[3][];
						for (int i = 0; i < 3; i++)
							nextLog[i] = new int[] { current.log[i][0] + deltas[d][0],
									current.log[i][1] + deltas[d][1] };
						q.offer(new Info(nextLog, current.moveCount + 1));
						visited[nr][nc][currentShape] = true;
					}
				}
			}
			boolean isRotatable = checkRotatable(current.log);
			if (!visited[midR][midC][nextShape] && isRotatable) {
				int[][] rotatedLog = rotate(current.log);
				q.offer(new Info(rotatedLog, current.moveCount + 1));
				visited[midR][midC][nextShape] = true;
			}
		}
		return minCount == Integer.MAX_VALUE ? 0 : minCount;
	}

	private static int[][] rotate(int[][] currentLog) {
		// TODO Auto-generated method stub
		boolean isVertical = currentLog[0][1] == currentLog[1][1];
		int d = 0;
		if (isVertical)
			d = 1;
		currentLog[0][0] = currentLog[1][0] + deltas[d][0];
		currentLog[0][1] = currentLog[1][1] + deltas[d][1];
		currentLog[2][0] = currentLog[1][0] + deltas[d + 2][0];
		currentLog[2][1] = currentLog[1][1] + deltas[d + 2][1];

		return currentLog;
	}

	private static boolean checkRotatable(int[][] currentLog) {
		// TODO Auto-generated method stub
		boolean isVertical = currentLog[0][1] == currentLog[1][1];
		int start = 0;
		if (isVertical)
			start = 1;
		for (int d = start; d < deltas.length; d += 2) {
			for (int i = 0; i < 3; i++) {
				int nr = currentLog[i][0] + deltas[d][0], nc = currentLog[i][1] + deltas[d][1];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || field[nr][nc] == '1')
					return false;
			}
		}
		return true;
	}
}
