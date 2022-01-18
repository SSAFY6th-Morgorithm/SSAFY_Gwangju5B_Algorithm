package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_16973 {

	static int N;	// 세로
	static int M;	// 가로
	static int[][] map;
	static int H;	// 직사각형 높이
	static int W;	// 직사각형 너비
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	static int result = -1;	// 이동횟수(결과)
	
	public static void main(String[] args) throws IOException {
		// 직사각형 탈출
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		
		Dot start = new Dot(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);		// 출발점
		Dot finish = new Dot(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 0);	// 도착점
		
		int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
		
		Queue<Dot> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.add(start);
		visited[start.x][start.y] = true;
		
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			
			if(current.x==finish.x && current.y==finish.y) {	// 도착점에 도착
				result = current.dis;
				break;
			}
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr, nc) && !visited[nr][nc] && map[nr][nc]!=1) {
					if(wall(nr, nc)) {
						queue.add(new Dot(nr, nc, current.dis+1));
						visited[nr][nc] = true;
					}
				}
			}
		}
		
		System.out.println(result);
	}
	
	// 직사각형과 벽이 겹치는지 확인
	static boolean wall(int r, int c) {
		for(int h=r; h<H+r; h++) {
			for(int w=c; w<W+c; w++) {
				if(!isIn(h, w) || map[h][w]==1) {	// 격자판을 벗어나거나 벽과 겹치면 이동 불가능
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class Dot{
		int x, y, dis;	// x:행, y:열, dis:이동거리

		public Dot(int x, int y, int dis) {
			super();
			this.x = x;
			this.y = y;
			this.dis = dis;
		}
	}
}