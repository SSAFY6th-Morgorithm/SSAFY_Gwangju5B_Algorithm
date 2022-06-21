import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954_움직이는미로탈출 {
	private final static int[][] deltas = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 },
			{ -1, -1 }, { 0, 0 } };
	static char[][] maze;

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		maze = new char[8][8];
		for (int i = 0; i < 8; i++)
			maze[i] = input.readLine().toCharArray();
		System.out.println(isAvailable() ? 1 : 0);
	}

	private static boolean isAvailable() {
		// TODO Auto-generated method stub
		Queue<int[]> q = new LinkedList<>();
		// start = (7, 0), end = (0, 7);
		boolean[][][] visited = new boolean[8][8][8]; // r, c, step
		visited[7][0][0] = true;
		int currentStep = 0;
		q.offer(new int[] { 7, 0, currentStep });
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			int r = tmp[0], c = tmp[1], step = tmp[2];
			if (currentStep != step) {
				moveMaze(currentStep);
				currentStep = step;
			}
			if (maze[r][c] == '#')
				continue;
			if (r == 0 && c == 7) {
				return true;
			}

			for (int d = 0; d < deltas.length; d++) {
				int nr = r + deltas[d][0], nc = c + deltas[d][1], nstep = step == 7 ? 7 : step + 1;
				if (nr >= 0 && nr < 8 && nc >= 0 && nc < 8 && !visited[nr][nc][nstep] && maze[nr][nc] != '#') {
					visited[nr][nc][nstep] = true;
					q.offer(new int[] { nr, nc, nstep });
				}
			}

		}
		return false;
	}

	static void moveMaze(int step) {
		for (int r = maze.length - 1; r >= 1 + step; r--) {
			maze[r] = maze[r - 1];
		}
		maze[step] = new char[8];
		Arrays.fill(maze[step], '.');
		
	}
}
