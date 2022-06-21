package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2573 {

	static int N;	// 행
	static int M;	// 열
	static int[][] sea;	// 바다
	static int year;	// 최초 시간
	static List<Dot> list;
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};	// 방향
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws IOException {
		// 빙산
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sea = new int[N][M];
		
		list = new ArrayList<>();
		
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				sea[r][c] = Integer.parseInt(st.nextToken());
				if(sea[r][c]!=0) {
					list.add(new Dot(r,c,sea[r][c]));
				}
			}
		}
		
		year = 0;
		
		outer:while(true) {
			for(int i=0; i<list.size(); i++) {	// 빙하 녹이기
				Dot ice = list.get(i);
				
				for(int d=0; d<4; d++) {
					int nr = ice.x + deltas[d][0];
					int nc = ice.y + deltas[d][1];
					
					if(isIn(nr,nc) && sea[nr][nc]==0) {
						list.get(i).height--;
						if(list.get(i).height<0)
							list.get(i).height = 0;
					}
				}
			}
			
			for(int i=0; i<list.size(); i++) {	// 녹인 빙하 바다에 적용하기
				Dot change = list.get(i);
				sea[change.x][change.y] = change.height;
			}
			
			list.clear();
			
			int lump = 0;	// 덩어리 개수
			boolean[][] visited  = new boolean[N][M];
			for(int r=0; r<N; r++) {	// 덩어리 개수 확인하기(bfs)
				for(int c=0; c<M; c++) {
					if(sea[r][c]!=0 && !visited[r][c]) {
						bfs(r, c, visited);
						lump++;
					}
				}
			}
			
			year++;
			
			if(lump>=2) {	// 덩어리가 2개 이상일때
				break outer;
			}else if(lump==0) {	// 빙하가 다 녹기전까지 덩어리가 2개 이하일 경우
				year =0;
				break outer;
			}else {
				continue outer;
			}
		}
		
		System.out.println(year);
		
	}
	
	static void bfs(int r, int c, boolean[][] visited) {
		Queue<Dot> queue = new LinkedList<>();
		queue.offer(new Dot(r, c, sea[r][c]));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Dot current = queue.poll();
			list.add(current);
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr, nc) && sea[nr][nc]!=0 && !visited[nr][nc]) {
					queue.offer(new Dot(nr, nc, sea[nr][nc]));
					visited[nr][nc] = true;;
				}
			}
		}
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
	
	static class Dot{
		int x, y, height;

		public Dot(int x, int y, int height) {
			super();
			this.x = x;
			this.y = y;
			this.height = height;
		}
	}
}
