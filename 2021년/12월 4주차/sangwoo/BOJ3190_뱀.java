import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ3190_뱀 {
	private final static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int N, K, L, Answer, direction;
	static int[][] map;
	static List<int[]> snake = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(input.readLine());
		map = new int[N][N];
		K = Integer.parseInt(input.readLine());
		String[] ss;
		for (int i = 0; i < K; i++) {
			ss = input.readLine().split(" ");
			int r = Integer.parseInt(ss[0]) - 1, c = Integer.parseInt(ss[1]) - 1;
			map[r][c] = 1;
		}
		direction = 0;
		L = Integer.parseInt(input.readLine());
		String[] commands = new String[L];
		for (int i = 0; i < L; i++) {
			commands[i] = input.readLine();
		}
		snake.add(new int[] { 0, 0 });
		playDummy(commands);
		System.out.println(Answer);
	}

	private static void playDummy(String[] commands) {
		int index = 0;
		String[] ss = commands[index].split(" ");
		int time = Integer.parseInt(ss[0]);
		String nextDirection = ss[1];
		while (true) {
			Answer++;
			int[] head = snake.get(0);
			int nr = head[0] + deltas[direction][0], nc = head[1] + deltas[direction][1];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
				return;
			}
			for (int j = 1; j < snake.size(); j++) {
				int[] body = snake.get(j);
				if (nr == body[0] && nc == body[1]) {
					return;
				}
			}
			snake.add(0, new int[] { nr, nc });
			if (map[nr][nc] != 1)
				snake.remove(snake.size() - 1);
			else
				map[nr][nc] = 0;
			if (Answer == time) {
				if (nextDirection.equals("D"))
					direction++;
				else
					direction--;
				if (direction >= 4)
					direction -= 4;
				else if (direction < 0)
					direction += 4;
				if (++index < commands.length) {
					ss = commands[index].split(" ");
					time = Integer.parseInt(ss[0]);
					nextDirection = ss[1];
				}
			}
		}

	}
}
