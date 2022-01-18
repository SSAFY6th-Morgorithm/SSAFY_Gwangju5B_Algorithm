import java.io.*;
import java.util.*;

/**
 * 15%���� Ʋ�Ƚ��ϴ�. ���ڸ��� ��� ����?->�׳� �����ڸ� q�� �߰�?->Ʋ��
 * @author CHO
 * @see https://www.acmicpc.net/problem/16954
 * @category BFS
 */

public class BOJ_16594_�����̴¹̷�Ż�� {
	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public void setX(int x) {
			this.x = x;
		}

		public void setY(int y) {
			this.y = y;
		}

	}

	static StringTokenizer st;
	static final int N = 8;
	static char[][] map;
	static int dir[][] = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 }, { -1, -1 }, { -1, 1 }, { 1, -1 }, { 1, 1 }};
	static ArrayList<Position> walls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[N + 1][N + 1];
		walls = new ArrayList<>(); // �� ��ġ ����Ʈ
		for (int i = 1; i < N + 1; i++) {
			String str = br.readLine();
			for (int j = 1; j < N + 1; j++) {
				map[i][j] = str.charAt(j - 1);
				if (map[i][j] == '#')
					walls.add(new Position(i, j));
			}
		} // �Է� �Ϸ�

		// ������ġ�� (8,1)
		if (bfs(N, 1)) {
			System.out.println(1);
		} else
			System.out.println(0);

	}

	private static boolean bfs(int i, int j) {
		Queue<Position> q = new LinkedList<>();
		q.add(new Position(i, j));
//		boolean[][] visited = new boolean[N + 1][N + 1];
//		visited[i][j] = true;
		while (!q.isEmpty()) {
			int size = q.size();
			while (size-- > 0) {
				Position cur = q.poll();
				int x = cur.x;
				int y = cur.y;
				if (x == 1 && y == N) {
					return true;
				}	
				// ������ �� �ִ� ����
				if (map[x][y] == '.') {
					for (int d = 0; d < dir.length; d++) {
						int nx = x + dir[d][0];
						int ny = y + dir[d][1];
						if (isOkay(nx, ny) && map[nx][ny] == '.') {
							q.add(new Position(nx, ny));
						}
					}
					// ���ڸ�
					q.add(new Position(x, y));
				}
			}
			// ���� �����̸� ���� ��ġ ������ ������
			for (int k = 0; k < walls.size(); k++) {
				Position wall = walls.get(k);
				int nx = wall.x + 1; // ��ĭ ������ ���� ��ġ
				int ny = wall.y;
				if (isOkay(nx, ny)) {
					map[wall.x][wall.y] = '.';
					wall.setX(nx);
					map[wall.x][wall.y] = '#';
				} else
					walls.remove(k--);
			}
		}
		return false;
	}

	private static boolean isOkay(int nx, int ny) {
		return nx > 0 && ny > 0 && nx <= N && ny <= N;
	}
}
