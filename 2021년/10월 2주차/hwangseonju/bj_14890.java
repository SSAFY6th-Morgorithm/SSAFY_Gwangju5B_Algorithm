import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14890 {
	static int N; // 철별지대
	static int L; // 경사로 가로 길이
	static int[][] height; // 지형 높이 정보
	static int result; // 설치 가능한 활주로 개수
	static boolean[][] visited; // 설치한 곳 확인
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 경사로(swea_4014와 거의 같은 문제)
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		height = new int[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int c = 0; c < N; c++) {
				height[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		result = 0;

		visited = new boolean[N][N];
		outer: for (int r = 0; r < N; r++) { // 행별로 가능한 곳 찾기
			for (int c = 1; c < N; c++) {
				int current = height[r][c - 1];
				if (current != height[r][c]) {
					int minus = current - height[r][c];
					if (Math.abs(minus) >= 2)	// 경사로의 높이는 1로 제한되어 있기 때문에 차이가 2를 넘어가면 그 행은 무조건 안됨.
						continue outer;
					if (!rowcheck(new Point(r, c, minus)))
						continue outer;
				}
			}
			result++;	// 모두 통과시 경사로를 놓을 수 있는 길임 -> result 1 증가
		}

		visited = new boolean[N][N];		// 방문한 곳 리셋
		outer: for (int c = 0; c < N; c++) { // 열별로 가능한 곳 찾기
			for (int r = 1; r < N; r++) {
				int current = height[r - 1][c];
				if (current != height[r][c]) {
					int minus = current - height[r][c];
					if (Math.abs(minus) >= 2)	// 경사로의 높이는 1로 제한되어 있기 때문에 차이가 2를 넘어가면 그 열은 무조건 안됨.
						continue outer;
					if (!calcheck(new Point(r, c, minus)))
						continue outer;
				}
			}
			result++;
		}

		System.out.println(result);
	}

	// 높이가 차이나는 곳에 경사로를 놓을 수 있는지 확인(행)
	static boolean rowcheck(Point idx) {
		int r = idx.x;
		int c = idx.y;
		int minus = idx.w;	// 내리막길인지 오르막길인지 확인
		int zero = 0;		// 가능한 경사로의 길이 확인

		if (minus == 1) {	// 내리막길
			if(!visited[r][c]) {	// 시작부분이 방문하지 않은 곳이면 경사로의 길이는 1부터 시작
				visited[r][c] = true;
				zero = 1;
			}
			for (int nc = c + 1; nc < N; nc++) {
				if (zero == L) {	// 경사로의 길이가 1인 경우도 있기 때문에 가장 먼저 확인
					return true;
				}
				if (height[r][nc] == height[r][nc - 1] && !visited[r][nc]) {
					zero++;
					visited[r][nc] = true;
				} else {
					return false;
				}
			}
		} else {	// 오르막길
			if(!visited[r][c-1]) {
				visited[r][c - 1] = true;
				zero = 1;
			}
			for (int nc = c - 2; nc >= 0; nc--) {
				if (zero == L) {
					return true;
				}
				if (height[r][nc] == height[r][nc + 1] && !visited[r][nc]) {
					zero++;
					visited[r][nc] = true;
					
				} else {
					return false;
				}
			}
		}

		if (zero < L) {
			return false;
		}
		return true;
	}

	// 높이가 차이나는 곳에 경사로를 놓을 수 있는지 확인(열)
	static boolean calcheck(Point idx) {
		int r = idx.x;
		int c = idx.y;
		int minus = idx.w;
		int zero = 0;

		if (minus == 1) {
			if(!visited[r][c]) {
				visited[r][c] = true;
				zero = 1;
			}
			for (int nr = r + 1; nr < N; nr++) {
				if (zero == L) {
					return true;
				}
				if (height[nr][c] == height[nr - 1][c] && !visited[nr][c]) {
					zero++;
					visited[nr][c] = true;
				} else {
					return false;
				}
			}
		} else {
			if(!visited[r-1][c]) {
				visited[r - 1][c] = true;
				zero = 1;
			}
			for (int nr = r - 2; nr >= 0; nr--) {
				if (zero == L) {
					return true;
				}
				if (height[nr][c] == height[nr + 1][c] && !visited[nr][c]) {
					zero++;
					visited[nr][c] = true;
				} else {
					return false;
				}
			}
		}

		if (zero < L) {
			return false;
		}
		return true;
	}

	static class Point {
		int x, y, w;

		public Point(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}
	}
}
