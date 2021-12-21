import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 49236	408
 * @author CHO
 * @see
 * @category 구현, BFS
 * 중력 어떻게 작용할지 고민
 */
public class BOJ_2933_미네랄 {
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
	static int R, C, N;
	static char[][] map;
	static int[] stick;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static boolean[][] visited;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R + 1][C + 1];
		for (int i = 1; i < R + 1; i++) {
			String str = br.readLine();
			for (int j = 1; j < C + 1; j++) {
				map[i][j] = str.charAt(j - 1);
			}
		}
		N = Integer.parseInt(br.readLine()); // 막대를 던진 횟수
		stick = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			stick[i] = Integer.parseInt(st.nextToken());
		} // 입력 완료

		// 막대를 던진 횟수만큼 반복
		for (int n = 0; n < N; n++) {
			int r = R + 1 - stick[n]; // 밑에서부터 1이므로
			// 짝수는 왼쪽에서 시작, 홀수는 오른쪽에서 시작
			// 처음으로 만나는 위치가 x라면 미네랄 파괴, 멈춤
			if (n % 2 == 0) {
				for (int c = 1; c < C + 1; c++) {
					if (map[r][c] == 'x') {
						map[r][c] = '.';
						break;
					}
				}
			} else {
				for (int c = C; c > 0; c--) {
					if (map[r][c] == 'x') {
						map[r][c] = '.';
						break;
					}
				}
			}
			/*
			 * 클러스터의 좌표 중 행이 R인 것이 있다? -> 바닥에 붙어있다 -> 중력 x
			 * 그게 아니라면 -> 중력 o
			 */
			visited = new boolean[R + 1][C + 1];
			r = R; // 바닥에 있는 정점에서만 클러스터 찾기 (r==R)
			for (int c = 1; c < C + 1; c++) {
				if (map[r][c] == 'x')
					bfs(r, c);
			}// 중력 적용할 클러스터 찾기
			
			// 바닥으로부터 떨어진 클러스터(x)는 visited가 false, 중력 적용 대상 (한 클러스터만 나옴)
			int min = R + 1; // 중력 작동 거리 구함
			// 
			/*
			 * 한 열씩 밑에서부터 최초로 나오는 떨어져있는 좌표 구하기
			 * 좌표에서 행++ 하면서 바닥으로부터 얼마나 떨어져 있는지 height 구하기
			 * height min과 현재 구한 height 비교하기
			 * 
			 */
			for (int j = 1; j < C + 1; j++) {
				for (int i = R; i > 0; i--) {
					if (!visited[i][j] && map[i][j] == 'x') {
						int height = 0;
						int startX = i;
						int startY = j;
						for (int ii = startX + 1; ii < R + 1; ii++) {
							if (map[ii][startY] == '.')
								height++;
							else
								break;
						}
						if (height != 0)
							min = min > height ? height : min;
						break;
					}
				}
			}
			// min 만큼 중력 작동
			if (min < R + 1) {
				for (int i = R; i > 0; i--) {
					for (int j = 1; j < C + 1; j++) {
						if (!visited[i][j] && map[i][j] == 'x') {
							map[i][j] = '.';
							map[i + min][j] = 'x';
						}
					}
				}
			}
		}
		// 결과 출력
		for (int i = 1; i < R + 1; i++) {
			for (int j = 1; j < C + 1; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void bfs(int x, int y) {
		visited[x][y] = true;
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(x, y));

		while (!q.isEmpty()) {
			Position cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if (isOkay(nx, ny) && !visited[nx][ny] && map[nx][ny] == 'x') {
					visited[nx][ny] = true;
					q.add(new Position(nx, ny));
				}
			}
		}

	}

	private static boolean isOkay(int nx, int ny) {
		return nx > 0 && ny > 0 && nx <= R && ny <= C;
	}
}
