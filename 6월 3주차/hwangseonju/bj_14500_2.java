package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_14500_2 {

	static int N; // 세로
	static int M; // 가로
	static int[][] mino; // 테트로미노가 놓인 칸
	static int max;	 // 합의 최대값
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 테트로미노(재채점으로 다시 풀었음)
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

		boolean[][] visited = new boolean[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				dfs(r,c,mino[r][c], 1, visited);
				visited[r][c] = false;
				
				// ㅏ,ㅓ,ㅗ,ㅜ 확인
				for(int i=0; i<4; i++) {
					int sum = 0;
					boolean flag = true;
					
					for(int j=0; j<4; j++) {
						int nr = r + x_deltas[i][j];
						int nc = c + y_deltas[i][j];
						
						if(isIn(nr,nc)) {
							sum += mino[nr][nc];
						}else {
							flag = false;
							break;
						}
					}
					
					if(flag)
						max = Math.max(sum, max);
				}
			}
		}

		System.out.println(max);
		
	}
	
	static int[][] deltas = {{1,0},{0,-1},{0,1}};	// 위의 방향은 확인X
	static int[][] x_deltas = {{0,1,1,2},{0,1,1,2},{0,1,1,1},{0,0,0,1}};
	static int[][] y_deltas = {{0,0,1,0},{1,0,1,1},{1,0,1,2},{0,1,2,1}};
	
	static void dfs(int r, int c, int sum, int i, boolean[][] visited) {
		if(i==4) {
			max = Math.max(sum, max);
			return;
		}
		
		for(int d=0; d<3; d++) {
			int nr = r + deltas[d][0];
			int nc = c + deltas[d][1];
			
			if(isIn(nr,nc) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				dfs(nr,nc, sum+mino[nr][nc], i+1, visited);
				visited[nr][nc] = false;
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}

}
