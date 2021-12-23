import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 11660	80
 * @author CHO
 * @see https://www.acmicpc.net/problem/11559
 * @category 구현, BFS
 * 중력 적용
 */

public class BOJ_11559_뿌요뿌요 {
	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static StringTokenizer st;
	static int N = 12;
	static int M = 6;
	static char[][] map;
	static boolean visited[][], group[][];
	static Queue<Position> q;
	static int[][] dir = { { 0, -1 }, { 0, 1 }, { 1, 0 }, { -1, 0 } };
	static int groupCnt;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N + 1][M + 1];
		for (int r = 1; r < N + 1; r++) {
			String str = br.readLine();
			for (int c = 1; c < M + 1; c++) {
				map[r][c] = str.charAt(c - 1);
			}
		} // 입력 완료
		int result = 0; // 연쇄
		while (true) {
			groupCnt = 0; // 뿌요 그룹이 몇개인지
			visited = new boolean[N + 1][M + 1]; // 방문 여부
			group = new boolean[N + 1][M + 1]; // 뿌요 그룹 여부
			for (int r = 1; r < N + 1; r++) {
				for (int c = 1; c < M + 1; c++) {
					if (map[r][c] != '.' && !visited[r][c])
						bfs(r, c); // 같은 뿌요 그룹 구하기
				}
			}
			if (groupCnt == 0) {
				System.out.println(result);
				break;
			}
			// 뿌요 그룹들 동시에 터지기
			for (int r = 1; r < N + 1; r++) {
				for (int c = 1; c < M + 1; c++) {
					if (group[r][c])
						map[r][c] = '.';
				}
			}
			result++;

			gravity();
		}

	}

	private static void gravity() {
		// 첫번째 열, 마지막 행부터 위로 올라가면서 중력 적용 할 뿌요 탐색
		for (int c = 1; c < M + 1; c++) {
			for (int r = N; r > 0; r--) {
				if (map[r][c] != '.') {
					int startR = r;
					// 해당 뿌요로부터 밑으로 내려가면서 어디가지 갈 수 있는지 확인
					for (int i = startR + 1; i < N + 1; i++) {
						if (map[i][c] != '.') {
							// 뿌요를 만났다면 그 전까지 갈 수 있다는 뜻
							if (i - 1 == r) // 만약, 중력 적용할 위치가 원래 뿌요의 위치라면 중력 x
								break;
							map[i - 1][c] = map[r][c];
							map[r][c] = '.';
							break;
						} else if (i == N) {
							if (i == r)
								break;
							map[i][c] = map[r][c];
							map[r][c] = '.';
							break;
						}
					}
				}
			}
		}
	}

	private static void bfs(int r, int c) {
		int count = 1; // 뿌요 개수
		visited[r][c] = true;
		q = new LinkedList<>();
		q.add(new Position(r, c));
		char color = map[r][c];
		ArrayList<Position> puyo = new ArrayList<>();
		puyo.add(new Position(r, c));

		while (!q.isEmpty()) {
			Position cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nr = cur.x + dir[i][0];
				int nc = cur.y + dir[i][1];
				if (isOkay(nr, nc) && !visited[nr][nc] && map[nr][nc] == color) {
					visited[nr][nc] = true;
					count++;
					q.add(new Position(nr, nc));
					puyo.add(new Position(nr, nc));
				}
			}
		}
		if (count >= 4) {
			groupCnt += 1;
			// 뿌요가 4개 이상이면 뿌요 그룹이다.
			// 그룹여부 group 배열에 저장
			for (int i = 0; i < puyo.size(); i++) {
				group[puyo.get(i).x][puyo.get(i).y] = true;
			}
		}
	}

	private static boolean isOkay(int nr, int nc) {
		return nr > 0 && nc > 0 && nr <= N && nc <= M;
	}
}
