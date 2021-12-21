import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 16032 180
 * @author CHO
 * @see https://www.acmicpc.net/problem/21609
 * @category 구현, BFS
 * visited -> 무지개 블럭은 다시 false로 초기화
 */
public class BOJ_21609_상어중학교 {
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
	static int N, M, blockX, blockY, max, rainbowMax;
	static int[][] map;
	static boolean[][] visited;
	static Queue<Position> q;
	static int[][] dir = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		} // 입력 완료
		int result = 0;
		while (true) {
			max = Integer.MIN_VALUE;
			rainbowMax = Integer.MIN_VALUE;
			blockX = -1;
			blockY = -1; // 블록 그룹의 기준 xy
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] > 0 && map[i][j] <= M && !visited[i][j]) {
						bfs(i, j);
					}
				}
			} // 블록 그룹 찾기
			if (blockX == -1 && blockY == -1)
				break;
			int s = remove(blockX, blockY); // 그룹에 있는 모든 블록 제거
			result += (s * s);
			gravity(); // 중력
			rotation(); // 90도 반시계 회전
			gravity(); // 중력
		}
		System.out.println(result);
	}

	private static void rotation() {
		int[][] rot = new int[N][N];
		int rotx = 0;
		for (int j = N - 1; j >= 0; j--) {
			int roty = 0;
			for (int i = 0; i < N; i++) {
				rot[rotx][roty] = map[i][j];
				roty++;
			}
			rotx++;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = rot[i][j];
			}
		}
	}

	private static void gravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {
				if (map[i][j] <= M && map[i][j] != -1) {
					// 밑으로 내릴 수 있음
					int curx = i;
					while (true) {
						// 내릴수 있을 때 까지 가거나, -1 만나거나, 값이 존재할 때 까지
						curx++;
						if (curx >= N || map[curx][j] == -1 || map[curx][j] <= M) {
							if (curx - 1 == i)
								break;
							map[curx - 1][j] = map[i][j];
							map[i][j] = M + 1;
							break;
						}
					}
				}
			}
		}
	}

	private static void bfs(int x, int y) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					visited[i][j] = false;
				}
			}
		} // 무지개 블록 자리는 visited 초기화
		int blockCnt = 1; // 블럭 그룹에 있는 블럭 갯수
		int rainbowCnt = 0; // 무지개 블럭 갯수
		int color = map[x][y]; // 기준 색
		int endx = x;
		int endy = y; // 기준 블럭

		visited[x][y] = true;
		q = new LinkedList<>();
		q.add(new Position(x, y));

		while (!q.isEmpty()) {
			Position cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if (isOkay(nx, ny) && !visited[nx][ny] && map[nx][ny] != -1) {
					if (map[nx][ny] == color || map[nx][ny] == 0) {
						// 검은색 블럭이 아니고, 이전의 블럭 색과 같거나 무지개블럭이면
						if (map[nx][ny] == 0)
							rainbowCnt++;
						visited[nx][ny] = true;
						blockCnt++;
						q.add(new Position(nx, ny));
					}
				}
			}
		}
		boolean flag = false; // true면 값 바꿔야 한다는 뜻
		if (blockCnt >= 2) {
			// 일반블럭이 적어도 하나 있어야, 2보다 크면 블럭 그룹 후보
			// 블럭 개수 > 무지개 블럭 > 행 크기 > 열 크기
			if (max == blockCnt) {
				if (rainbowCnt == rainbowMax) {
					if (blockX == endx) {
						if (endy > blockY) {
							flag = true;
						}
					} else if (endx > blockX) {
						flag = true;
					}
				} else if (rainbowCnt > rainbowMax) {
					flag = true;
				}
			} else if (max < blockCnt) {
				flag = true;
			}
		}
		if (flag) {
			max = blockCnt;
			blockX = endx;
			blockY = endy;
			rainbowMax = rainbowCnt;
		}
	}

	private static int remove(int x, int y) {
		int count = 0;
		visited = new boolean[N][N];
		visited[x][y] = true;
		q = new LinkedList<>();
		q.add(new Position(x, y));

		while (!q.isEmpty()) {
			Position cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dir[i][0];
				int ny = cur.y + dir[i][1];
				if (isOkay(nx, ny) && !visited[nx][ny] && map[nx][ny] != -1) {
					if (map[nx][ny] == map[blockX][blockY] || map[nx][ny] == 0) {
						visited[nx][ny] = true;
						q.add(new Position(nx, ny));
					}
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) {
					map[i][j] = M + 1;
					count++;
				}
			}
		} // remove -> 삭제할 부분은 M+1로 만들어줌, 점수 구하기 위해 count++
		return count;
	}

	private static boolean isOkay(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < N && ny < N;
	}
}
