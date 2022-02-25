package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_18404 {

	static int N;	// 체스판 크기
	static int M;	// 상대 말 개수
	static int[][] chess;	// 체스
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		// 현명한 나이트
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		chess = new int[N][N];
		boolean[][] visited = new boolean[N][N];
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken())-1;
		int c = Integer.parseInt(st.nextToken())-1;
		Dot K = new Dot(r,c,0);
		visited[r][c] = true;
		
		List<Dot> list = new LinkedList<>();
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken())-1;
			c = Integer.parseInt(st.nextToken())-1;
			chess[r][c] = -1;	// 상대 말 위치
			list.add(new Dot(r,c,0));
		}
		
		int[][] deltas = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
		int check = 0;
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(K);

		while (!queue.isEmpty()) {
			Dot current = queue.poll();

			for (int d = 0; d < 8; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					if (chess[nr][nc] == -1) {
						chess[nr][nc] = current.cnt+1;
						check++;
						
						if (check == M) {
							queue.clear();
							break;
						}
					}
					
					queue.offer(new Dot(nr, nc, current.cnt + 1));
					visited[nr][nc] = true;
				}
			}
		}

		for(int i=0; i<list.size(); i++) {
			sb.append(chess[list.get(i).x][list.get(i).y]).append(" ");
		}
		
		sb.deleteCharAt(sb.lastIndexOf(" "));
		System.out.println(sb);
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
	}
	
	static class Dot {
		int x, y, cnt;

		public Dot(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
