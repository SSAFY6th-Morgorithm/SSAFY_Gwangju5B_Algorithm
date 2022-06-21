package algo;

import java.io.*;
import java.util.*;

/**
 * 124708	724
 * @author CHO
 * @see https://www.acmicpc.net/problem/24513
 * @category ����,BFS
 * @warning �ð�,�޸� �ʰ� ����
 */
public class BOJ_24513_������̷��� {
	static class Pos {
		int x;
		int y;
		int c; //���° turn���� ���

		public Pos(int x, int y, int c) {
			this.x = x;
			this.y = y;
			this.c = c;
		}
	}

	static StringTokenizer st;
	static int N, M;
	static int[][] map, copy;
	static Queue<Pos> q;
	static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		copy = new int[N][M]; //���° turn���� ����ϴ� 2���� �迭
		q = new LinkedList<>();
		for (int n = 0; n < N; n++) {
			st = new StringTokenizer(br.readLine());
			for (int m = 0; m < M; m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
				if (map[n][m] == 1 || map[n][m] == 2) {
					q.add(new Pos(n, m, 1));
				}
			}
		} // �Է� �Ϸ�
		virus();
		int result[] = new int[4];
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (map[n][m] == 1)
					result[1] += 1;
				else if (map[n][m] == 2)
					result[2] += 1;
				else if (map[n][m] == 3)
					result[3] += 1;
			}
		}
		System.out.print(result[1] + " " + result[2] + " " + result[3]);
	}

	private static void virus() {
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			int x = cur.x;
			int y = cur.y;
			int c = cur.c;
			if (map[x][y] == 1 || map[x][y] == 2) {
				for (int i = 0; i < dir.length; i++) {
					int nx = x + dir[i][0];
					int ny = y + dir[i][1];
					if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] != -1 && map[nx][ny] != 3) {
						if (map[nx][ny] == 0) { 
							//�湮���� ���� ��
							map[nx][ny] = map[x][y];
							copy[nx][ny]=c;
							q.add(new Pos(nx, ny, c + 1));
						} else if (copy[nx][ny]==c && map[nx][ny] != map[x][y]) { 
							//���� turn, �湮�� ��
							map[nx][ny] = 3;
						}
					}
				}
			}
		}
	}
}
