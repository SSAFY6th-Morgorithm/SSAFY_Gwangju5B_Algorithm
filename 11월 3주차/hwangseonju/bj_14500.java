package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class bj_14500 {

	static int N; // 세로
	static int M; // 가로
	static int[][] mino; // 테트로미노가 놓인 칸
	static PriorityQueue<Dot> pq;	// 테트로미노가 놓인 칸의 수 - 우선순위 큐(내림차순)
	static int max;	 // 합의 최대값
	static boolean[][] visited;		// 방문 확인
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 테트로미노
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		mino = new int[N][M];
		for (int r = 0; r < N; r++) {	// 입력값 받기
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				mino[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		pq = new PriorityQueue<>();
		visited = new boolean[N][M];
		max = Integer.MIN_VALUE;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				pq.add(new Dot(r, c, mino[r][c]));
				Search();
			}
		}
		System.out.println(max);
	}

	static int deltas[][] = { { 1, 0 }, { 0, -1 }, { 0, 1 } };	// 위쪽은 탐색X
	static void Search() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			Dot m = pq.poll();

			visited[m.x][m.y] = true;
			sum += m.num;
			for (int d = 0; d < 3; d++) {
				int nr = m.x + deltas[d][0];
				int nc = m.y + deltas[d][1];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					pq.add(new Dot(nr, nc, mino[nr][nc]));
				}
			}
		}
		max = Math.max(max, sum);
		for(boolean v[] : visited) {	// visited배열 초기화(생성보다 시간이 더 단축된다고 함)
			Arrays.fill(v, false);
		}
		pq.clear();
	}

	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

	static class Dot implements Comparable<Dot>{
		Integer x, y, num;

		public Dot(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}

		@Override
		public int compareTo(Dot o) {
			return o.num.compareTo(this.num);
		}
	}

}
