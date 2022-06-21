package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class bj_11559 {

	static char[][] field; // 12x6 필드
	static int result; // 연쇄 횟수
	static int flag; // 게임이 끝났는지 확인
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		// Puyo Puyo
		field = new char[12][6];
		for (int r = 0; r < 12; r++) {
			String str = br.readLine();
			for (int c = 0; c < 6; c++) {
				field[r][c] = str.charAt(c);
			}
		}

		flag = -1;
		result = 0;
		while (true) {
			bfs();
			if (flag == 0)
				break;
			gravity();

			result++;
		}

		System.out.println(result);
	}

	static int[][] deltas = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 상하좌우
	// 연쇄시킬 뿌요 찾기

	static void bfs() {
		flag = 0;
		for (int r = 0; r < 12; r++) {
			for (int c = 0; c < 6; c++) {
				if (field[r][c] != '.') {
					Queue<Dot> queue = new LinkedList<>();
					Queue<Dot> puyo = new LinkedList<>();
					boolean[][] visited = new boolean[12][6];

					visited[r][c] = true;
					queue.offer(new Dot(r, c, field[r][c]));
					puyo.offer(new Dot(r, c, field[r][c]));

					while (!queue.isEmpty()) {
						Dot dot = queue.poll();

						for (int d = 0; d < 4; d++) {
							int nr = dot.x + deltas[d][0];
							int nc = dot.y + deltas[d][1];

							if (isIn(nr, nc) && !visited[nr][nc] && field[nr][nc] == dot.color) {
								visited[nr][nc] = true;
								queue.offer(new Dot(nr, nc, field[nr][nc]));
								puyo.offer(new Dot(nr, nc, field[nr][nc]));
							}
						}
					}

					// 같은 색상의 뿌요가 상하좌우로 4개 이상이였을 경우 제거
					if (puyo.size() >= 4) {
						while (!puyo.isEmpty()) {
							Dot del = puyo.poll();
							field[del.x][del.y] = '.';
						}
						flag++;
					}
				}
			}
		}
	}

	// 중력
	static void gravity() {
		Queue<Character> queue = new LinkedList<>();
		for (int c = 0; c < 6; c++) {
			for (int r = 11; r >= 0; r--) {
				if (field[r][c] != '.') {
					queue.offer(field[r][c]);
					field[r][c] = '.';
				}
			}

			if (queue.size() > 0) {
				for (int r = 11; r >= 0; r--) {
					if (!queue.isEmpty()) {
						field[r][c] = queue.poll();
					} else {
						break;
					}
				}
			}
		}
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && r < 12 && c >= 0 && c < 6;
	}

	static class Dot {
		int x, y;
		char color;

		public Dot(int x, int y, char color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
	}

}
