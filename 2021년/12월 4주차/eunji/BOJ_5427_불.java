import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 170452	984
 * @author CHO
 * @see https://www.acmicpc.net/problem/5427\
 * @category 구현,BFS
 * 4179 불!과 매우 유사한 문제
 */
public class BOJ_5427_불 {
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
	static StringBuilder sb = new StringBuilder();
	static int w, h;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
	static char[][] map;
	static int result;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			result = 0;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			map = new char[h][w];
			// . 빈공간, # 벽, @ 상근이 위치, * 불
			Queue<Position> fq = new LinkedList<>();
			int x = -1;
			int y = -1; // 상근이 위치
			for (int i = 0; i < h; i++) {
				String str = br.readLine();
				for (int j = 0; j < w; j++) {
					map[i][j] = str.charAt(j);
					if (map[i][j] == '@') {
						x = i;
						y = j;
						map[i][j] = '.';
					}else if(map[i][j]=='*') fq.add(new Position(i, j));
				}
			} // 입력 완료
			int[][] timeS = new int[h][w];
			int[][] timeF = new int[h][w];
			
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == '*')
						cal(i, j, timeF, fq); // 불 이동
				}
			}
			Queue<Position> q = new LinkedList<>();
			q.add(new Position(x, y));
			cal(x, y, timeS, q); // 사람 이동
			int c=bfs(x, y, timeS, timeF); // 갈 수 있는지 확인
			if(c>0) sb.append(c + "\n");
			else sb.append("IMPOSSIBLE" + "\n");
		}
		System.out.println(sb);
	}

	private static int bfs(int x, int y, int[][] timeS, int[][] timeF) {
		int time = 1;
		boolean visited[][] = new boolean[h][w];
		visited[x][y] = true;
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(x, y));

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Position cur = q.poll();
				if (cur.x == 0 || cur.x == h-1 || cur.y == 0 || cur.y == w-1) {
					// 탈출
					return time;
				}
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];
					if (isOkay(nx, ny) && !visited[nx][ny] && map[nx][ny]=='.') {
						if (timeS[nx][ny] < timeF[nx][ny] || timeF[nx][ny]==0) {
							// 사람이 먼저 이동할 수 있거나, 불이 아직 안퍼졌을 때 이동가능
							visited[nx][ny] = true;
							q.add(new Position(nx, ny));
						}
					}
				}
			}
			time++;
		}
		return 0;
	}

	private static void cal(int x, int y, int[][] timev, Queue<Position> q) {
		int time = 1;
		timev[x][y] = time;
		time++;

		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Position cur = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = cur.x + dir[i][0];
					int ny = cur.y + dir[i][1];
					if (isOkay(nx, ny) && timev[nx][ny] == 0 && map[nx][ny] == '.') {
						timev[nx][ny] = time;
						q.add(new Position(nx, ny));
					}
				}
			}
			time++;
		}

	}

	private static boolean isOkay(int nx, int ny) {
		return nx >= 0 && ny >= 0 && nx < h && ny < w;
	}
}
