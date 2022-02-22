package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2636 {

	static int R;	// 세로
	static int C;	// 가로
	static int[][] box;	// 치즈
	static int time = 0;	// 소요 시간
	static int cheese = 0;	// 치즈 조각
	static int remain = 0;	// 나머지
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 치즈
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		box = new int[R][C];
		for(int r=0; r<R; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<C; c++) {
				box[r][c] = Integer.parseInt(st.nextToken());
				if(box[r][c]==1)
					cheese++;
			}
		}
		
		while(cheese>0) {
			MELT();
		}
		
		System.out.println(time);
		System.out.println(remain);
	}
	
	static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
	
	// 치즈 녹이기
	// 0 을 기준으로 bfs 탐색
	static void MELT() {
		Queue<Dot> queue = new LinkedList<>();
		Queue<Dot> melt = new LinkedList<>();	
		boolean[][] visited = new boolean[R][C];
		queue.add(new Dot(0, 0));
		visited[0][0] = true;
		
		while (!queue.isEmpty()) {
			Dot current = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];

				if (isIn(nr, nc) && !visited[nr][nc]) {
					if(box[nr][nc]==0) queue.add(new Dot(nr, nc));
					else if(box[nr][nc]==1)	melt.add(new Dot(nr, nc)); // 0을 기준으로 bfs 탐색하면서 상하좌우에 치즈가 있는지 확인
						
					visited[nr][nc] = true;
				}
			}
		}
		
		cheese -= melt.size();
		remain = melt.size();
		
		while(!melt.isEmpty()) {	// 치즈를 한번에 모아서 녹이기
			Dot change = melt.poll();
			box[change.x][change.y] = 0;
		}
		
		time++;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<R && c>=0 && c<C;
	}
	
	static class Dot{
		int x,y;

		public Dot(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
