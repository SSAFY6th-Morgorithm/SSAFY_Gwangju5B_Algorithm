package algo;

import java.io.*;
import java.util.*;

public class bj_1584 {

	static int N;	// 위험구역수
	static int M;	// 죽음구역수
	static int[][] map;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st = null;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 게임
		map = new int[501][501];
		
		N = Integer.parseInt(br.readLine());
		for(int n=0; n<N; n++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int temp = 0;
			
			if(x1>x2) {
				temp = x1;
				x1 = x2;
				x2 = temp;
			}
			
			if(y1>y2) {
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			for(int r=x1; r<=x2; r++) {
				for(int c=y1; c<=y2; c++) {
					map[r][c] = 1;
				}
			}
		}
		
		M = Integer.parseInt(br.readLine());
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int temp = 0;
			
			if(x1>x2) {
				temp = x1;
				x1 = x2;
				x2 = temp;
			}
			
			if(y1>y2) {
				temp = y1;
				y1 = y2;
				y2 = temp;
			}
			
			for(int r=x1; r<=x2; r++) {
				for(int c=y1; c<=y2; c++) {
					map[r][c] = 2;
				}
			}
		}
		
		// 안전구역 = 0, 위험구역 = 1, 죽음구역 = 2
		search();
		
	}
	
	static int[][] deltas = {{-1,0},{1,0},{0,1},{0,-1}};
	static void search() {
		PriorityQueue<Dot> pq = new PriorityQueue<>();
		pq.add(new Dot(0,0,0));
		
		boolean[][] visited = new boolean[501][501];
		visited[0][0] = true;
		
		while(!pq.isEmpty()) {
			Dot current = pq.poll();
			
			if(current.x==500 && current.y==500) {
				System.out.println(current.c);
				return;
			}
			
			for(int d=0; d<4; d++) {
				int nr = current.x + deltas[d][0];
				int nc = current.y + deltas[d][1];
				
				if(isIn(nr,nc) && map[nr][nc] != 2 && !visited[nr][nc]) {
					if (map[nr][nc] == 1) {
						pq.add(new Dot(nr,nc, current.c + 1));
					} else {
						pq.add(new Dot(nr,nc, current.c));
					}
					
					visited[nr][nc] = true;
				}
			}
		}
		
		System.out.println(-1);
		return;
	}
	
	static boolean isIn(int r, int c) {
		return r>=0 && r<501 && c>=0 && c<501;
	}
	
	static class Dot implements Comparable<Dot>{
		int x,y,c;

		public Dot(int x, int y, int c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Dot o) {
			return this.c - o.c;
		}
	}

}
