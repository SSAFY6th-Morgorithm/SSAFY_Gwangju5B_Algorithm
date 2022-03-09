package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_21610 {
	
	static int N;	// 크기
	static int M;	// 이동 수
	static int water;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;

	public static void main(String[] args) throws IOException {
		// 마법사 상어와 비바리기
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][N];
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Dot> queue = new LinkedList<>();
		queue.add(new Dot(N-1,0));
		queue.add(new Dot(N-1,1));
		queue.add(new Dot(N-2,0));
		queue.add(new Dot(N-2,1));
		
		int[][] deltas = {{},{0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}};
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			boolean[][] clouds = new boolean[N][N];
			
			// 단계1. 구름 이동 시키기
			int size = queue.size();
			while(size-->0) {
				Dot current = queue.poll();
				
				int nr = current.x + (deltas[dir][0]*s);
				int nc = current.y + (deltas[dir][1]*s);
				
				while(nr>=N) {
					nr-=N;
				}
				while(nc>=N) {
					nc-=N;
				}
				while(nr<0) {
					nr+=N;
				}
				while(nc<0) {
					nc+=N;
				}
				
				queue.add(new Dot(nr,nc));
				clouds[nr][nc] = true;
			}
			
			// 단계2. 비가 내려 물양 1증가 + 단계3. 구름 모두 사라짐
			size = queue.size();
			while(size-->0) {
				Dot rain = queue.poll();
				map[rain.x][rain.y]++;
				queue.add(rain);
			}
			
			// 단계4. 물복사 버그 마법
			while(!queue.isEmpty()) {
				Dot magic = queue.poll();
				
				int cnt = 0;
				for(int d=2; d<=8; d=d+2) {
					int nr = magic.x + deltas[d][0];
					int nc = magic.y + deltas[d][1];
					
					if(isIn(nr,nc) && map[nr][nc]>=1) {
						cnt++;
					}
				}
				map[magic.x][magic.y] += cnt;
			}
			
			// 단계5. 구름 생성 및 물 양 감소
			for(int r=0; r<N; r++) {
				for(int c=0; c<N; c++) {
					if(map[r][c]>=2 && !clouds[r][c]) {
						queue.add(new Dot(r,c));
						map[r][c] -= 2;
					}
				}
			}
		}
		
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				water += map[r][c];
			}
		}
		System.out.println(water);
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<N && c>=0 && c<N;
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
